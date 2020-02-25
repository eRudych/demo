angular.module('app',[]).controller("PostController", function($scope, $http) {
    $scope.posts = [];
    $scope.postId=1;
    $scope.post={
        isSendPostIntoChannel: true
    };

    _refreshPostData();

    $scope.submitPost = function() {
        var post1;
        var method = "POST";
        var url = '/st-line/posts';
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.post),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(post1=$scope.post,_success, _error);
        console.log(post1);
    };

    $scope.createPost = function() {
        _clearFormData();
    }

    $scope.deletePost = function(post) {
        $http({
            method: 'DELETE',
            url: '/st-line/posts/' + post.id
        }).then(_refreshPostData, _error);
    };


    $scope.editPost = function() {
        $http({
            method: 'PUT',
            url: '/st-line/posts/',
            data: angular.toJson($scope.post),
            headers: {
                'Content-Type': 'application/json'
            },
        }).then(_refreshPostData, _error);
    };

    function _refreshPostData() {
        $http({
            method: 'GET',
            url: '/st-line/posts'
        }).then(
            function(res) {
                $scope.posts = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    $scope.getPost = function() {
        var postId = location.search.split('postId=')[1];
        $http({
            method: 'GET',
            url: '/st-line/posts/'+postId
        }).then(
            function(res) {
                $scope.post = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    function _success(res) {
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

    function _clearFormData() {
        $scope.post.text = "";
        $scope.post.description = "";
        $scope.post.title = ""
    };
});