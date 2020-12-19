angular.module('app').controller('confirmController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.createOrder = function () {
    $http({
                url: contextPath + '/api/v1/order',
                method: 'POST',
                params: {
                    address: $scope.newOrder.address,
                    phone: $scope.newOrder.phone
                }
            })
                .then(function (response) {
                    console.log("newOrder send");
                    $scope.newOrder = null;
                    window.alert("Ваш заказ успешно оформлен!");
                });
    };

        $scope.cartContentRequest = function () {
            $http({
                url: contextPath + '/api/v1/cart',
                method: 'GET'
            })
                .then(function (response) {
                    console.log("getCart");
                    $scope.cart = response.data;
                });
        };

    $scope.cartContentRequest();

});