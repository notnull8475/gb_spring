const contextPath = "http://localhost:8080/api/v1";
const rowsOfPage = 5;
let app = angular.module('productApp', []);
app.controller('indexController', function ($scope, $http) {
    let pageVar = 0;
    let allPages = 0;
    $scope.loadProductList = function () {

        $http({
            url: contextPath + "/products",
            method: 'GET',
            params: {
                rows: rowsOfPage,
                page: pageVar,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                title_part: $scope.filter ? $scope.filter.title : null,
            }
        }).then(function (response) {
            $scope.pageVar = pageVar + 1;
            $scope.allPages = response.data.totalPages;
            allPages = response.data.totalPages;
            if (pageVar >= allPages) {
                pageVar = allPages - 1;
                $scope.loadProductList();
            }
            $scope.ProductList = response.data.content;
            $scope.loadCartList();
        })

    }

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + "/products/" + productId)
            .then(function (response) {
                $scope.loadProductList();
            });
    }
    $scope.changeCost = function (productId, delta) {
        $http({
            method: 'GET',
            url: contextPath + '/products/change_price',
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
        $http.post(contextPath + "/products", p)
            .then(function (response) {
                    $scope.loadProductList();
                    $scope.product.title = "";
                    $scope.product.price = "";
                }
            )
    }


    $scope.prevPage = function () {
        if (pageVar > 0) {
            pageVar = pageVar - 1;
        }
        $scope.loadProductList(rowsOfPage, pageVar);
    }
    $scope.nextPage = function () {
        if (pageVar < allPages) {
            pageVar = pageVar + 1;
        }
        $scope.loadProductList(rowsOfPage, pageVar);
    }

    $scope.loadCartList = function () {
        $http.get(contextPath + "/carts").then(function (response) {
            $scope.CartProductList = response.data;
        })
    }
    $scope.deleteProductFromCount = function (productId) {
        $http({
            url:contextPath + "/carts/remove",
            method: "DELETE",
            params:{
                productId:productId
            }
        }).then(function (response) {
            $scope.loadCartList();
        })
    }
    $scope.changeCountInCart = function (productId, delta) {
        $http({
            url: contextPath + "/carts/change",
            method: "GET",
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCartList();
        })
    }

    $scope.addToCart = function (productID) {
        $http({
            url: contextPath + "/carts/add",
            method: "GET",
            params: {
                id: productID
            }
        }).then(function (response) {
            $scope.loadCartList();
        })
    }


    $scope.loadProductList(rowsOfPage, pageVar);
});
