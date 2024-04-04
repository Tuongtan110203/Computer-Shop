<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>View Profile</title>
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
            .viewUser{
                margin-right: 30px;
            }
            .changpass{
                margin-left: 30px;
            }
            .viewProfile{
                margin-left: 80px;
            }
            .profile-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.6); 
            text-align: center; 
        }
        .profile-container ul {
            list-style: none;
            padding: 0;
        }
        .profile-container ul li {
            margin-bottom: 10px;
        }
      
        </style>
    </head>
    <body>
        
   <div class="container mt-5">
    <div class="row justify-content-center viewProfile">
        <div class="col-md-6">
            <div class="profile-container">
                <h1>View Profile</h1>
                <ul>
                    <li><strong>Username:</strong> ${sessionScope.loginUsername}</li>
                    <li><strong>Password:</strong> ${sessionScope.passwordUsername}</li>
                    <li><strong>isAdmin:</strong> ${sessionScope.isAdminUsername}</li>
                </ul>
                <c:if test="${sessionScope.isAdminUsername==true}">
                    <p><a href="DashBoard" class="btn btn-primary">Back to Page Admin</a></p>
                </c:if>
                <c:if test="${sessionScope.isAdminUsername==false}">
                    <p><a href="user" class="btn btn-primary" id="backShop">Back To Continue Shopping</a></p>
                </c:if>
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
