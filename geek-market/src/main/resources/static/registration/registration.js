angular.module('app').controller('registrationController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.tryToReg = function () {
        $http.post(contextPath + '/api/v1/registration', $scope.newUser)
            .then(function (response) {
                console.log("tryToReg");
                $scope.newUser=null;
                window.location = 'http://localhost:8189/market/index.html#!/auth';
                });
    };

});