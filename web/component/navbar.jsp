<%-- 
    Document   : navbar
    Created on : Jan 14, 2021, 9:02:44 PM
    Author     : TRANTATDAT
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="Products">Golden Backpack Shop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">New</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Your cart
                        <c:if test="${sessionScope.listCart ne null}">
                            <span class="badge rounded-pill bg-danger"> ${sessionScope.totalProduct} </span> 
                        </c:if>
                    </a>
                    <c:choose>
                        <c:when test="${sessionScope.listCart eq null}">
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <li><p class="dropdown-item">No product in cart</p></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <c:forEach items="${sessionScope.listCart}" begin="0" end="2" var="cartProduct">
                                    <li>
                                        <div class="dropdown-item">
                                            <div class="card mb-3" style="max-width: 100%;">
                                                <div class="row g-0">
                                                    <div class="col-md-4">
                                                        <img class="card-img" style="object-fit: cover" src="assets/images/products/${cartProduct.imgName}" alt="Product image">
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="card-body" >
                                                            <p class="card-text text-wrap">                                           
                                                                <a href="product-detail?id=${cartProduct.id}" style="text-decoration: none; color: #000;">
                                                                    ${cartProduct.name}
                                                                </a>                                                        
                                                            </p>
                                                            <p class="card-text text-wrap">In cart: ${cartProduct.quantity}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>                                     
                                </c:forEach>                                            
                                <li><a class="dropdown-item" href="cart.jsp">See all products in cart</a></li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </li>    
                <c:if test="${sessionScope.authSuccess ne null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Hallo
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><p class="dropdown-item">Profile</p></li>
                            <li><p class="dropdown-item">Your order</p></li>
                            <li><p class="dropdown-item">Logout</p></li>
                        </ul>
                    </li>
                    
                </c:if>
                <c:if test="${sessionScope.authSuccess eq null}">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="login.jsp">Login</a>
                    </li>
                </c:if>
            </ul>


            <form class="d-flex" action="search" method="get">
                <input class="form-control me-2 mt-3" type="search" placeholder="Search" aria-label="Search" name="input-text">
                <button class="btn btn-outline-success mt-3" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
