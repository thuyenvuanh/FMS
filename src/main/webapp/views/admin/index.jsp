<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thuyn
  Date: 6/14/2022
  Time: 11:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin HomePage</title>
</head>
<body>
    <h1>Welcome to Admin HomePage</h1>
    <c:url var="Logout" value="${requestScope.contextPath}/auth/logout"/>
    <a href="${Logout}">Log out</a>
</body>
</html>
