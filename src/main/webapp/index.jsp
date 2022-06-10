<%-- 
    Document   : index
    Created on : May 31, 2022, 8:32:52 AM
    Author     : Admin
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Login</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        </head>

        <body style="background-color:#9EBF99">

        <header class="text-center" style="margin:30px auto;">
            <img src="image/Background.jpg" width="200px">
        </header>
        <div class="text-center">
            <section class="btn btn-light" style="position:relative; text-align:center;">
            <form action="AccountController" method="post">
            <section class="btn btn-light" style="position:relative; text-align:center; width: 400px;">
            <form>
                <p style="font-weight:bold;"> SIGN IN </p>
                <p class="text-center">
                    <!--Email-->
                <div class="form-outline mb-4" style="text-align:left;">
                    <p>Email: <input type="text" id="loginEmail" placeholder="Email" style="width:100%;"></p>
                </div>
                <!-- Password input -->
                <div class="form-outline mb-4" style="text-align:left;">
                    <p>Password: <input type="text" id="loginPassword" placeholder="Password" style="width:100%;"></p>
                </div>

                <!-- 2 column grid layout -->
                <div class="row mb-3">
                    <div class="d-flex justify-content-center">
                        <!-- Checkbox -->
                        <div class="form-check mb-3 mb-md-0">
                            <input class="form-check-input" type="checkbox" value="" id="loginCheck" />
                            <label for="loginCheck"> Remember me </label>
                        </div>
                    </div>

                </div>
                <!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>
                <!-- Forgot password-->
                <div class="d-flex justify-content-center" style="margin-bottom:20px;">
                    <!-- Simple link -->
                    <a href="#">Forgot password?</a>
                </div>
                <!-- Register buttons -->
                <div class="text-center">
                    <p>Not a member? <a href="#">Register</a></p>
                </div>
                </p>
            </form>
        </section>
        </div>

        <footer ></footer>
        </body>

        </html>