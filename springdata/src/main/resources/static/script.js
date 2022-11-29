const contextPath = "http://localhost:8080/app/products";
const rowsOfPage = 5;
let app = angular.module('productApp', []);
app.controller('indexController', function ($scope, $http) {
    let pageVar = 0;
    $scope.loadProductList = function () {
        $http.get(contextPath).then(function (response) {
            $scope.ProductList = response.data;
        });
    };
    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + "/" + productId)
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
    }
    $scope.submit = function () {
        let p = {
            title: $scope.product.title,
            price: $scope.product.price
        }
        $http.post(contextPath, p)
            .then(function (response) {
                    $scope.loadProductList();
                    $scope.product.title = "";
                    $scope.product.price = "";
                }
            )
    }

    $scope.getAndLoadPage = function (rowsNumber, page) {
        $scope.pageVar = pageVar;
        $http({
            method: 'GET',
            url: contextPath + "/list",
            params: {
                rowsNumber: rowsNumber,
                page: page
            }
        }).then(function (response) {
            $scope.ProductList = response.data;
        })
    }

    $scope.prevPage = function (){
        if (pageVar > 0){
            pageVar = pageVar - 1;
        }
        $scope.getAndLoadPage(rowsOfPage,pageVar);
    }
    $scope.nextPage = function () {
        pageVar = pageVar + 1;
        $scope.getAndLoadPage(rowsOfPage,pageVar);
    }

    $scope.getAndLoadPage(rowsOfPage,pageVar);
});
