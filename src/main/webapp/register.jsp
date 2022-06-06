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
            <title>Register</title>
            <link rel="stylesheet" type="text/css" href="CSS/css/bootstrap.css" />
        </head>

        <body style="background-color:#9EBF99">

        <header class="text-center col-sm-4" style="margin-left:auto;margin-right:auto;">
            <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="tab-login" data-mdb-toggle="pill" href="index.jsp" role="tab"
                       aria-controls="pills-login" aria-selected="false">Login</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="tab-register" data-mdb-toggle="pill" href="register.jsp" role="tab"
                       aria-controls="pills-register" aria-selected="true">Register</a>
                </li>
            </ul>
        </header>
        <section><div class="text-center">
            <section class="btn btn-light" style="position:relative;">
            <!-- Register buttons -->
            <div class="text-center">
                <p style="font-weight:bold;"> REGISTER </p>
                <!-- Name input -->
                <div class="form-outline mb-4 flex-fill" style="text-align:left;">
                    <p>Name: <input type="text" id="registerName" placeholder="Name"/></p>
                </div>

                <!-- Username input -->
                <div class="form-outline mb-4" style="text-align:left;">
                    <p>Username: <input type="text" id="registerUsername" placeholder="Username"/></p>
                </div>

                <!-- Password input -->
                <div class="form-outline mb-4" style="text-align:left;">
                    <p>Password: <input type="password" id="registerPassword" placeholder="Password"/></p>
                </div>

                <!-- Repeat Password input -->
                <div class="form-outline mb-4" style="text-align:left;">
                    <p>Repeat Password: <input type="password" id="registerRepeatPassword" placeholder="Password"/></p>
                </div>

                <!-- Checkbox -->
                <div class="form-check d-flex justify-content-center mb-4">
                    <input class="form-check-input me-2" type="checkbox" value="" id="registerCheck"
                           aria-describedby="registerCheckHelpText" />
                    <label class="form-check-label" for="registerCheck">
                        I have read and agree to the terms
                    </label>
                </div>

                <!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-block mb-3">Sign in</button>
                </form>
                <p>Already a member? <a href="index.jsp">Login now</a></p>
            </div>
            </section></div>
        </section>

        <footer></footer>
        </body>

        </html>