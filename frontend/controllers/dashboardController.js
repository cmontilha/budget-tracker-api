app.controller('DashboardController', function($scope, ApiService) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser') || 'null');
    $scope.newTx = {};
    $scope.transactions = [];
    $scope.total = 0;

    function load() {
        if (!currentUser) return;
        ApiService.listTransactions(currentUser.id).then(function(res) {
            $scope.transactions = res.data;
            $scope.total = $scope.transactions.reduce(function(a, b) { return a + b.amount; }, 0);
        });
    }

    $scope.addTransaction = function() {
        $scope.newTx.user = { id: currentUser.id };
        ApiService.addTransaction($scope.newTx).then(function(res) {
            $scope.newTx = {};
            load();
        });
    };

    load();
});
