<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo.webp" type="image/x-icon">
        <meta charset="UTF-8">
        <title>Password Change Error</title>

        <script>
            alert("${sessionScope.errorMessage}");
            window.history.back();
        </script>
    </head>
    <body>

    </body>
</html>
