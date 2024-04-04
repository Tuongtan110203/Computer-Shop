<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
            }

            h1 {
                text-align: center;
                color: #333;
            }

            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 300px;
            }

            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 10px;
                margin: 8px 0;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            input[type="submit"],
            input[type="reset"] {
                background-color: #4caf50;
                color: white;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type="submit"]:hover,
            input[type="reset"]:hover {
                background-color: red;
            }
            span{
                color: red
            }
        </style>

    </head>
    <body>
        <h1><b>Registration Page</b></h1>
        <form action="DispatchServlet" method="post" id="form" onsubmit="return validateForm()">
            Username: <input type="text" name="txtUsername" value="" /><br>
            Password: <input type="password" name="txtPassword" value="" /><br/>
            Confirm password:<input type="password" name="confirmPass"/></br>
            LastName: <input type="text" name="lastname" value=""/><br/>
            IsAdmin: <input type="checkbox" name="isAdmin" value="true" /><br/>

            <div class="g-recaptcha" data-sitekey="6Lc74ZQpAAAAAK8j9eFSooDCiscBtCfqSbih6_Df"></div>
            <div id="error" style="color: red"></div>
            <input type="submit" name="btSubmit" value="Register"/>
            <input type="reset" value="Reset"/></br>
            <a href="LogoutServlet">Back Login</a>
        </form>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            function validateForm() {
                var response = grecaptcha.getResponse();
                if(response.length == 0) {
                    document.getElementById('error').innerText = "Please complete the captcha.";
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>
