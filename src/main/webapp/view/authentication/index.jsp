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
    <title>Sign in - FMS</title>
    <link rel="icon" href="<c:url value="/images/Background.png"/>">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link crossorigin="anonymous" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" rel="stylesheet">
    <style>
        body {
            height: 100vh;
        }

        body{
            -webkit-user-select: none;
            -khtml-user-select: none;
            -moz-user-select: none;
            -o-user-select: none;
            user-select: none;
            -webkit-user-drag: none;
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
        /* Breakpoint xxl */
        @media screen and (min-width: 1400px) {
            img[alt = 'Store logo'] {
                margin-right: 100px;
            }
            .sign-in-section{
                width: 600px;
                height: 600px;
            }
            header{
                width: 100%;
            }
        }

        /* Break point xl*/
        @media screen and (max-width: 1400px) and (min-width: 1200px){
            .sign-in-section{
                width: 450px;
                height: 600px;
            }
            header{
                width: 100%;
            }
        }

        /* Breakpoint lg */
        @media screen and (max-width: 1200px) and (min-width: 992px){
            .sign-in-section{
                width: 95%;
                height: 600px;
            }
            header{
                width: 100%;
            }
        }

        @media screen and (max-width: 992px) {
            .left-screen{
                display: none !important;
            }
            .right-screen {
            }
            .sign-in-section{
                height: 600px;
                width: 600px;
            }
        }
    </style>
</head>

<body style="background-color:#84f5b4;" class="container-fluid">
<div class="row d-flex flex-wrap h-100">
    <div class="left-screen d-flex px-xl-0 px-5
                align-items-lg-center
                justify-content-lg-end
                col-lg-6
                ">
        <header class="d-flex
                        justify-content-xl-end justify-content-lg-center
                        px-xl-5">
            <img src="<c:url value="/images/Background1.png"/>" class="rounded-3"
                 style="height: 100%; object-fit: cover;"
                 alt="Store logo"/>
        </header>
    </div>
    <div class="right-screen d-flex
                align-items-center
                justify-content-xxl-start justify-content-center
                col-lg-6
                px-xl-5
                ">
        <div class="d-flex flex-column
                    justify-content-start
                    pt-5
                    align-items-center
                    px-4 px-md-5 sign-in-section bg-white shadow-lg rounded-3"
             style="min-height: 500px;">
            <img src="<c:url value="/images/Background.png"/>" class="rounded-3" height="100px" />
            <form method="post" class="w-100">
                <p class="fw-bold fs-1 text-center">SIGN IN</p>
                <!--Username-->
                <div class="mb-3">
                    <label for="loginUsername" class="form-label">Username</label>
                    <input type="text" class="form-control" id="loginUsername" placeholder="Enter username"
                           name="username" value="store" autofocus
                           required/>
                </div>
                <!-- Password input -->
                <div class="mb-3">
                    <label for="loginPassword" class="form-label">Password</label>
                    <input type="password" class="form-control" id="loginPassword" placeholder="Enter password"
                           name="password" value="store" required>
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