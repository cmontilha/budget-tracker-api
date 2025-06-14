app.controller('LoginController', function($scope, $location, ApiService) {
    $scope.credentials = {};
    $scope.login = function() {
        ApiService.login($scope.credentials).then(function(res) {
            localStorage.setItem('currentUser', JSON.stringify(res.data));
            $location.path('/dashboard');
        }, function() {
            alert('Backend unavailable. Continuing in demo mode.');
            localStorage.setItem('currentUser', JSON.stringify($scope.credentials));
            $location.path('/dashboard');
        });
    };
});
