angular.module('productApp', []).controller('indexController', function ($scope, $http) {
    const contextPath = "http://localhost:8080/products"
    $scope.loadProductList = function () {
        $http.get(contextPath).then(function (response) {
            $scope.ProductList = response.data;
        });
    };
    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + "/delete/" + productId)
            .then(function (response) {
                $scope.loadProductList();
            });
    }
    $scope.changeCost = function (productId, delta) {
        $http({
            method: 'GET',
            url: contextPath + '/change_price',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProductList();
        })
        // $http.get(contextPath + '/change_price',{params})
    }
    $scope.loadProductList()
});

let app = angular.module('postserviceApp', []);
app.controller('postserviceCtrl', function ($scope, $http) {
    const contextPath = "http://localhost:8080/products"

    $scope.postData = function (product) {
        let p = {
            id: product.id,
            name: product.name,
            cost: product.cost
        };
//Call the services
        let pr = JSON.stringify(p);
        $http({
            method: 'POST',
            url: contextPath + '/addproduct',
            data: pr,
            // headers: {'Access-Control-Allow-Origin': 'http://127.0.0.1:8080'}
        });
    };
});
// async function changeCost(id, delta){
//     alert(id + " " + delta)
//     let product = {
//         productId: id,
//         delta: delta
//     }
//     let response = await fetch(contextPath + '/change_price', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json;charset=utf-8'
//         },
//         body: JSON.stringify(product)
//     });
//     let result = await response.json();
// }
