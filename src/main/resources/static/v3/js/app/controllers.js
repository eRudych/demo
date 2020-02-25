var app = angular.module("app",[])
app.directive('ckEditor', function () {
    return {
        require: '?ngModel',
        link: function (scope, elm, attr, ngModel) {
            var ck = CKEDITOR.replace(elm[0]);
            if (!ngModel) return;
            ck.on('instanceReady', function () {
                ck.setData(ngModel.$viewValue);
            });
            function updateModel() {
                scope.$apply(function () {
                    ngModel.$setViewValue(ck.getData());
                });
            }
            ck.on('change', updateModel);
            ck.on('key', updateModel);
            ck.on('dataReady', updateModel);

            ngModel.$render = function (value) {
                ck.setData(ngModel.$viewValue);
            };
        }
    };
});

//MESSAGE
app.controller("MessageController", function($scope, $http) {
    $scope.messages = [];
    $scope.messageForm;

    _refreshMessageData();

    $scope.submitMessage = function() {
        var method = "POST";
        var url = '/st-line';
        $http({

            method: method,
            url: url,
            data: angular.toJson($scope.messageForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.createMessage = function() {
        _clearFormData();
    }
    $scope.deleteMessage = function(message) {
        $http({
            method: 'DELETE',
            url: '/st-line/' + message.id
        }).then(_refreshMessageData, _error);
    };

    function _refreshMessageData() {
        $http({
            method: 'GET',
            url: '/st-line'
        }).then(
            function(res) {
                $scope.messages = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        _clearFormData();
        if(res.status==200){
            alert("Your message has been sent. Thank you!");
        }
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

    function _clearFormData() {
        $scope.messageForm.name = "";
        $scope.messageForm.phone = "";
        $scope.messageForm.message = "";
    };
});

//POST
app.controller("PostController", function($scope, $http) {
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

//     //PHOTO
//     app.controller("PhotoController", function($scope, $http) {
//         $scope.photos = [];
//         $scope.products = [];
//         $scope.models = [];
//         $scope.brands = [];
//         $scope.product;
//         $scope.model;
//         $scope.brand;
//         $scope.photo;
//
//         _refreshPhotoData();
//
//         $scope.searchPhoto = function() {
//             var method = "POST";
//             var url = '/st-line/posts';
//             $http({
//                 method: method,
//                 url: url,
//                 data: angular.toJson($scope.photo),
//                 headers: {
//                     'Content-Type': 'application/json'
//                 }
//             }).then(_success, _error);
//         };
//
//         function _refreshPhotoData() {
//             $http({
//                 method: 'GET',
//                 url: '/st-line/photos'
//             }).then(
//                 function(res) {
//                     $scope.brands = res.data;
//                     $scope.models = res.data;
//                     $scope.products = res.data;
//                 },
//                 function(res) {
//                     console.log("Error: " + res.status + " : " + res.data);
//                 }
//             );
//         }
//
//         // $scope.getPhoto = function() {
//         //     var photoId = location.search.split('photoId=')[1];
//         //     $http({
//         //         method: 'GET',
//         //         url: '/st-line/posts/'+photoId
//         //     }).then(
//         //         function(res) {
//         //             $scope.photo = res.data;
//         //         },
//         //         function(res) {
//         //             console.log("Error: " + res.status + " : " + res.data);
//         //         }
//         //     );
//         // };
//
//         function _success(res) {
//             _clearFormData();
//         }
//
//         function _error(res) {
//             var data = res.data;
//             var status = res.status;
//             var header = res.header;
//             var config = res.config;
//             alert("Error: " + status + ":" + data);
//         }
//
//         function _clearFormData() {
//             $scope.photo.text = "";
//             $scope.photo.description = "";
//             $scope.photo.tittle = ""
//         };
// });

app.controller("UserController", function($scope, $http) {
    $scope.user;

    $scope.submitUser = function() {
        var method = "POST";
        var url = '/st-line/users';
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.user),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.getUserById = function() {
        var userId = location.search.split('userId=')[1];
        $http({
            method: 'GET',
            url: '/st-line/users/'+userId
        }).then(
            function(res) {
                $scope.user = res.data;
            },
            function(res) {
                alert("Error: "+res.status)
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    $scope.getUserByName = function(user) {
        $http({
            method: 'GET',
            url: '/st-line/users/'
        }).then(
            function(res) {
                $scope.user = res.data;
            },
            function(res) {
                alert("Error: "+res.status)
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    $scope.createUser = function() {
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
        if(status==500){
            alert("Enter another nickname");
        }else{
            alert("Error: " + status + ":" + data);
        }
    }

    function _clearFormData() {
        $scope.user.nick = "";
        $scope.user.email = "";
        $scope.user.password = "";
    };
});

// app.controller("ProductController", function($scope, $http) {
//
//     $scope.product;
//     $scope.products = [];
//
//     _refreshProductData();
//
//     $scope.submitProduct = function() {
//         var method = "POST";
//         var url = '/st-line/productsFun/';
//         $http({
//             method: method,
//             url: url,
//             data: angular.toJson($scope.product),
//             headers: {
//                 'Content-Type': 'application/json'
//             }
//         }).then(_success, _error);
//     };
//
//     $scope.deleteProduct = function(product) {
//         $http({
//             method: 'DELETE',
//             url: '/st-line/productsFun/' + product.id
//         }).then(_refreshProductData, _error);
//     };
//
//
//     $scope.editProduct = function() {
//         $http({
//             method: 'PUT',
//             url: '/st-line/productsFun/',
//             data: angular.toJson($scope.product),
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//         }).then( _error);
//     };
//
//     $scope.getProductById = function() {
//         var userId = location.search.split('productId=')[1];
//         $http({
//             method: 'GET',
//             url: '/st-line/productsFun/'+productId
//         }).then(
//             function(res) {
//                 $scope.product = res.data;
//             },
//             function(res) {
//                 console.log("Error: " + res.status + " : " + res.data);
//             }
//         );
//     };
//
//     $scope.createProduct = function() {
//         _clearFormData();
//     }
//
//     function _success(res) {
//         _clearFormData();
//     }
//
//     function _error(res) {
//         var data = res.data;
//         var status = res.status;
//         var header = res.header;
//         var config = res.config;
//         //alert("Error: " + status + ":" + data);
//         if(status==500){
//             alert("Enter nick");
//         }
//     }
//
//     function _clearFormData() {
//         $scope.product.id = "";
//         $scope.product.product = "";
//     };
//
//     function _refreshProductData() {
//         $http({
//             method: 'GET',
//             url: '/st-line/productsFun/'
//         }).then(
//             function(res) {
//                 $scope.products = res.data;
//             },
//             function(res) {
//                 console.log("Error: " + res.status + " : " + res.data);
//             }
//         );
//     }
// });

app.controller("OrderController", function($scope, $http) {
    $scope.order;
    $scope.product;
    $scope.user = {
        name: " "
    };


    $scope.searchUserId = function () {
        $http({
            method: 'POST',
            url: '/st-line/users/searchIdByName',
            data: angular.toJson($scope.user.name),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            function (res) {
                // $scope.user = res.data;
                console.log(res.data());
                //submitOrder(res.data());
                console.log("succ");
            },
            function (res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function submitOrder(userId) {
        // var userId=0;
        // userId=id;
        var method = "POST";
        var url = '/st-line/orders';
        $http({
            method: method,
            url: url,
            // data:userId
            data: angular.toJson(userId),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error, console.log(userId));
    };


    $scope.deleteOrder = function (order) {
        $http({
            method: 'DELETE',
            url: '/st-line/order/' + product.id
        }).then(_refreshProductData, _error);
    };

    $scope.deleteProduct = function (product) {
        $http({
            method: 'DELETE',
            url: '/st-line/order/product/' + product.id
        }).then(_refreshProductData, _error);
    };


    function _refreshProductData() {
        $http({
            method: 'GET',
            url: '/st-line/order'
        }).then(
            function (res) {
                $scope.posts = res.data;
            },
            function (res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _cleanPostData() {
        $http({
            method: 'GET',
            url: '/st-line/order/clean/'
        }).then(
            function (res) {
                $scope.posts = res.data;
            },
            function (res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    $scope.addProduct = function (product) {
        $http({
            method: 'PUT',
            url: '/st-line/order/product/',
            data: angular.toJson($scope.product),
            headers: {
                'Content-Type': 'application/json'
            },
        }).then(_error);
    };

    $scope.getProductById = function () {
        var userId = location.search.split('productId=')[1];
        $http({
            method: 'GET',
            url: '/st-line/products/' + productId
        }).then(
            function (res) {
                $scope.product = res.data;
            },
            function (res) {
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    $scope.createProduct = function () {
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
        if (status == 500) {
            alert("Enter nick");
        }
    }

    function _clearFormData() {
        $scope.product.id = "";
        $scope.product.name = "";
    };

    // $scope.category;
    // $scope.categories = [];
    //
    // $scope.submitCategory = function () {
    //     var method = "POST";
    //     var url = '/st-line/productsFun/createCategory';
    //     $http({
    //         method: method,
    //         url: url,
    //         data: angular.toJson($scope.category.name),
    //         headers: {
    //             'Content-Type': 'application/json'
    //         }
    //     }).then(_success, _error);
    // };
    //
    // $scope.deleteCategory = function (category) {
    //     $http({
    //         method: 'DELETE',
    //         url: '/st-line/productsFun/categories/' + category.id
    //     }).then(_refreshCategoryData, _error);
    // };
    //
    // function _refreshCategoryData() {
    //     $http({
    //         method: 'GET',
    //         url: '/st-line/productsFun/categories'
    //     }).then(
    //         function (res) {
    //             $scope.categories = res.data;
    //         },
    //         function (res) {
    //             console.log("Error: " + res.status + " : " + res.data);
    //         }
    //     );
    // }
    //
    // function _cleanCategoryData() {
    //     $http({
    //         method: 'GET',
    //         url: '/st-line/productsFun/categories'
    //     }).then(
    //         function (res) {
    //             $scope.categories = res.data;
    //         },
    //         function (res) {
    //             console.log("Error: " + res.status + " : " + res.data);
    //         }
    //     );
    // }
    //
    // $scope.mark;
    // $scope.marks = [];
    //
    // $scope.submitMark = function () {
    //     var method = "POST";
    //     var url = '/st-line/productsFun/createMark';
    //     $http({
    //         method: method,
    //         url: url,
    //         data: angular.toJson($scope.mark),
    //         headers: {
    //             'Content-Type': 'application/json'
    //         }
    //     }).then(_success, _error);
    // };
    //
    // $scope.deleteCategory = function (mark) {
    //     $http({
    //         method: 'DELETE',
    //         url: '/st-line/productsFun/marks/' + mark.id
    //     }).then(_refreshCategoryData, _error);
    // };
    //
    // function _refreshCategoryData() {
    //     $http({
    //         method: 'GET',
    //         url: '/st-line/productsFun/marks'
    //     }).then(
    //         function (res) {
    //             $scope.marks = res.data;
    //         },
    //         function (res) {
    //             console.log("Error: " + res.status + " : " + res.data);
    //         }
    //     );
    // }
    //
    // function _cleanCategoryData() {
    //     $http({
    //         method: 'GET',
    //         url: '/st-line/productsFun/marks'
    //     }).then(
    //         function (res) {
    //             $scope.marks = res.data;
    //         },
    //         function (res) {
    //             console.log("Error: " + res.status + " : " + res.data);
    //         }
    //     );
    // }
});


// app.controller("MarkController", function($scope, $http) {
//     $scope.mark;
//     $scope.marks = [];
//
//     $scope.submitMark = function() {
//         var method = "POST";
//         var url = '/st-line/productsFun/createMark';
//         $http({
//             method: method,
//             url: url,
//             data: angular.toJson($scope.mark),
//             headers: {
//                 'Content-Type': 'application/json'
//             }
//         }).then(_success, _error);
//     };
//
//     $scope.deleteCategory = function(mark) {
//         $http({
//             method: 'DELETE',
//             url: '/st-line/productsFun/marks/' + mark.id
//         }).then(_refreshCategoryData, _error);
//     };
//
//     function _refreshCategoryData() {
//         $http({
//             method: 'GET',
//             url: '/st-line/productsFun/marks'
//         }).then(
//             function(res) {
//                 $scope.marks = res.data;
//             },
//             function(res) {
//                 console.log("Error: " + res.status + " : " + res.data);
//             }
//         );
//     }
//
//     function _cleanCategoryData() {
//         $http({
//             method: 'GET',
//             url: '/st-line/productsFun/marks'
//         }).then(
//             function(res) {
//                 $scope.marks = res.data;
//             },
//             function(res) {
//                 console.log("Error: " + res.status + " : " + res.data);
//             }
//         );
//     }
//
//
//     $scope.createMark = function() {
//         _clearFormData();
//     }
//
//     function _success(res) {
//         _clearFormData();
//     }
//
//     function _error(res) {
//         var data = res.data;
//         var status = res.status;
//         var header = res.header;
//         var config = res.config;
//         //alert("Error: " + status + ":" + data);
//         if(status==500){
//             alert("Opps..");
//         }
//     }
//
//     function _clearFormData() {
//         $scope.mark.id = "";
//         $scope.mark.name = "";
//     };
// });