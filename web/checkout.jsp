<%-- 
    Document   : base
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

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <title>Checkout</title>
    </head>
    <body class="d-flex flex-column min-vh-100">
        
        <c:if test="${sessionScope.authSuccess==null}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>

        <%@include file="component/navbar.jsp"%>

        <div class="container mb-5">            
            <div class="row">
                <div class="col-md-7">
                    <h3 class="mt-5">List of product in your cart</h3>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th >Image</th>
                                <th >Product name</th>
                                <th >Price</th>
                                <th >Order quantity</th>
                                <th >Total cost</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.listCart}" var="cart">
                                <tr>
                                    <td><img src="assets/images/products/${cart.imgName}" width="60"></td>
                                    <td>${cart.name}</td>
                                    <td><fmt:formatNumber value="${cart.price}" type="currency" currencySymbol="₫" maxFractionDigits="0" /></td>
                                    <td>${cart.quantity}</td>
                                    <td><fmt:formatNumber value="${cart.price * cart.quantity}" type="currency" currencySymbol="₫" maxFractionDigits="0" /></td>        
                                </tr>
                            </c:forEach>                            
                        </tbody>
                    </table>
                    <div class="text-end">
                        <b class="me-4">Total sum: <fmt:formatNumber value="${sessionScope.totalMoney}" type="currency" currencySymbol="₫" maxFractionDigits="0" /></b>                      
                    </div>
                    <div>
                        <a href="remove-cart?id=0" class="btn btn-lg btn-danger me-2 mt-4 w-100">Cancel</a>
                    </div>
                </div>
                <div class="col-md-5 ps-5">
                    <h3 class="mt-5">Your information: </h3>
                    <div>
                        <form action="check-out" method="get">
                            <div class="mb-3">
                                <label for="name" class="form-label">Your Name:</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="e.g: Nguyen Thi A">
                            </div>
                            <div class="mb-3">
                                <label for="mobile" class="form-label">Your Phone Pumber:</label>
                                <input type="text" class="form-control" id="mobile" name="mobile" placeholder="e.g: 0969123456">
                            </div>
                            <div class="mb-3">
                                <label for="address" class="form-label">Your Address:</label>
                                <textarea class="form-control" id="address" rows="2" name="address"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="note" class="form-label">Note:</label>
                                <textarea class="form-control" id="note" rows="2" name="note"></textarea>
                            </div>
                            <button type="submit" class="btn btn-lg btn-success me-2 w-100 mt-3">Confirm</button>
                        </form>
                    </div>                   
                </div>
            </div>
        </div>

        <%@include file="component/footer.jsp" %>
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>