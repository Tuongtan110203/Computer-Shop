<%@page import="tuongnt.products.ProductDTO"%>
<%@page import="tuongnt.products.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="content-language" content="vi">
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <title>Computer Shop</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <!-- Google Web Fonts -->
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
            .pt-5{
                padding: 0rem!important;
            }
            .py-5{
                padding-top: 0rem!important;
                padding-bottom: 0rem!important;
            }
            #hwimg{
                width: 229px;
                height: 284px;
            }
            #product{
                font-size: 50px;
                margin-bottom: 15px;
                margin-top: 20px;
                color: orange;
            }
            .best{
                margin-top: 15px;
                color: orange;
            }
            .p-4{
                padding: 0.5rem !important;
            }
            .logout{
                margin-left: 22px;
            }
            .col-xl-3{
                margin-top: 50px;
            }
            .marginProduct{
                margin-top: -80px;
            }
            #loadMore{
                margin-top: 10px;
                border-radius: 2px;
            }
            .btn-load-more:hover {
                background-color: red; 
                transform: scale(1.1)
            }
            #add{
                margin-left: 70px;
            }
            #viewDetails{
                margin-left: 7px;
            }
            .addToCart{
                margin-left: 70px;
            }
        </style>
        <script>
            function loadMore() {
                var amount = document.getElementsByClassName("product").length;
                $.ajax({
                    url: "/Shopping_2_12_1_2/LoadMoreControl",
                    type: "get",
                    data: {
                        exist: amount
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML += data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }
            function searchByName(param) {
                var txtSearch = param.value;
                $.ajax({
                    url: "/Shopping_2_12_1_2/SearchByAjax",
                    type: "get",
                    data: {
                        txt: txtSearch
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }
            function fetchProductsByPrice() {
                var rangeValue = document.getElementById("rangeInput").value;
                var formattedRangeValue = new Intl.NumberFormat('vi-VN').format(rangeValue);
                document.getElementById("amount").innerHTML = formattedRangeValue + " VND";
                $.ajax({
                    url: "/Shopping_2_12_1_2/FetchProductsByPriceServlet",
                    type: "GET",
                    data: {
                        price: rangeValue
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                        // Handle errors here
                    }
                });
            }
            function addToCart(productId) {
                var userId = "${sessionScope.userId}";
                $.ajax({
                    url: "/Shopping_2_12_1_2/AddToCartServlet",
                    type: "POST",
                    data: {
                        productId: productId,
                        userId: userId
                    },
                    success: function (data) {
                        var cartQuantitySpan = document.querySelector("#userTotalQuantityMapContainer .cart-quantity");
                        cartQuantitySpan.innerHTML = data;
                    },
                    error: function (xhr) {
                        console.error("Error adding product to cart:", xhr.statusText);
                    }
                });
            }
        </script>
    </head>

    <body>

        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar start -->
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
                        <div id="userTotalQuantityMapContainer" class="d-flex m-3 me-0">
                            <a href="cart.jsp" class="position-relative me-4 my-auto">
                                <i class="fa fa-shopping-bag fa-2x"></i>                              
                                <span class="badge rounded-pill bg-primary position-absolute top-0 start-100 translate-middle cart-quantity">
                                    <c:if test="${sessionScope.userTotalQuantityMap != null || sessionScope.cartItemCount!=null}">                         
                                        <c:choose>
                                            <c:when test="${sessionScope.userTotalQuantityMap[sessionScope.userId] > 0}">
                                                <span style="font-size: 16px;color: black">${sessionScope.userTotalQuantityMap[sessionScope.userId]}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span style="font-size: 16px;color: black">${sessionScope.cartItemCount}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>

                                </span>
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
        <!-- Navbar End -->



        <!-- Hero Start -->
        <div class="container-fluid py-5 mb-5 hero-header">
            <div class="container py-5">
                <div class="row g-5 align-items-center">
                    <div class="col-md-12 col-lg-7">
                        <h4 class="mb-3 text-secondary">100% real product</h4>
                        <h1 class="mb-5 display-3 text-primary">Imported from abroad and high quality</h1>
                        <div class="position-relative mx-auto">
                            <form action="SearchUser" method="post">
                                <input oninput="searchByName(this)" class="form-control border-2 border-secondary w-75 py-3 px-4 rounded-pill" type="text" name="search" placeholder="Search">
                                <button type="submit" class="btn btn-primary border-2 border-secondary py-3 px-4 position-absolute rounded-pill text-white h-100" style="top: 0; right: 25%;">Submit Now</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-5">
                        <div id="carouselId" class="carousel slide position-relative" data-bs-ride="carousel" >
                            <div class="carousel-inner" role="listbox">
                                <div class="carousel-item active rounded">
                                    <img src="images/Banphim/BAKKO3080.jpg" class="img-fluid w-100 h-100 bg-secondary rounded" alt="First slide">
                                    <a href="#" class="btn px-4 py-2 text-white rounded">Key Board AKKO3080</a>
                                </div>

                                <div class="carousel-item rounded">
                                    <img src="images/Tainghe/TG933.jpg" class="img-fluid w-100 h-100 rounded" alt="Second slide">
                                    <a href="#" class="btn px-4 py-2 text-white rounded">Head Phone Logitech933</a>
                                </div>
                                <div class="carousel-item rounded">
                                    <img src="images/chuot/CG103.jpg" class="img-fluid w-100 h-100 rounded" alt="Third slide">
                                    <a href="#" class="btn px-4 py-2 text-white rounded">Mouse Logitech103</a>
                                </div>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselId" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselId" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Hero End -->


        <!-- Featurs Section Start -->
        <div class="container-fluid featurs py-5">
            <div class="container py-5">
                <div class="row g-4">
                    <div class="col-md-6 col-lg-3">
                        <div class="featurs-item text-center rounded bg-light p-4">
                            <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                                <i class="fas fa-car-side fa-3x text-white"></i>
                            </div>
                            <div class="featurs-content text-center">
                                <h5>Free Shipping</h5>
                                <p class="mb-0">Free on order over 300.000VND</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <div class="featurs-item text-center rounded bg-light p-4">
                            <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                                <i class="fas fa-user-shield fa-3x text-white"></i>
                            </div>
                            <div class="featurs-content text-center">
                                <h5>Security Payment</h5>
                                <p class="mb-0">100% security payment</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <div class="featurs-item text-center rounded bg-light p-4">
                            <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                                <i class="fas fa-exchange-alt fa-3x text-white"></i>
                            </div>
                            <div class="featurs-content text-center">
                                <h5>30 Day Return</h5>
                                <p class="mb-0">30 day money guarantee</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <div class="featurs-item text-center rounded bg-light p-4">
                            <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                                <i class="fa fa-phone-alt fa-3x text-white"></i>
                            </div>
                            <div class="featurs-content text-center">
                                <h5>24/7 Support</h5>
                                <p class="mb-0">Support every time fast</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Featurs Section End -->


        <!-- Fruits Shop Start-->
        <div class="container-fluid fruite py-5">
            <div class="container py-5">
                <div class="row g-4">
                    <div class="col-lg-12">
                        <div class="row g-4">
                            <div class="col-xl-3">
                                <form action="SearchUser" method="post">
                                    <div class="input-group w-100 mx-auto d-flex" style="margin-top: -5px;">
                                        <input oninput="searchByName(this)" type="text" name="search" class="form-control p-3"  placeholder="keywords" aria-describedby="search-icon-1">
                                        <button type="submit" class="btn btn-secondary btn-number">
                                            <i class="fa fa-search"></i>                                        
                                        </button>
                                    </div>
                                </form>

                            </div>
                            <div class="col-6"></div>
                        </div>
                        <div class="row g-4">
                            <div class="col-lg-3">
                                <div class="row g-4">
                                    <div class="col-lg-11">
                                        <div class="mb-3">
                                            <h4 style="margin-top: 22px;">Categories</h4>
                                            <ul class="list-unstyled fruite-categorie">
                                                <li>
                                                    <div class="d-flex justify-content-between fruite-name">
                                                        <a href="mouseUser"><i class="fas fa-mouse me-2"></i>Mouse</a>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="d-flex justify-content-between fruite-name">
                                                        <a href="keyBoardUser"><i class="fas fa-keyboard me-2"></i>Key Board</a>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="d-flex justify-content-between fruite-name">
                                                        <a href="headPhoneUser"><i class="fas fa-headphones me-2"></i>Head Phone</a>
                                                    </div>
                                                </li>

                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-lg-11" id="priceRangeDiv">
                                        <div class="mb-3">
                                            <h4 class="mb-2">Price</h4>
                                            <input type="range" class="form-range w-100" id="rangeInput" name="rangeInput" min="0" max="5000000" value="0" oninput="fetchProductsByPrice()">
                                            <output id="amount" name="amount" min-value="0" max-value="5000000" for="rangeInput">0</output>                                

                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="col-lg-9">
                                <div id="content" class="row g-4 justify-content-center marginProduct">
                                    <c:forEach var="product" items="${sessionScope.listProductByUser}">
                                        <div class="product col-md-6 col-lg-4 col-xl-4">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img id="hwimg" src="${product.image}" class="img-fluid w-100 rounded-top" alt="">
                                                    <c:if test="${product.category.id == 1}">
                                                        <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 0; left: 0;">Mouse</div>
                                                    </c:if>
                                                    <c:if test="${product.category.id == 2}">
                                                        <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 0; left: 0;">HeadPhone</div>
                                                    </c:if>
                                                    <c:if test="${product.category.id == 3}">
                                                        <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 0; left: 0;">KeyBoard</div>
                                                    </c:if>
                                                </div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4 class="text-center">${product.getName()}</h4> 
                                                    <div class="text-center">
                                                        <c:choose>
                                                            <c:when test="${product.quantity > 0}">
                                                                <span style="color: green;">(Còn hàng)</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span style="color: red;">(Hết hàng)</span>
                                                            </c:otherwise>
                                                        </c:choose><br>  
                                                        <p class="text-dark fs-5 fw-bold mb-0">
                                                            <fmt:formatNumber pattern="#,###">${product.price}</fmt:formatNumber> VND
                                                            </p>
                                                        </div>
                                                        <button type="button" class="btn border border-secondary rounded-pill px-3 text-primary addToCart" onclick="addToCart('${product.id}')"> 
                                                        <i class="fa fa-shopping-bag me-2 text-primary"></i>Add to Cart                           
                                                    </button>
                                                    <form id="add" action="productDetail" method="post">
                                                        <input type="hidden" name="userId" value="${sessionScope.userId}">
                                                        <input type="hidden" name="productId" value="${product.id}">
                                                        <a href="productDetail?id=${product.id}" class="btn border border-secondary rounded-pill px-3 text-primary" id="viewDetails">View Details</a>
                                                    </form>

                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div id="loadMore">
                                    <button onclick="loadMore()" class="btn btn-primary btn-load-more">Load More</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bestsaler Product Start -->
        <div class="container-fluid py-5">
            <div class="container py-5">
                <div class="text-center mx-auto mb-5" style="max-width: 700px;">
                    <h1 class="display-4 best">Bestseller Products</h1>
                </div>
                <div class="row g-4">
                    <!-- Iterate over the list of best seller products -->
                    <c:forEach var="bestSellerProduct" items="${listBestSeller}">
                        <div class="col-lg-6 col-xl-4">
                            <div class="p-4 rounded bg-light">
                                <div class="row align-items-center">
                                    <div class="col-6">
                                        <img src="${bestSellerProduct.image}" class="img-fluid rounded-circle w-100" alt="${bestSellerProduct.name}">
                                    </div>
                                    <div class="col-6">
                                        <a href="#" class="h5">${bestSellerProduct.name}</a>  
                                        <button type="button" class="btn border border-secondary rounded-pill px-3 text-primary" 
                                                onclick="addToCart('${bestSellerProduct.productId}')"> 
                                            <i class="fa fa-shopping-bag me-2 text-primary"></i>Add to Cart                           
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>



        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
            <div class="container py-5">
                <div class="pb-4 mb-4" style="border-bottom: 1px solid rgba(226, 175, 24, 0.5) ;">
                    <div class="row g-4">
                        <div class="col-lg-6 text-center">
                            <a href="#">
                                <h1 class="text-primary mb-0">Computer Shop</h1>
                            </a>
                        </div>

                        <div class="col-lg-6">
                            <div class="d-flex justify-content-end pt-3">
                                <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href="https://www.facebook.com/tantuong.03/"><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href="https://youtube.com/@user-jl6sn5ht6i?si=ATT9l4D15N8oCjFi"><i class="fab fa-youtube"></i></a>
                                <a class="btn btn-outline-secondary btn-md-square rounded-circle" href="https://www.instagram.com/tuongtan.03/"><i class="fab fa-linkedin-in"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row g-5"  style="text-align: center">
                    <div class="col-lg-12 col-md-12">
                        <div class="footer-item">
                            <h4 class="text-light mb-3">Contact</h4>
                            <p>Address: Nguyen Van Tang,TP.Thu Duc</p>
                            <p>Email: tuongntse171150@fpt.edu.vn</p>
                            <p>Phone: +0865429351</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>

