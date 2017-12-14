<%--
  Created by IntelliJ IDEA.
  User: alexresh
  Date: 13.12.2017
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <td>id</td>
        <td>model</td>
        <td>price</td>
    </tr>
<c:forEach items="${phones}" var="phone">
    <tr>
        <td>${phone.getId()}</td>
        <td>${phone.getModel()}</td>
        <td>${phone.getPrice()}</td>
    </tr>
    </c:forEach>

</body>
</html>

