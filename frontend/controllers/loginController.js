app.controller('LoginController', function($scope, $location, ApiService) {
    $scope.userId = null;
    $scope.login = function() {
        ApiService.getUser($scope.userId).then(function(res) {
            localStorage.setItem('currentUser', JSON.stringify(res.data));
            $location.path('/dashboard');
        }, function() {
            alert('User not found');
        });
    };
});
