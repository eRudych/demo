angular.module('app',[]).controller("CategoryController", function($scope, $http) {
    $scope.categories = [];
    $scope.category= {};

    _refreshCategoryData();

    $scope.submitCategory = function() {
        var method = "POST";
        var url = '/st-line/productsFun/createCategory';
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.category.name),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.deleteCategory = function(category) {
        $http({
            method: 'DELETE',
            url: '/st-line/productsFun/categories/' + category.id
        }).then(_refreshCategoryData, _error);
    };

    function _refreshCategoryData() {
        $http({
            method: 'GET',
            url: '/st-line/categories'
        }).then(
            function(res) {
                $scope.categories = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    // function _cleanCategoryData() {
    //     $http({
    //         method: 'GET',
    //         url: '/st-line/productsFun/categories'
    //     }).then(
    //         function(res) {
    //             $scope.categories = res.data;
    //         },
    //         function(res) {
    //             console.log("Error: " + res.status + " : " + res.data);
    //         }
    //     );
    // }


    $scope.getCategory = function() {
        var categoryId = location.search.split('categoryId=')[1];
        $http({
            method: 'GET',
            url: '/st-line/posts/'+categoryId
        }).then(
            function(res) {
                $scope.category = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    $scope.createCategory = function() {
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
            alert("Opps..");
        }
    }

    function _clearFormData() {
        $scope.category.id = "";
        $scope.category.name = "";
    };
});