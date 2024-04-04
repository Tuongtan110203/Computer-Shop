<%@page import="tuongnt.cart.CartDAO"%>
<%@page import="tuongnt.cart.CartDTO"%>
<%@page import="tuongnt.cart.CartDTO"%>
<%@page import="tuongnt.orders.OrdersDTO"%>
<%@page import="tuongnt.orders.OrdersDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tuongnt.orderDetails.OrderDetailsDAO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="tuongnt.products.ProductDTO"%>
<%@page import="tuongnt.products.ProductDAO"%>
<%@page import="tuongnt.orderDetails.OrderDetailsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 
        <!-- Icon Font Stylesheet -->
        <script src="https://kit.fontawesome.com/d6052cb9ef.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <!-- Libraries Stylesheet -->
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <style>
            .btn-block {
                display: block;
                width: 100%;
            }
            .order-details {
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1), 0 1px 3px rgba(0, 0, 0, 0.08);
                padding: 10px;
                margin-top: -28px;
            }

            .order-item {
                padding: 10px;
                border-bottom: 1px solid #ddd;
            }

            .order-item:last-child {
                border-bottom: none;
            }
            .logout{
                margin-left: 22px;
            }

            .breadcrumb{
                background-color: white;

            }   
            .breadcrumb-item a {
                text-decoration: none;
            }

            .breadcrumb-item:hover a {
                color: red !important; /* Change link color to red on hover */
            }         
        </style>
    </head>
    <body>
        <div class="container-fluid fixed-top">
            <div class="container topbar bg-primary d-none d-lg-block">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">Nguyen Van Tang,TP Thu Duc</a></small>
                        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white">tuongnts171150@fpt.edu.vn</a></small>
                    </div>

                </div>
            </div>
            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl">
                    <a href="user" class="navbar-brand"><h1 class="text-primary display-6">Computer Shop</h1></a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">

                            <a href="user" class="nav-item nav-link active">Home</a>

                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Products</a>
                                <div class="dropdown-menu m-0 bg-secondary rounded-0">
                                    <a href="AllProductServlet" class="dropdown-item">All Product</a>
                                    <a href="mouseUser" class="dropdown-item">Mouse</a>
                                    <a href="headPhoneUser" class="dropdown-item">Head Phone</a>
                                    <a href="keyBoardUser" class="dropdown-item">Key Board</a>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex m-3 me-0">
                            <a href="cart.jsp" class="position-relative me-4 my-auto">
                                <i class="fa fa-shopping-bag fa-2x"></i>
                                <c:if test="${sessionScope.userTotalQuantityMap != null}">                         
                                    <c:choose>
                                        <c:when test="${sessionScope.userTotalQuantityMap[sessionScope.userId] > 0}">
                                            <span style="font-size: 16px;color: black">${sessionScope.userTotalQuantityMap[sessionScope.userId]}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <!--<span >0</span>-->
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </a>
                            <a href="view.jsp" class="my-auto">
                                <i class="fas fa-user fa-2x"></i>
                            </a>
                            <a href="LogoutServlet" class="my-auto logout">
                                <i class="fas fa-sign-out-alt fa-2x"></i>
                            </a>
                            <a href="changePasswordUser.jsp" class="my-auto logout">
                                <i class="fas fa-key fa-2x"></i>
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="h-100 rounded">
                        <iframe class="rounded w-100" style="height: 500px;"
                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d195943.65314041133!2d106.66017274914018!3d10.762622579402213!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175295f764d48b1%3A0x29a6d7d08c550d1!2sHo%20Chi%20Minh%20City%2C%20Vietnam!5e0!3m2!1sen!2sus!4v1644871665477!5m2!1sen!2sus"
                                loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>

                    </div>
                </div> 
            </div>
        </div>
        <div class="container" >
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="user">Home</a></li>
                    <li class="breadcrumb-item"><a href="cart.jsp">Cart</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Checkout</li>
                </ol>
            </nav>
        </div>                              

        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <form action="checkout" method="post">
                        Full Name <br/>
                        <input type="text" name="fullName" value="" required=""/><br/> 
                        Number Phone <br/>
                        <input type="number" name="phone" value="" required=""/><br/> 
                        Address <br/>
                        <input type="text" name="address" value="" required=""/><br/> 
                        Notice<br/>
                        <textarea name="notice" rows="3"></textarea><br/>
                        <input type="checkbox" name="agree" required=""/>I agree to the policies and terms of the Website. 
                        <hr>
                        <h4>Checkout</h4>
                        <input type="checkbox" name="paymentOnDelivery" required=""/>Payment on delivery   </br>   
                        Please enter your email to check out <input type="text" name="userEmail" required=""/>
                        <input class="btn btn-primary btn-lg btn-block" type="submit" value="CheckOut"/>
                    </form>
                </div>
                <div class="col-md-4" style="margin-top: -34px;">
                    <%
                        int userId = (int) session.getAttribute("userId");
                        CartDAO cartDAO = new CartDAO();
                        ArrayList<CartDTO> cartList = cartDAO.getCartByUserId(userId);
                        double totalMoneyAllProducts = 0;
                        if (cartList != null && !cartList.isEmpty()) {
                            for (CartDTO cartItem : cartList) {
                                ProductDAO productDAO = new ProductDAO();
                                ProductDTO product = productDAO.getProductById(cartItem.getProductId());
                                if (product != null) {
                                    double itemTotalMoney = cartItem.getQuantity() * product.getPrice();
                                    totalMoneyAllProducts += itemTotalMoney;
                    %>
                    <div class="order-item">
                        <div class="row">
                            <div class="col-md-3">
                                <img src="<%= product.getImage()%>" alt="<%= product.getName()%>" width="80px">
                            </div>
                            <div class="col-md-9">
                                <p>Name: <%= product.getName()%></p>
                                <p>Price: <%= product.getPrice()%></p>
                                <p>Quantity: <%= cartItem.getQuantity()%> </p>
                                <p>Total: <%= itemTotalMoney%> </p>
                            </div>
                        </div>
                    </div>
                    <%
                                }
                            }
                        }
                        DecimalFormat totalMoneyFormat = new DecimalFormat("#,###");
                        String formattedTotalMoneyAllProducts = totalMoneyFormat.format(totalMoneyAllProducts);
                    %>

                </div>
                <div class="col-md-2 order-details" style="width: 210px;height: 100px">
                    <h4><b>Total Money: <%= formattedTotalMoneyAllProducts%></b></h4>

                </div>

            </div>
        </div>

    </body>
</html>
