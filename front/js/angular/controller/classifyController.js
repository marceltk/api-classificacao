app.controller("classifyController", ["$scope", "$http", function ($scope, $http) {
    $scope.send = function () {
        var url = "";
        var endPoint = "";
        var token = localStorage.getItem("token");
        var endPointAta = localStorage.getItem("endPointAta");
        var endPointPublicacao = localStorage.getItem("endPointPublicacao");
        var threshold = $scope.thre;
        var object = {};
        var texto = $scope.form.text;


        if (texto == null || texto == "") {
            alert("Please enter text!");
            return;
        }
        if ($scope.user.password == null || $scope.user.password == "") {
            alert("Please enter the password.");
            return;
        } else {
            pass = $scope.user.password;
        }

        if (tipo == "ata") {
            url = "ata";
            endPoint = endPointAta;
            object = {
                "IdSolicitacao": 1234,
                "Arquivos": [{"Arquivo": texto}]
            };
            if (endPointAta == null || endPointAta == "") {
                alert("Please, configure ata endpoint.")
            }
        } else {
            url = "publicacao";
            endPoint = endPointPublicacao;
            object = {
                "TextoPublicacao": texto,
                "uf": "",
                "sistema": ""
            };
            if (endPointPublicacao == null || endPointPublicacao == "") {
                alert("Please, configure publicacao endpoint.")
            }
        }

        var data =
            {
                "endPoint": endPoint,
                "threshold": threshold,
                "object": object
            };


        var requisicao = {
            method: 'POST',
            url: 'http://localhost:9000/api/v1/classification/' + url,
            headers: {"Content-Type": "application/json", "Access-Control-Allow-Origin": "*", "Authorization": token},
            data: data
        };

        $http(requisicao).then(function successCallback(response) {
            var data = response.data;
            if (data.statusCode() == 200) {
                $scope.return = data.message;
            } else {
                alert("Error return.");
            }

        }, function errorCallback(response) {
            console.log("There was an error performing the request.");
        });
    };

    $scope.save = function (endpoint) {
        if (endpoint.ata == null || endpoint.ata == "") {
            alert("Please, configure ata endpoint.")
            return;
        }
        if (endpoint.publicacao == null || endpoint.publicacao == "") {
            alert("Please, configure publicacao endpoint.")
            return;
        }

        localStorage.setItem("endPointAta", endpoint.ata);
        localStorage.setItem("endPointPublicacao", endpoint.publicacao);
        alert("Success!")
    };

    $scope.select = function () {
        tipo = $scope.tipo

        if (tipo == "ata") {
            $scope.text = "Enter the file link.";
        } else {
            $scope.text = "Enter the text.";
        }
    };


    $scope.threshold = function () {
        $scope.value = $scope.thre;
    };

    $scope.openWindow = function () {
        $('#modalClassify').modal();
    };

    $scope.ataModal = function () {
        $('#ataModal').modal();
    };
    $scope.publicacaoModal = function () {
        $('#publicacaoModal').modal();
    };
}]);