app.controller("loginController", ["$scope", "$http", function ($scope, $http) {
    $scope.logar = function () {
        var username = "";
        var password = "";

        if ($scope.user.username == null || $scope.user.username == "") {
            alert("Please enter the username.!");
            return;
        } else {
            username = $scope.user.username;
        }
        if ($scope.user.password == null || $scope.user.password == "") {
            alert("Please enter the password.");
            return;
        } else {
            pass = $scope.user.password;
        }
        var data =
            {
                "username": username,
                "password": password
            };

        var requisicao = {
            method: 'POST',
            url: 'http://localhost:9000/api/v1/classification/auth',
            headers: {"Content-Type": "application/json", "Access-Control-Allow-Origin": "*"},
            data: data
        };

        $http(requisicao).then(function successCallback(response) {
            var data = response.data;
            if (data.statusCode() == 200) {
                localStorage.setItem("token", data.token);
                window.location.replace("classify.html");
            } else {
                alert("Incorrect username or passsword.");
            }

        }, function errorCallback(response) {
            console.log("There was an error performing the request.");
        });
    };
}]);