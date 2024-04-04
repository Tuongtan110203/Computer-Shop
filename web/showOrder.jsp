    <%@page import="tuongnt.orders.OrdersDAO"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>


    <!DOCTYPE html>
    <html>
        <head>
            <link rel="icon" href="images/logo.webp" type="image/x-icon">
            <meta charset="UTF-8">
            <title>Customer Information</title>
            <!-- Bootstrap CSS -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
                  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
                  crossorigin="anonymous">
            <style>

                body {
                    font-family: Arial, sans-serif;
                    background-color: #f8f9fa;
                    padding: 20px; 
                }
                h1 {
                    color: #007bff; 
                }
                h5 {
                    color: #6c757d;
                }
                .btn-primary {
                    background-color: #007bff; 
                    border-color: #007bff; 
                }
                .btn-primary:hover {
                    background-color: #0056b3; 
                    border-color: #0056b3; 
                }
                .footer {
                    background-color: #f8f9fa; 
                    padding: 20px 0;  
                    text-align: center; 
                }

                .footer p {
                    margin-bottom: 5px; 
                }

            </style>
        </head>
        <body>


            <div class="container">
                <h1>Customer Information</h1>
                <!-- Hiển thị thông tin khách hàng -->
                <p><strong>Full Name:</strong> ${fullName}</p>
                <p><strong>Address:</strong> ${address}</p>
                <p><strong>Phone:</strong> ${phone}</p>
                <hr>
                <h1><strong>STK: 0363319463</strong></h1>
                <h5><strong>Bank:</strong> MB Bank</h5>
                <h5><strong>Branch:</strong> TP.HCM</h5>
                <h5><strong>Account Owner:</strong> Nguyen Tan Tuong</h5>
                <hr>
                <h1><strong>HOTLINE: 0363319463</strong></h1>
                <hr>
                <a href="user" class="btn btn-primary">Continue Shopping</a>
            </div>
            <div class="container">
                <footer class="footer mt-auto py-3">
                    <div class="row">
                        <div class="col-md-12">
                            <h5 class="text-center">Thông tin liên hệ</h5>
                            <p class="text-center">Email: tuongntse171150@fpt.edu.vn</p>
                            <p class="text-center">Điện thoại: 0865429351</p>
                            <p class="text-center"><a href="https://www.facebook.com/tantuong.03/">Liên hệ(Facebook)</a></p>
                        </div>
                    </div>
                </footer>
            </div>

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
