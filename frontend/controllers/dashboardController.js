app.controller('DashboardController', function($scope, $location, ApiService) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser') || 'null');
    $scope.newTx = {};
    $scope.transactions = [];
    $scope.total = 0;

    function load() {
        if (!currentUser) {
            $location.path('/login');
            return;
        }
        ApiService.listTransactions(currentUser.id).then(function(res) {
            $scope.transactions = res.data;
            $scope.total = $scope.transactions.reduce(function(a, b) { return a + b.amount; }, 0);
        });
    }

    $scope.addTransaction = function() {
        $scope.newTx.user = { id: currentUser.id };
        if ($scope.newTx.date) {
            $scope.newTx.date = new Date($scope.newTx.date);
        }
        ApiService.addTransaction($scope.newTx).then(function(res) {
            $scope.newTx = {};
            load();
        });
    };

rkm447-codex/fix-and-finalize-frontend-registration,-login,-and-dashboard
    $scope.deleteTransaction = function(id) {
        ApiService.deleteTransaction(id).then(function() {
            load();
        });
    };


main
    $scope.logout = function() {
        localStorage.removeItem('currentUser');
        $location.path('/login');
    };

    load();
});
