<%--
  Created by IntelliJ IDEA.
  User: mahsom
  Date: 9/22/17
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Username</th>
        <th>Password</th>
    </tr>
    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td><c:out value="${user.getId()}"/></td>
            <td><c:out value="${user.getUsername()}"/></td>
            <td><c:out value="${user.getPassword()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
