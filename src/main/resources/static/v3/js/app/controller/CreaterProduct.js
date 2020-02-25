angular.module('app',[]).controller("ProductController", function($scope, $http) {
    $scope.product;
    $scope.products = [];
    $scope.categories = [];
    $scope.category= {};
    $scope.marks = [];
    $scope.mark={};

    _refreshCategoryData();
    _refreshMarkData();

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

    function _refreshCategoryData() {
        $http({
            method: 'GET',
            url: '/st-line/productsFun/categories'
        }).then(
            function(res) {
                $scope.categories = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _refreshMarkData() {
        $http({
            method: 'GET',
            url: '/st-line/productsFun/marks'
        }).then(
            function(res) {
                $scope.marks = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }


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
        $scope.product.mark = "";
        $scope.product.model = "";
        $scope.product.price = "";
        $scope.product.category = "";

    };
});