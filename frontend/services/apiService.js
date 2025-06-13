app.factory('ApiService', function($http) {
    var base = 'http://localhost:8080';
    return {
        register: function(user) {
            return $http.post(base + '/users', user);
        },
        getUser: function(id) {
            return $http.get(base + '/users/' + id);
        },
        addTransaction: function(tx) {
            return $http.post(base + '/transactions', tx);
        },
        listTransactions: function(userId) {
            return $http.get(base + '/transactions', { params: { userId: userId }});
        }
    };
});
