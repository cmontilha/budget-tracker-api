var app = angular.module('budgetApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/register', { templateUrl: 'views/register.html', controller: 'RegisterController' })
        .when('/login', { templateUrl: 'views/login.html', controller: 'LoginController' })
        .when('/dashboard', { templateUrl: 'views/dashboard.html', controller: 'DashboardController' })
        .otherwise({ redirectTo: '/register' });
});

app.run(function($rootScope, $location) {
    $rootScope.$on('$routeChangeStart', function(event, next, current) {
        var user = JSON.parse(localStorage.getItem('currentUser') || 'null');
        if (!user && next.$$route && next.$$route.originalPath === '/dashboard') {
            $location.path('/login');
        }
    });
});
