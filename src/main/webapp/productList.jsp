<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Product list</h1>

<c:set var="time" value="<%= new java.util.Date()%>"></c:set>
${time}
${sessionScope.name}
</body>
</html>
