<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="en">
    <head
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Details</title>
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
        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <style>
            /* Custom CSS styles here */
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa; /* Màu nền của trang */
            }
            .footer {
                background-color: #343a40;
                color: white;
                text-align: center; /* Căn giữa nội dung của footer */
                padding: 20px 0; /* Thêm padding cho footer */
                position: fixed;
                bottom: 0;
                width: 100%;
            }
            .footer p {
                margin-bottom: 5px; /* Khoảng cách giữa các dòng trong footer */
            }
            .footer a {
                color: white; /* Màu của liên kết trong footer */
            }
            .footer a:hover {
                color: red; /* Màu khi di chuột qua liên kết trong footer */
            }
            #imgP{
                width: 500px;
                height: 500px;
            }
            #imgP:hover{
                transform: scale(1.03)
            }
            .logout{
                margin-left: 22px;
            }
            .breadcrumb{
                background-color: #f8f9fa;

            }   
            .breadcrumb-item a {
                text-decoration: none;
            }

            .breadcrumb-item:hover a {
                color: red !important; /* Change link color to red on hover */
            }
            #add{
                margin-left: 70px;
            }
            #hwimg:hover{
                transform: scale(1.2);
            }
            #hwimg{
                width: 229px;
                height: 284px;
            }
            .addToCart{
                margin-left: 65px;
            }
        </style>
        <script>
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
        <div class="container" style="margin-top: 147px">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="user">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">ViewDetails</li>
                </ol>
            </nav>
        </div> 
        <div class="container">
            <div class="row">
                <c:set var="product" value="${sessionScope.productDetails}"/>
                <div class="col-md-6">
                    <img  src="${product.image}" id="imgP" alt="${product.name}">
                </div>
                <div class="col-md-6">
                    <h3><strong></strong> ${product.name}</h3>
                    <p class=""><strong>Description:</strong> ${product.describe}</p>                
                    <h4 style="color: red"><strong></strong> <fmt:formatNumber pattern="#,###">${product.price}</fmt:formatNumber> VND</h4>
                    <button type="button" class="btn border border-secondary rounded-pill px-3 text-primary" onclick="addToCart('${product.id}')"> 
                        <i class="fa fa-shopping-bag me-2 text-primary"></i>Add to Cart                           
                    </button>                     
                    <a href="cart.jsp" class="btn btn-primary">View Cart</a>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <h1>Related Product</h1>
                <c:forEach var="pRelated" items="${sessionScope.listProductRelated}">
                    <div class="product col-md-6 col-lg-3 col-xl-3">
                        <div class="rounded position-relative fruite-item">
                            <div class="fruite-img">
                                <img id="hwimg" src="${pRelated.image}" class="img-fluid w-100 rounded-top" alt="">
                                <c:if test="${pRelated.category.id == 1}">
                                    <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 0; left: 0;">Mouse</div>
                                </c:if>
                                <c:if test="${pRelated.category.id == 2}">
                                    <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 0; left: 0;">HeadPhone</div>
                                </c:if>
                                <c:if test="${pRelated.category.id == 3}">
                                    <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 0; left: 0;">KeyBoard</div>
                                </c:if>
                            </div>
                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                <h4 class="text-center">${pRelated.getName()}</h4> 
                                <div class="text-center">
                                    <c:choose>
                                        <c:when test="${pRelated.quantity > 0}">
                                            <span style="color: green;">(Còn hàng)</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">(Hết hàng)</span>
                                        </c:otherwise>
                                    </c:choose><br>  
                                    <p class="text-dark fs-5 fw-bold mb-0">
                                        <fmt:formatNumber pattern="#,###">${pRelated.price}</fmt:formatNumber> VND
                                        </p>
                                    </div>
                                    <button type="button" class="btn border border-secondary rounded-pill px-3 text-primary addToCart" onclick="addToCart('${pRelated.id}')"> 
                                    <i class="fa fa-shopping-bag me-2 text-primary"></i>Add to Cart                           
                                </button>
                                <form id="add" action="productDetail" method="post">
                                    <input type="hidden" name="userId" value="${sessionScope.userId}">
                                    <input type="hidden" name="productId" value="${pRelated.id}">
                                    <a href="productDetail?id=${pRelated.id}" class="btn border border-secondary rounded-pill px-3 text-primary" id="viewDetails">View Details</a>
                                </form>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <script src="js/main.js"></script>
    </body>
</html>
