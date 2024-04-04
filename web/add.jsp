<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <title>ADD PRODUCT PAGE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">    
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <style>
            .text-secondary{
                color: orange!important;
            }

            #menu .nav-link:hover {
                color: red;
            }
            .py-3{padding-top: 0rem!important;
                  padding-bottom: 0rem!important}
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


                document.getElementById('id').addEventListener('input', function () {
                    var idValue = this.value;
                    var categoryIdInput = document.getElementById('cid');

                    if (idValue === 'B') {
                        categoryIdInput.value = 3;
                        categoryIdInput.setAttribute('readonly', true);
                    } else {
                        categoryIdInput.removeAttribute('readonly');
                        categoryIdInput.value = ""; // Xóa giá trị nếu ID không phải 'B'
                    }
                });


            </script>
        </head>
        <body>
            <div class="container-fluid">
            <div class="row flex-nowrap">
                <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
                    <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                        <a href="DashBoard" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                            <span class="fs-5 d-none d-sm-inline">Menu</span>
                        </a>
                        <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                            <li class="nav-item">
                                <a href="DashBoard" class="nav-link align-middle px-0">
                                    <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Home</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="DashBoard" class="nav-link align-middle px-0">
                                    <i class="fs-4 bi-speedometer2"></i> <span class="ms-1 d-none d-sm-inline">Dashboard</span> </a>
                                </a>
                            </li>

                            <li>
                                <a href="#submenu3" data-bs-toggle="collapse" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-grid"></i> <span class="ms-1 d-none d-sm-inline">Orders</span> </a>
                                <ul class="collapse nav flex-column ms-1" id="submenu3" data-bs-parent="#menu">
                                    <li class="w-100">
                                        <a href="ManagementOrderServlet" class="nav-link px-0"> <span class="d-none d-sm-inline">Management Order</span> </a>
                                    </li>
                                    <li  class="w-100">
                                        <a href="ManagementRevenueServlet" class="nav-link px-0"> <span class="d-none d-sm-inline">Management revenue </span> </a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#submenu1" data-bs-toggle="collapse" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-grid"></i> <span class="ms-1 d-none d-sm-inline">Products</span> </a>
                                <ul class="collapse nav flex-column ms-1" id="submenu1" data-bs-parent="#menu">
                                    <li class="w-100">
                                        <a href="add.jsp" class="nav-link px-0"> <span class="d-none d-sm-inline">Add New Product</span> </a>
                                    </li>
                                    <li>
                                        <a href="AllProductAdmin" class="nav-link px-0"> <span class="d-none d-sm-inline">All Product</span> </a>
                                    </li>
                                    <li>
                                        <a href="mouse" class="nav-link px-0"> <span class="d-none d-sm-inline">Mouse</span> </a>
                                    </li>
                                    <li>
                                        <a href="keyBoard" class="nav-link px-0"> <span class="d-none d-sm-inline">Key Board</span> </a>
                                    </li>
                                    <li>
                                        <a href="headPhone" class="nav-link px-0"> <span class="d-none d-sm-inline">Head Phone</span> </a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#submenu2" data-bs-toggle="collapse" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-grid"></i> <span class="ms-1 d-none d-sm-inline">User</span> </a>
                                <ul class="collapse nav flex-column ms-1" id="submenu2" data-bs-parent="#menu">
                                    <li class="w-100">
                                        <a href="ViewProfileUserServlet" class="nav-link px-0"> <span class="d-none d-sm-inline">View Profile User</span> </a>
                                    </li>                                
                                </ul>
                            </li>

                        </ul>
                        <hr>
                         <div class="dropdown pb-4">
                            <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="images/tuong.jpg" alt="hugenerd" width="30" height="30" class="rounded-circle">
                                <span class="d-none d-sm-inline mx-1"> ${sessionScope.loginUsername}</span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">

                                <li><a class="dropdown-item" href="changePassword.jsp">Change password</a></li>
                                <li><a class="dropdown-item" href="view.jsp">Profile</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="LogoutServlet">Sign out</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col py-3" >
                    <div class="row justify-content-center">
                        <div class="col-md-6">
                            <form action="DispatchServlet" method="post">
                                <div class="mb-3">
                                    ID:<input type="text" class="form-control" id="id" name="id"  required >
                                    <span class="error" style="color: red">${requestScope.idError}</span>
                                </div> 
                                <div class="mb-3">
                                    Name:<input type="text" class="form-control" id="name" name="name" required>
                                </div>
                                <div class="mb-3">
                                    Quantity:<input type="number" class="form-control" id="quantity" name="quantity" required min="0">
                                </div>
                                <div class="mb-3">
                                    Price:<input type="number" class="form-control" id="price" name="price" required min="1" step="0.01">
                                </div>
                                <div class="mb-3">
                                    Release Date:<input type="text" class="form-control" id="releasedate" name="releasedate" required pattern="\d{4}-\d{2}-\d{2}" placeholder="YYYY-MM-DD">
                                </div>
                                <div class="mb-3">
                                    Describe:<textarea class="form-control" id="describe" name="describe" required></textarea>
                                </div>
                                <div class="mb-3">
                                    Image:<input type="text" class="form-control" id="image" name="image" required>
                                </div>
                                <div class="mb-3">
                                    Category ID:<input type="number" class="form-control" id="cid" name="categoryId" required min="1" max="3" required  >
                                </div>
                                <div class="mb-3">
                                    <input type="submit" name="btSubmit" value="Add Product" class="btn btn-primary">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
