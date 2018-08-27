angular.module("seb-task", [])
.controller("productSuggestions", function($scope, $http) {
    $scope.user= {
        age: 0,
        student: false,
        income: 0
    };
    $scope.getData = function() {
        $http.get('/products', { params: $scope.user}).then(function(response) {
            $scope.products = response.data;
        });
    };
});