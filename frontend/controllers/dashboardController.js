app.controller('DashboardController', function($scope, $location) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser') || 'null');
    if (!currentUser) {
        $location.path('/login');
        return;
    }
    $scope.currentUser = currentUser;

    $scope.newTx = {};
    $scope.transactions = [];
    $scope.total = 0;
    $scope.monthlyTotals = {};
    $scope.dailyTotals = {};

    var storageKey = 'transactions_' + (currentUser.id || 'demo');

    function load() {
        var stored = JSON.parse(localStorage.getItem(storageKey) || '[]');
        stored.forEach(function(t) { t.date = new Date(t.date); });
        $scope.transactions = stored;
        computeTotals();
    }

    function save() {
        localStorage.setItem(storageKey, JSON.stringify($scope.transactions));
        computeTotals();
    }

    function pad(n) { return n < 10 ? '0' + n : n; }

    function computeTotals() {
        $scope.total = 0;
        $scope.monthlyTotals = {};
        $scope.dailyTotals = {};
        $scope.transactions.forEach(function(tx) {
            $scope.total += tx.amount;
            var d = new Date(tx.date);
            var monthKey = d.getFullYear() + '-' + pad(d.getMonth() + 1);
            var dayKey = d.toISOString().slice(0,10);
            $scope.monthlyTotals[monthKey] = ($scope.monthlyTotals[monthKey] || 0) + tx.amount;
            $scope.dailyTotals[dayKey] = ($scope.dailyTotals[dayKey] || 0) + tx.amount;
        });
    }

    $scope.addTransaction = function() {
        var tx = {
            description: $scope.newTx.description,
            amount: parseFloat($scope.newTx.amount),
            date: $scope.newTx.date ? new Date($scope.newTx.date) : new Date()
        };
        $scope.transactions.push(tx);
        $scope.newTx = {};
        save();
    };

    $scope.deleteTransaction = function(index) {
        $scope.transactions.splice(index, 1);
        save();
    };

    $scope.logout = function() {
        localStorage.removeItem('currentUser');
        $location.path('/login');
    };

    load();
});
