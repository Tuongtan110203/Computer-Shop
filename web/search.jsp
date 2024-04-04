<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN PAGE</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            body {
                margin-top: 130px;
            }

            .container {
                margin: 20px auto;
            }


            div.nav-links {
                text-align: right;
            }

            div.nav-links a {
                margin-right: 10px;
                color: #007bff; 
                text-decoration: none;
                font-weight: bold;
            }

            div.nav-links a:hover {
                color: red; 
            }

            form {
                margin-top: 20px;
            }


            button[type="submit"] {
                margin-left: -8px;
                background-color: orange;
                color: white;
                font-weight: bold;
                border-color: #007bff;
            }

            button[type="submit"]:hover {
                background-color: red;
                border-color: red;
            }


            .table thead th {
                background-color: #343a40;
                color: white;
            }


            .table tbody td {
                text-align: center;
            }
            #imgLogo {
                position: absolute;
                left: -120px;
            }


            #setform {
                position: relative;
                margin-left: 65px; 
                margin-bottom: -1px;
                display: flex;
                justify-content: center;
                margin-top: -17px;
                background-color: darkgrey!important;
                border: 1px solid #ccc;
            }
            #priceFields1{
                margin-left: -8px;
            }

            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                padding-top: 100px;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgb(0,0,0);
                background-color: rgba(0,0,0,0.9);
            }

            /* Modal content styles */
            .modal-content {
                margin: auto;
                display: block;
                width: 80%;
                max-width: 700px;
            }

            /* Close button styles */
            .close {
                color: #fff;
                position: absolute;
                top: 15px;
                right: 35px;
                font-size: 30px;
                font-weight: bold;
                cursor: pointer;
            }
            #hover1:hover{
                color: red;
            }
            #hover1{
                color: white;
                text-align: right;
            }
            #home:hover{
                color: red;
            }
            #home{
                text-decoration: none;
                color: white;

            }
            #hover2{
                color: white;
            }
            #hover2:hover{
                color: red;
            }
            .footer {
                background-color: darkgrey;
                color: white;
            }                   
            #footer{ 
                height: 170px;
                text-align: center;    

            }
            .footer h5{
                margin-top: 18px
            }
            #footer div p a:hover{
                color: red;
            }
            .NavAndSeach{
                margin-top: -25px;
                position: fixed;
                top: 42px;
                width: 100%;
                z-index: 999;
            }

            .container {
                margin-top: 100px;   
                z-index: 1;  
            }
            .dropdown-menu .dropdown-item {
                list-style-type: none; /* Loại bỏ dấu chấm */
            }
        </style>
        <script>
            function confirmDelete(productId) {
                var result = confirm("Are you sure you want to delete this product?");
                if (result) {
                    window.location.href = "delete?cid=" + productId;
                }
                return false;
            }
            function confirmUpdate(productId) {
                var result = confirm("Are you sure you want to update this product?");
                if (result) {
                    window.location.href = "update?cid=" + productId;
                }
                return false;
            }
            function displayToOption() {
                var searchOption = document.getElementById("searchOption");
                var priceFields = document.getElementById("priceFields");
                var priceFields1 = document.getElementById("priceFields1");
                var searchSub = document.getElementById("searchSub");

                if (searchOption.value === "price") {
                    priceFields.style.display = "block";
                    priceFields1.style.display = "block";
                    searchSub.style.display = "none";
                } else {
                    priceFields.style.display = "none";
                    priceFields1.style.display = "none";
                    searchSub.style.display = "block";
                }
            }


            function toggleSidebar() {
                var sidebar = document.querySelector('.sidebar');
                var menuIcon = document.getElementById('menuIcon');
                var closeIcon = document.getElementById('closeIcon');
                var Setform = document.getElementById('setform');

                if (sidebar.style.display === 'none' || sidebar.style.display === '') {
                    sidebar.style.display = 'block';
                    menuIcon.style.display = 'none';
                    closeIcon.style.display = 'block';
                    Setform.style.marginLeft = '147px';
                } else {
                    sidebar.style.display = 'none';
                    menuIcon.style.display = 'block';
                    closeIcon.style.display = 'none';
                    Setform.style.marginLeft = '0px';
                }
            }


        </script>
    </head>
    <body>
        <div class="NavAndSeach">
            <form class="form-inline" action="search" method="post" id="setform">

                <img src="images/clogo.jpg" alt="Logo" style="max-width: 71px; margin-left:  54px;" id="imgLogo">

                <!-- Các trường và nút tìm kiếm -->
                <select class="form-control" id="searchOption" name="searchOption" onchange="displayToOption()">
                    <option value="name">Search by Name</option>
                    <option value="id">Search by ID</option>
                    <option value="price">Search by Price</option>
                    <option value="releaseDate">Search by Release Date</option>
                </select>
                <input class="form-control mr-sm-2" type="text" id="searchSub" name="search" placeholder="Enter keyword">
                <input class="form-control mr-sm-2" type="text" id="priceFields" name="priceFrom" placeholder="pricefrom" style="display: none">
                <input class="form-control mr-sm-2" type="text" id="priceFields1" name="priceTo" placeholder="priceTo" style="display: none">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="background-color: darkgrey!important">
                <h3><a href="admin" id="home">Home</a></h3>&nbsp;&nbsp;&nbsp;
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" 
                           style="color: white;font-size: 22px; margin-top: -6px;">
                            Management Product
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                            <h4 class="dropdown-header">Admin Actions</h4>
                            <a class="dropdown-item" href="add.jsp">Add New Product</a>
                            <a class="dropdown-item" href="AllProductAdmin">ALL</a>
                            <a class="dropdown-item" href="mouse">Mouse</a>
                            <a class="dropdown-item" href="headPhone">HeadPhone</a>
                            <a class="dropdown-item" href="keyBoard">KeyBoard</a>

                            <div class="dropdown-divider"></div>
                        </div>
                    </li>
                </ul>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown " role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: white;font-size: 22px">
                                Management Account
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <h4 class="dropdown-header">Admin Actions</h4>
                                <a class="dropdown-item" href="view.jsp">View Profile</a>
                                <a class="dropdown-item" href="ViewProfileUserServlet">View Profile User</a>
                                <a class="dropdown-item" href="changePassword.jsp">Change Password</a>
                                <div class="dropdown-divider"></div>
                            </div>
                        </li>

                        <li class="nav-item">
                            <h4><a class="nav-link" href="LogoutServlet" id="home">Logout</a></h4>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <table class="table">
                        <thead>
                            <tr>
                                <th style="padding: 30px;background-color: darkgrey">Product ID</th>
                                <th style="padding: 30px;background-color: darkgrey">Name</th>
                                <th style="padding: 30px;background-color: darkgrey">Image</th>
                                <th style="padding: 30px;background-color: darkgrey">Quantity</th>
                                <th style="padding: 30px;background-color: darkgrey">Price</th>
                                <th style="padding: 30px;background-color: darkgrey">Release Date</th>
                                <th style="padding: 30px;background-color: darkgrey">Describe</th>
                                <th style="padding: 30px;background-color: darkgrey">Category ID</th>                               
                                <th style="padding: 30px;background-color: darkgrey">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" items="${sessionScope.searchList}">
                                <tr>
                                    <td>${product.id}</td>
                                    <td>${product.name}</td>
                                    <td><img src="${product.image}"  width="200px" height="200px" style="margin-top: -60px;padding: 55px"></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${product.quantity > 0}">
                                                <span style="color: green;">${product.quantity}(Còn hàng)</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span style="color: red;">${product.quantity}(Hết hàng)</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><fmt:formatNumber pattern="#,###">${product.price}</fmt:formatNumber> VND</td>
                                    <td>${product.releaseDate}</td>
                                    <td>${product.describe}</td>
                                    <td>${product.category.id}</td>

                                    <td>
                                        <a href="#" onclick="confirmUpdate('${product.id}')" class="btn btn-warning" style="padding: 9px">Update</a>
                                        <a href="#" onclick="confirmDelete('${product.id}')" class="btn btn-danger">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <footer class="footer" id="footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h5>Thông tin liên hệ</h5>
                        <p>Email: tuongntse171150@fpt.edu.vn</p>
                        <p>Điện thoại: 0865429351</p>
                        <p><a href="https://www.facebook.com/tantuong.03/">Liên hệ(Facebook)</a></p>
                    </div>

                </div>
            </div>
        </footer>

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
