<%-- 
    Document   : cart2
    Created on : Mar 2, 2021, 8:20:31 PM
    Author     : TRANTATDAT
--%>

<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page errorPage="error.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">        

        <script src="https://kit.fontawesome.com/9507fb7f76.js" crossorigin="anonymous"></script>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <title>Your Cart</title>
    </head>
    <body class="d-flex flex-column min-vh-100">      

        <%@include file="component/navbar.jsp"%>

        <c:if test="${sessionScope.listCart eq null}">
            <div class="container mb-5">
                <div class="row">
                    <div class="col-md-12 text-center mt-5">
                        <h4>Your cart is empty</h4>
                        <a href="Products" class="btn btn-outline-primary mt-3">Return</a>
                    </div>
                </div>
            </div>            
        </c:if>
        <c:if test="${sessionScope.listCart ne null}">
            <div class="container">            
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="mt-5">Your Cart</h3>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th >Image</th>
                                    <th >Product name haha hoho</th>
                                    <th >Price</th>
                                    <th >Order quantity</th>
                                    <th >Total cost</th>
                                    <th >Manage</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.listCart}" var="cart">
                                    <tr>
                                        <td><img src="assets/images/products/${cart.imgName}" width="60"></td>
                                        <td>${cart.name}</td>
                                        <td><fmt:formatNumber value="${cart.price}" type="currency" currencySymbol="₫" maxFractionDigits="0" /></td>
                                        <td>
                                            <div class="d-flex align-items-center">
                                                <c:choose>
                                                    <c:when test="${cart.quantity eq 1}">
                                                        <a href="remove-cart?id=${cart.id}" class="me-3" style="text-decoration: none">
                                                            <button type="button" class="btn btn-outline-secondary btn-sm"><span class="fas fa-minus"></span></button>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="update-cart?flag=1&id=${cart.id}" class="me-3" style="text-decoration: none">
                                                            <button type="button" class="btn btn-outline-secondary btn-sm"><span class="fas fa-minus"></span></button>
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
                                                <span>${cart.quantity}</span>
                                                <c:forEach items="${sessionScope.lsProductInStore}" var="product">
                                                    <c:if test="${product.id eq cart.id}">
                                                        <c:choose>
                                                            <c:when test="${product.quantity eq cart.quantity}">
                                                                <button type="button" class="btn btn-outline-secondary btn-sm ms-3" disabled data-bs-toggle="button" autocomplete="off"><span class="fas fa-plus"></span></button>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="update-cart?flag=2&id=${cart.id}" class="ms-3" style="text-decoration: none">
                                                                    <button type="button" class="btn btn-outline-secondary btn-sm"><span class="fas fa-plus"></span></button>
                                                                </a> 
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </td>
                                        <td><fmt:formatNumber value="${cart.price * cart.quantity}" type="currency" currencySymbol="₫" maxFractionDigits="0" /></td>
                                        <td><a href="remove-cart?id=${cart.id}">Remove</a></td>
                                    </tr>
                                </c:forEach>                            
                            </tbody>
                        </table>

                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <a href="Products" class="btn btn-outline-primary">Return</a>
                    </div>
                    <div class="col-md-6 text-end">
                        <b class="me-4">Total sum: <fmt:formatNumber value="${sessionScope.totalMoney}" type="currency" currencySymbol="₫" maxFractionDigits="0" /></b>
                        <a href="remove-cart?id=0" class="btn btn-outline-danger me-2">Remove all products mama meme</a>
                        <a href="checkout" class="btn btn-outline-success">Check out</a>
                    </div>
                </div>
            </div>
        </c:if>



        <%@include file="component/footer.jsp" %>




        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>