<%-- 
    Document   : index
    Created on : Jan 11, 2021, 8:40:25 PM
    Author     : TRANTATDAT
--%>

<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page errorPage="error.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">        
        <title>BaloShop</title>
        <style>
            .filter {
                display: block;
                text-decoration: none;
                color: #000;
                text-transform: uppercase;
                font-weight: 500;
            }

            a:hover {
                color: orange !important;
            }
        </style>
    </head>
    <body class="d-flex flex-column min-vh-100">    
        <%@include file="component/navbar.jsp"%>

        <div class="container mt-5 mb-5">
            <c:if test="${requestScope.pageIndex eq 0}">
                <div class="row">
                    <div class="col-md-12" style="flex-direction: column;">
                        <h2 style="text-align: center">New Arrivals</h2>
                        <div class="card-group">
                            <c:forEach items="${requestScope.lsNewProduct}" var="newProduct">
                                <div class="card d-flex align-items-center">
                                    <img src="assets/images/products/${newProduct.imgName}" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">
                                            <a href="product-detail?id=${newProduct.id}" style="text-decoration: none; color: #000;">${newProduct.name}</a>
                                        </h5>                                   
                                        <p class="card-text">
                                            <fmt:formatNumber value="${newProduct.price}" groupingUsed="true" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                        </p>                       
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-2">
                    <div class="accordion mt-4" id="filter">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingOne">
                                <button class="accordion-button" style="font-weight: 500;" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Brands
                                </button>
                            </h2>
                            <div id="collapseOne" class="accordion-collapse " aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    <ul class="list-group list-group-flush">
                                        <c:forEach items="${sessionScope.lsBrand}" var="brand">
                                            <a class="filter mt-3" href="filter?id=${brand.id}&type=1" style="display: block;">${brand.name}</a>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingTwo">
                                <button class="accordion-button" style="font-weight: 500;" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Categories
                                </button>
                            </h2>
                            <div id="collapseTwo" class="accordion-collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                    <ul class="list-group list-group-flush">
                                        <c:forEach items="${sessionScope.lsCategory}" var="category">
                                            <a class="filter mt-3" href="filter?id=${category.id}&type=2" style="display: block;">${category.name}</a>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>                      
                </div>

                <div class="col-md-10">
                    <div class="order mt-4">
                        <p style="display: inline-block; font-weight: bold"> Order all product by: </p>
                        <div class="dropdown" style="display: inline-block">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false" style="background-color: white; color: #000">
                                Order options
                            </button>
                            <c:choose>
                                <c:when test="${requestScope.pageIndex eq 0}">
                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                        <li><a class="dropdown-item" href="Products?flag=${1}">Increasing price</a></li>
                                        <li><a class="dropdown-item" href="Products?flag=${2}">Decreasing price</a></li>                               
                                    </ul>
                                </c:when>
                                <c:when test="${requestScope.pageIndex eq 1}">
                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                        <li><a class="dropdown-item" href="filter?flag=${1}&id=${requestScope.id}&type=${requestScope.type}">Increasing price</a></li>
                                        <li><a class="dropdown-item" href="filter?flag=${2}&id=${requestScope.id}&type=${requestScope.type}">Decreasing price</a></li>                               
                                    </ul>
                                </c:when>
                                <c:otherwise>                                    
                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                        <li><a class="dropdown-item" href="search?flag=${1}&input-text=${requestScope.text}">Increasing price</a></li>
                                        <li><a class="dropdown-item" href="search?flag=${2}&input-text=${requestScope.text}">Decreasing price</a></li>                               
                                    </ul>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="row">                       
                        <c:choose>
                            <c:when test="${requestScope.flagOrder eq 1}">
                                <c:forEach items="${requestScope.lsProductIncreasePrice}" var="product">
                                    <div class="col-md-4 mt-4">
                                        <div class="card">
                                            <img src="assets/images/products/${product.imgName}" class="card-img-top" alt="Product image" width="400" height="400">
                                            <div class="card-body">
                                                <h5 class="card-title">
                                                    <!-- Khi lam viec voi link truyen vao ?ten_bien -->
                                                    <!-- Truyen nhieu bien ?id=1 & name="xxx" &... -->
                                                    <!-- truyen bao nhieu bien get bay nhieu lan ben servlet-->
                                                    <a href="product-detail?id=${product.id}" style="text-decoration: none; color: #000;">
                                                        ${product.name}
                                                    </a>
                                                </h5>
                                                <p>
                                                    <fmt:formatNumber value="${product.price}" groupingUsed="true" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                                </p>
                                                <a href="cart?id=${product.id}" class="btn btn-primary">Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:when test="${requestScope.flagOrder eq 2}">
                                <c:forEach items="${requestScope.lsProductDecreasePrice}" var="product">
                                    <div class="col-md-4 mt-4">
                                        <div class="card">
                                            <img src="assets/images/products/${product.imgName}" class="card-img-top" alt="Product image" width="400" height="400">
                                            <div class="card-body">
                                                <h5 class="card-title">
                                                    <!-- Khi lam viec voi link truyen vao ?ten_bien -->
                                                    <!-- Truyen nhieu bien ?id=1 & name="xxx" &... -->
                                                    <!-- truyen bao nhieu bien get bay nhieu lan ben servlet-->
                                                    <a href="product-detail?id=${product.id}" style="text-decoration: none; color: #000;">
                                                        ${product.name}
                                                    </a>
                                                </h5>
                                                <p>
                                                    <fmt:formatNumber value="${product.price}" groupingUsed="true" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                                </p>
                                                <a href="cart?id=${product.id}" class="btn btn-primary" onclick="">Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise> 
                                <c:forEach items="${requestScope.lsProduct}" var="product">
                                    <div class="col-md-4 mt-4">
                                        <div class="card">
                                            <img src="assets/images/products/${product.imgName}" class="card-img-top" alt="Product image" width="400" height="400">
                                            <div class="card-body">
                                                <h5 class="card-title">
                                                    <!-- Khi lam viec voi link truyen vao ?ten_bien -->
                                                    <!-- Truyen nhieu bien ?id=1 & name="xxx" &... -->
                                                    <!-- truyen bao nhieu bien get bay nhieu lan ben servlet-->
                                                    <a href="product-detail?id=${product.id}" style="text-decoration: none; color: #000;">
                                                        ${product.name}
                                                    </a>
                                                </h5>
                                                <p>
                                                    <fmt:formatNumber value="${product.price}" groupingUsed="true" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                                </p>
                                                <c:if test="${product.quantity ne 0}">
                                                    <a href="cart?id=${product.id}" class="btn btn-primary">Add to cart</a>
                                                </c:if>
                                                <c:if test="${product.quantity eq 0}">
                                                    <p class="text-muted">Not available</p>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

        </div>
        <%@include file="component/footer.jsp" %>

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>
