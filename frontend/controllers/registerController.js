app.controller('RegisterController', function($scope, $location, ApiService) {
    $scope.user = {};
    $scope.register = function() {
        ApiService.register($scope.user).then(function(res) {
            localStorage.setItem('currentUser', JSON.stringify(res.data));
            $location.path('/dashboard');
        });
    };
});
