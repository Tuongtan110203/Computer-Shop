<%@page import="tuongnt.orders.OrdersDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <meta charset="UTF-8">
        <title>Customer Information</title>
        <!-- Bootstrap CSS -->
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
                    <a href="index.html" class="navbar-brand"><h1 class="text-primary display-6">Computer Shop</h1></a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">

                            <a href="user" class="nav-item nav-link active">Home</a>
                            <!--                            <a href="shop.html" class="nav-item nav-link">Shop</a>
                                                        <a href="shop-detail.html" class="nav-item nav-link">Shop Detail</a>-->                        
                            <a href="contact.html" class="nav-item nav-link">Contact</a>
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
                    <li class="breadcrumb-item"><a href="cart.jsp">Cart</a></li>
                    <li class="breadcrumb-item"><a href="checkout.jsp">Checkout</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Imformation</li>
                </ol>
            </nav>
        </div>                              
        <div class="container" >
            <h1>Customer Information</h1>
            <!-- Hiển thị thông tin khách hàng -->
            <p><strong>Full Name:</strong> ${fullName}</p>
            <p><strong>Address:</strong> ${address}</p>
            <p><strong>Phone:</strong> ${phone}</p>
            <hr>
            <h1><strong>STK: 0363319463</strong></h1>
            <h5><strong>Bank:</strong> MB Bank</h5>
            <h5><strong>Branch:</strong> TP.HCM</h5>
            <h5><strong>Account Owner:</strong> Nguyen Tan Tuong</h5>
            <hr>
            <h1><strong>HOTLINE: 0363319463</strong></h1>
            <hr>
            <a href="user" class="btn btn-primary">Continue Shopping</a>
        </div>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    </body>
</html>
