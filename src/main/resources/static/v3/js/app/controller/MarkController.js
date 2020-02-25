angular.module('app',[]).controller("MarkController", function($scope, $http) {
    $scope.marks = [];
    $scope.mark={};

    _refreshMarkData();

    $scope.submitMark = function() {
        var method = "POST";
        var url = '/st-line/productsFun/createMark';
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.mark),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.deleteMark = function(mark) {
        $http({
            method: 'DELETE',
            url: '/st-line/productsFun/marks/' + mark.id
        }).then(_refreshCategoryData, _error);
    };

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

    function _cleanMarkData() {
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


    $scope.createMark = function() {
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
        $scope.mark.id = "";
        $scope.mark.name = "";
    };
});