<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset Password</title>
        <link rel="stylesheet" href="style.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #f5f5f5;
            }

            .container {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h2 {
                margin-top: 0;
                margin-bottom: 20px;
            }

            form {
                width: 300px;
            }

            label {
                display: block;
                margin-bottom: 5px;
            }

            input[type="password"] {
                width: calc(100% - 12px);
                padding: 6px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            input[type="submit"] {
                width: 100%;
                padding: 8px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #0056b3;
            }

            /* Responsive */
            @media (max-width: 600px) {
                .container {
                    width: 90%;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Reset Password</h2>
            <form action="ResetPasswordServlet" method="post">
                New Password:<input type="password" id="password" name="password" required>
                Confirm New Password:<input type="password" id="confirmPassword" name="confirmPassword" required>
                <input type="submit" value="Reset Password">
            </form>
        </div>
    </body>
</html>
