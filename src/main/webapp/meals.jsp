<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 24.03.2017
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Meal list</title>
    <style type="text/css">
        TD, TH {
            padding: 5px;
            text-align: center;
        }

        #ID {
            font-style: italic;
        }
    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>

<table border="1">
    <caption>Таблица калорий</caption>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Exceed</th>
        <th>ID</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <%--
               <c:choose>
                   <c:when test="${meal.exceed}">
                       <c:set var="color" value="red"/>
                   </c:when>
                   <c:otherwise>
                       <c:set var="color" value="green"/>
                   </c:otherwise>
               </c:choose>
       --%>
        <c:set var="color" value="${meal.exceed ? 'red' : 'green'}"/>
        <tr>
            <td>${fn:replace(meal.dateTime,"T" ," ")}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td bgcolor=${color}>${meal.exceed}</td>
            <td id="ID">${meal.id}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
