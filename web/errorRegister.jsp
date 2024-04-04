<%-- 
    Document   : errorRegister
    Created on : Feb 4, 2024, 10:02:58 PM
    Author     : tuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Error</title>
        <script>
            alert("${sessionScope.errorMessage}");
            window.history.back();
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
