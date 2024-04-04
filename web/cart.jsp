<%@page import="java.util.List"%>
<%@page import="tuongnt.cart.CartDAO"%>
<%@page import="tuongnt.orders.OrdersDAO"%>
<%@page import="tuongnt.orders.OrdersDTO"%>
<%@page import="tuongnt.orderDetails.OrderDetailsDAO"%>
<%@page import="tuongnt.orderDetails.OrderDetailsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tuongnt.products.ProductDTO" %> 
<%@ page import="tuongnt.products.ProductDAO" %> 
<%@ page import="tuongnt.cart.CartDTO" %>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.DecimalFormat" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <meta charset="UTF-8">
        <title>Cart</title>
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
            .order-details {
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1), 0 1px 3px rgba(0, 0, 0, 0.08);
                padding: 20px;
                margin-top: 0px;
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
        <div class="container" style="margin-top: 147px">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="user">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Cart</li>
                </ol>
            </nav>
        </div>   
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                CartDAO cartDAO = new CartDAO();
                                List<CartDTO> cartList = null;
                                try {
                                    int userId = (int) session.getAttribute("userId");
                                    cartList = cartDAO.selectCart(userId);
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                if (cartList != null) {
                                    ProductDAO productDAO = new ProductDAO();
                                    for (CartDTO item : cartList) {
                                        ProductDTO product = productDAO.getProductById(item.getProductId());
                                        if (product != null) {
                                            int remainingQuantity = product.getQuantity() - item.getQuantity();
                                            if (remainingQuantity < 0) {
                            %>
                            <tr>
                                <td style="color: red"><%= product.getName()%> is out of stock.</td>
                                <td><img src="<%= product.getImage()%>" alt="<%= product.getName()%>" width="100px"></td>
                                <td><%= product.getPrice()%></td>
                                <td><input type="text" name="quantity" value="<%= item.getQuantity()%>" style="width: 30px;" readonly></td>
                                <td>
                                    <form action="UpdateCartServlet" method="post">
                                        <input type="hidden" name="userId" value="${sessionScope.userId}">
                                        <input type="hidden" name="productId" value="<%= product.getId()%>">
                                        <button type="submit" name="action" value="decrease" class="btn btn-sm btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                            <% } else {%>
                            <tr>
                                <td><%= product.getName()%></td>
                                <td><img src="<%= product.getImage()%>" alt="<%= product.getName()%>" width="100px"></td>
                                <td><%= product.getPrice()%></td>
                                <td><input type="text" name="quantity" value="<%= item.getQuantity()%>" style="width: 30px;" readonly></td>
                                <td>
                                    <form action="UpdateCartServlet" method="post">
                                        <input type="hidden" name="userId" value="${sessionScope.userId}">
                                        <input type="hidden" name="productId" value="<%= product.getId()%>">
                                        <button type="submit" name="action" value="increase" class="btn btn-sm btn-primary">+</button>
                                        <button type="submit" name="action" value="decrease" class="btn btn-sm btn-danger">-</button>
                                    </form>
                                </td>
                            </tr>
                            <% } %>
                            <% } %>
                            <% } %>
                            <% } %>
                        </tbody>
                    </table>
                    <div class="my-5 d-flex justify-content-between flex-column flex-lg-row">
                        <a href="user" class="btn btn-link text-muted">
                            <i class="fa fa-chevron-left"></i> Continue shopping
                        </a>
                    </div>
                </div>

                <div class="col-md-4">
                    <%
                        int userId = (int) session.getAttribute("userId");
                        CartDAO cartDAO1 = new CartDAO();
                        ArrayList<CartDTO> cartList1 = cartDAO.getCartByUserId(userId);
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
                        <p>Name: <%= product.getName()%></p>
                        <p>Price: <%= product.getPrice()%></p>
                        <p>Quantity: <%= cartItem.getQuantity()%> </p>
                        <p>Total: <%= itemTotalMoney%> </p>
                    </div>
                    <%
                                }
                            }
                        }
                        DecimalFormat totalMoneyFormat = new DecimalFormat("#,###");
                        String formattedTotalMoneyAllProducts = totalMoneyFormat.format(totalMoneyAllProducts);
                    %>
                    <div class="order-details">
                        <p>Total Money: <%= formattedTotalMoneyAllProducts%></p>
                        <form action="checkout.jsp" method="post">
                            <input type="hidden" name="userId" value="<%= userId%>">
                            <button type="submit" class="btn btn-primary">Checkout</button>
                        </form>
                    </div>
                </div>


            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    </body>
</html>
