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
            <td>id</td>v
            <td>name</td>
        </tr>
        <c:forEach items="${objectTypes}" var="objectType">
        <tr>
            <td>${objectType.getId()}</td>
            <td>${objectType.getName()}</td>
        </tr>
        </c:forEach>
</body>
</html>

