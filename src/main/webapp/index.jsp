<%-- 
    Document   : index
    Created on : May 31, 2022, 8:32:52 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link crossorigin="anonymous" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
</head>

<body style="background-color:#84f5b4" class="container-fluid">

<header class="text-center my-5">
    <img src="images/Background.jpg" class="rounded-3" width="200px" alt="Store logo">
</header>
<div>
    <section class="shadow bg-light mx-auto px-5 py-1 rounded-4">
        <form action="" method="post">
            <p class="fw-bold fs-1 text-center mt-3">SIGN IN</p>
            <!--Username-->
            <div class="mb-3">
                <label for="loginUsername" class="form-label">Username</label>
                <input type="text" class="form-control" id="loginUsername" placeholder="Enter username" name="username" required>
            </div>
            <!-- Password input -->
            <div class="mb-3">
                <label for="loginPassword" class="form-label">Password</label>
                <input type="password" class="form-control" id="loginPassword" placeholder="Enter password" name="password" required>
            </div>
            <!-- 2 column grid layout -->
            <div class="row mb-3">
                <div class="d-flex justify-content-center">
                    <!-- Checkbox -->
                    <div class="form-check mb-3 mb-md-0 was-validated">
                        <input class="form-check-input" type="checkbox" id="loginCheck" name="remember"/>
                        <label for="loginCheck" class="form-check-label">Remember me</label>
                    </div>
                </div>
            </div>
            <!-- Submit button -->
            <div class="row px-2">
                <button type="submit" class="btn mb-3 shadow" style="background-color: #4fc284; color: #ffffff">Sign in</button>
                <span class="text-danger"></span>
            </div>
        </form>
        <!-- Forgot password-->
        <div class="d-flex justify-content-center">
            <button type="submit" id="link-button" class="btn btn-link text-center">Forgot password?</button>
        </div>
    </section>
</div>

<footer></footer>
</body>

</html>