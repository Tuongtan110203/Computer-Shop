<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password User Page</title>
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
            .viewUser{
                margin-right: 30px;
            }
            .changpass{
                margin-left: 30px;
            }
        </style>
    </head>
    <body>
       


        <!-- Navbar start -->
        <!--<div class="container-fluid fixed-top">-->
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
                                <i class="fas fa-user fa-2x viewUser" ></i>
                            </a>
                            <a href="LogoutServlet" class="my-auto logout">
                                <i class="fas fa-sign-out-alt fa-2x"></i>
                            </a>
                            <a href="changePasswordUser.jsp" class="my-auto logout">
                                <i class="fas fa-key fa-2x changpass"></i>
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <h5 class="card-header bg-primary text-white">Change Password User Form</h5>
                        <div class="card-body">
                            <form action="changeUser" method="post">
                                <div class="form-group">
                                    <label for="oldPass">Old Password:</label>
                                    <input type="password" class="form-control" id="oldPass" name="oldPass">
                                </div>
                                <input type="hidden" name="user" value="${sessionScope.loginUsername}">
                                <div class="form-group">
                                    <label for="newPass">New Password:</label>
                                    <input type="password" class="form-control" id="newPass" name="newPass">
                                </div>
                                <div class="form-group">
                                    <label for="confirmPass">Confirm Password:</label>
                                    <input type="password" class="form-control" id="confirmPass" name="confirmPass">
                                </div>
                                <button type="submit" class="btn btn-primary">Change</button>
                                <button type="reset" class="btn btn-secondary">Reset</button>
                            </form>
                        </div>
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
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    </body>
</html>
