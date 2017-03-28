<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 24.03.2017
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal list</title>
    <style type="text/css">
        #exceed {
            background: green;
        }
    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>

<table border="1">
    <caption>Таблица калорий</caption>
    <tr>
        <th> DateTime </th>
        <th> Description </th>
        <th> Calories </th>
        <th> Exceed </th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <c:if test="${meal.exceed}">
            <c:out value="${red}"/>
        </c:if>
        <tr>
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td id="exceed">${meal.exceed}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
