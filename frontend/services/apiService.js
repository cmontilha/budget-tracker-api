app.factory('ApiService', function($http) {
    var base = 'http://localhost:8080';
    return {
        register: function(user) {
            return $http.post(base + '/users', user);
        },
        login: function(credentials) {
            return $http.post(base + '/users/login', credentials);
        },
        addTransaction: function(tx) {
            return $http.post(base + '/transactions', tx);
        },
        listTransactions: function(userId) {
            return $http.get(base + '/transactions', { params: { userId: userId }});
        },
        deleteTransaction: function(id) {
            return $http.delete(base + '/transactions/' + id);
        }
    };
});
