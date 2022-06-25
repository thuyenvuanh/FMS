<%--
    Document   : index
    Created on : May 31, 2022, 8:32:52 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link crossorigin="anonymous" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" rel="stylesheet">
    <style>
        body {
            height: 100vh;
        }

        section {
            width: 600px;
            height: 60vh;
            min-height: 400px;
            max-height: 600px;
        }

        header > img {
            min-height: 100px;
            max-height: 300px;
            width: 30vw;
            min-width: 150px;
            max-width: 450px;
        }

        #link-button {
            color: #4fc284 !important;
        }

        @media screen and (max-width: 736px) {
            section {
                width: 90vw;
            }
        }
        @media screen and (min-width: 1200px) {
            .login-section {
                width: 50% !important;
            }
        }
        @media screen and (max-width: 1200px) {
            .login-section {
                width: 80% !important;
            }
        }
        @media screen and (max-width: 900px) {
            .login-section {
                width: 90% !important;
            }
        }
        .login-section {
            width: 95%;
        }
    </style>
</head>

<body style="background-color:#84f5b4;" class="container-fluid">
<div class="row d-flex flex-wrap align-items-md-center align-items-stretch h-100 pb-md-auto pb-4">
    <div class="d-flex col-md-6 pe-md-5 justify-content-md-end col-12 px-auto justify-content-center">
        <header class="mt-5 mr-1 text-center">
            <img src="<c:url value="/images/Background.jpg"/>" class="rounded-3"
                 style="width: 80%; height: auto; object-fit: contain"
                 alt="Store logo"/>
        </header>
    </div>
    <div class="col-md-6 align-items-md-center justify-content-md-start col-12 justify-content-center d-flex">
        <div class="bg-light shadow-lg rounded-3 p-4 d-flex align-items-start login-section" style="min-height: 500px;">
            <form method="post" class="w-100">
                <p class="fw-bold fs-1 text-center mb-5 mt-3">SIGN IN</p>
                <!--Username-->
                <div class="mb-3">
                    <label for="loginUsername" class="form-label">Username</label>
                    <input type="text" class="form-control" id="loginUsername" placeholder="Enter username"
                           name="username"
                           required/>
                </div>
                <!-- Password input -->
                <div class="mb-3">
                    <label for="loginPassword" class="form-label">Password</label>
                    <input type="password" class="form-control" id="loginPassword" placeholder="Enter password"
                           name="password" required>
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
                    <button type="submit" class="btn mx-auto shadow" formaction="<c:url
        value="/account/login"/>" style="background-color: #4fc284; color: #ffffff; width: 98%">Sign in
                    </button>
                    <span class="text-danger">${sessionScope.message}</span>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

</html>

<%
    request.getSession().removeAttribute("message");
%>