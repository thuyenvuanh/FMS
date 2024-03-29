<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 29/06/2022
  Time: 11:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from webapplayers.com/inspinia_admin-v2.9.4/404.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 06 Jun 2022 04:36:18 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>404 Error - FMS</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="middle-box text-center animated fadeInDown">
    <h1>404</h1>
    <h3 class="font-bold">Page Not Found</h3>

    <div class="error-desc">
        ${msg404}
        <% session.removeAttribute("msg404"); %>
        <br>
        <a type="submit" href="<c:url value="/"/>" class="btn btn-primary mt-5">Back</a>
        <a type="submit" href="<c:url value="/account/logout"/>" class="btn btn-primary mt-5">Clear Session</a>
    </div>
</div>

<!-- Mainly scripts -->
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.js"></script>

</body>


<!-- Mirrored from webapplayers.com/inspinia_admin-v2.9.4/404.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 06 Jun 2022 04:36:18 GMT -->
</html>

