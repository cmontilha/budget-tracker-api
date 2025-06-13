app.controller('LoginController', function($scope, $location, ApiService) {
    $scope.credentials = {};
    $scope.login = function() {
        ApiService.login($scope.credentials).then(function(res) {
            localStorage.setItem('currentUser', JSON.stringify(res.data));
            $location.path('/dashboard');
        }, function() {
            alert('Invalid email or password');
        });
    };
});
