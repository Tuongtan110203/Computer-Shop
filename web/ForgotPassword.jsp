<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Forgot Password Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .container {
                margin-top: 50px;
                width: 50%;
                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
                padding: 20px;
                border-radius: 10px;
            }
            h2 {
                margin-bottom: 20px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            #sendOTP:hover{color: red}
            #verifyOTP:hover{color: red}
        </style>
    </head>
    <body>
        <div class="container">

            <h2>Forgot Password</h2>
            <form id="forgotPasswordForm" action="ForgotPasswordServlet" method="post">
                <div class="form-group">
                    Enter Username:<input type="text" class="form-control" id="username" name="username" required value="${sessionScope.username}">
                </div>
                <div class="form-group">
                    Enter Your Email:<input type="email" class="form-control" id="email" name="email" required value="${sessionScope.email}">
                </div>
                <button id="sendOTP" type="submit" class="btn btn-primary">Send OTP</button>
            </form>
            <form action="VerifyOTPServlet" method="post">
                <div class="form-group">
                    Enter OTP:<input type="text" class="form-control" id="otp" name="otp" required>
                </div>
                <button id="verifyOTP" type="submit" class="btn btn-success">Verify OTP</button>
            </form>
            <div style="color: red; margin-top: 20px;font: 40px">
                <h5>${requestScope.message}</h5>
                <h5>${requestScope.error}</h5>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
