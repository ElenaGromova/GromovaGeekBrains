angular.module('app').controller('orderController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/api/v1/order',
            method: 'GET',
            params:{
                  token: $localStorage.currentUser.token
            }
        })
            .then(function (response) {
                console.log("getOrders");
                $scope.Orders = response.data;
            });
    };

    $scope.fillTable();

});