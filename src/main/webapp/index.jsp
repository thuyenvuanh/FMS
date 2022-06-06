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
            <link rel="stylesheet" type="text/css" href="CSS/css/bootstrap.css" />
        </head>

        <body style="background-color:#9EBF99">

        <header class="text-center col-sm-4" style="margin-left:auto;margin-right:auto;">
            <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="tab-login" data-mdb-toggle="pill" href="index.jsp" role="tab"
                       aria-controls="pills-login" aria-selected="true" >Login</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="tab-register" data-mdb-toggle="pill" href="register.jsp" role="tab"
                       aria-controls="pills-register" aria-selected="false">Register</a>
                </li>
            </ul>
        </header>
        <div class="text-center">
            <section class="btn btn-light" style="position:relative; text-align:center;">
            <form>
                <p style="font-weight:bold;"> SIGN IN </p>
                <p class="text-center">
                    <!--Username-->
                <div class="form-outline mb-4">
                    <p>Username: <input type="text" id="loginName" placeholder="Username"></p>
                </div>
                <!-- Password input -->
                <div class="form-outline mb-4">
                    <p>Password: <input type="text" id="loginPassword" placeholder="Password"></p>
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

                    <div class="d-flex justify-content-center">
                        <!-- Simple link -->
                        <a href="register.jsp">Forgot password?</a>
                    </div>

                </div>
                <!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>
                <!-- Register buttons -->
                <div class="text-center">
                    <p>Not a member? <a href="register.jsp">Register</a></p>
                </div>
                </p>
            </form>
        </section>
        </div>

        <footer></footer>
        </body>

        </html>