angular.module('app').controller('profileController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/api/v1/users/profile',
            method: 'GET'
        })
            .then(function (response) {
                console.log("getProfile");
                $scope.profile = response.data;
                console.log(response.data);
            });
    };

      $scope.CheckPass = function () {
            $http({
                url: contextPath + '/api/v1/users/confirmPassword',
                method: 'POST',
                params: {
                    password: $scope.user.password
                }
            })
                .then(function (response) {
                console.log(response.data);
                if (response.data){
                    console.log("pass check success");
                    $scope.user.password = null;
                    $scope.UpdateProfile();
                    }
                else {
                    window.alert("Вы ввели неверный пароль!");
                    }
                });
    };


      $scope.UpdateProfile = function () {
            $http.put(contextPath + '/api/v1/profile', $scope.profile)
                .then(function (response) {
                    console.log("updateProfile");
                    window.alert("Выш профиль изменен!");
                    $scope.fillTable();
                    });
        };

         $scope.getCitiesList = function () {
                $http({
                    url: contextPath + '/api/v1/cities',
                    method: 'GET'
                })
                    .then(function (response) {
                        $scope.CitiesList = response.data;
                    });
            };

            $scope.getGendersList = function () {
                            $http({
                                url: contextPath + '/api/v1/genders',
                                method: 'GET'
                            })
                                .then(function (response) {
                                    $scope.GendersList = response.data;
                                });
                        };

    $scope.fillTable();
    $scope.getCitiesList();
    $scope.getGendersList();

});

