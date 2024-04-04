<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Update for Admin</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }

            form {
                max-width: 500px;
                margin: 20px auto;
                background: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
                color: red;
            }

            input[type="text"],
            input[type="number"],
            input[type="datetime-local"],
            textarea,
            input[type="file"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                background-color: #4caf50;
                color: white;
                padding: 10px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            .error {
                color: red;
                margin-top: -15px;
                margin-bottom: 15px;
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
                text-align: right;
            }
            #hover2{
                color: white;
            }
            #hover2:hover{
                color: red;
            }
        </style>

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <h3><a href="admin" id="home">Home</a></h3>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">

                    <li class="nav-item active">
                        <a class="nav-link" href="view.jsp" id="hover2">View Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="changePassword.jsp" id="hover2">Change Password</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LogoutServlet" id="hover2">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <h1>Hello Admin: ${sessionScope.loginUsername}</h1>
        <h1 style="text-align: center; color: red;">Update for Admin</h1>
        <form action="update" method="post">
            <c:set var="product" value="${requestScope.plist}" />
            ID:<input type="text" name="id" readonly="" value="${product.id}" /><br/>      
            Name<input type="text" id="name" name="name" value="${product.name}" required/><br/>
            Quantity<input type="number" id="quantity" name="quantity" value="${product.quantity}" required min="1"/><br/>
            Price<input type="number" step="0.01" id="price" name="price" value="${product.price}" required min="1"/><br/>
            ReleaseDate<input type="text" name="releasedate" value="${product.releaseDate}" required pattern="\d{4}-\d{2}-\d{2}" placeholder="YYYY-MM-DD"/><br/>
            Describe<textarea  id="describe" name="describe" required>${product.describe}</textarea><br/>
            Image<input type="text" id="image" name="image" value="${product.image}" required/><br/>
            <input type="submit" value="Update Product"/>
        </form>
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
