<%--
  Created by IntelliJ IDEA.
  User: thuyn
  Date: 6/18/2022
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
    <link crossorigin="anonymous" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" rel="stylesheet">
</head>
<body class="container-fluid d-flex flex-column justify-content-center align-items-center h-100" style="background-color: #84f5b4">
    <div class="text-center text-white">
        <h1 class="fs-1"><%= response.getStatus() %></h1>
        <h1><%= request.getAttribute("javax.servlet.error.message") %></h1>
    </div>
    <div style="height: 10%;" class="text-white d-flex justify-content-center align-items-end">
        <a class="btn btn-link" href="<c:url value="/"/>">Return to homepage</a>
    </div>
</body>
</html>
