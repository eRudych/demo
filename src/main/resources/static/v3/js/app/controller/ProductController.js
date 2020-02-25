angular.module('app',[]).controller("ProductController", function($scope, $http) {
    $scope.product;
    $scope.products = [];

    _refreshProductData();

    $scope.submitProduct = function() {
        var method = "POST";
        var url = '/st-line/productsFun/';
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.product),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.deleteProduct = function(product) {
        $http({
            method: 'DELETE',
            url: '/st-line/productsFun/' + product.id
        }).then(_refreshProductData, _error);
    };


    $scope.editProduct = function() {
        $http({
            method: 'PUT',
            url: '/st-line/productsFun/',
            data: angular.toJson($scope.product),
            headers: {
                'Content-Type': 'application/json'
            },
        }).then( _error);
    };

    $scope.getProductById = function() {
        var userId = location.search.split('productId=')[1];
        $http({
            method: 'GET',
            url: '/st-line/productsFun/'+productId
        }).then(
            function(res) {
                $scope.product = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    $scope.createProduct = function() {
        _clearFormData();
    }

    function _success(res) {
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        //alert("Error: " + status + ":" + data);
        if(status==500){
            alert("Enter nick");
        }
    }

    function _clearFormData() {
        $scope.product.id = "";
        $scope.product.product = "";
    };

    function _refreshProductData() {
        $http({
            method: 'GET',
            url: '/st-line/productsFun/'
        }).then(
            function(res) {
                $scope.products = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
});