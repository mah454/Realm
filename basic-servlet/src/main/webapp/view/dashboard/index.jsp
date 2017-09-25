<%--
  Created by IntelliJ IDEA.
  User: mahsom
  Date: 9/22/17
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<ul>
    <li><a href="<c:url value="/dashboard"/>">Dashboard</a></li>
    <li><a href="<c:url value="/user-manager"/>">User Manager</a></li>
    <li><a href="<c:url value="/monitoring"/>">Monitoring</a></li>
</ul>

<table>
    <tr>
        <th>Username</th>
        <th>IP Address</th>
        <th>Login Date</th>
    </tr>
    <c:forEach items="${sessionScope.listOnlineUsers}" var="user">
        <tr>
            <td><c:out value="${user.getUsername()}"/></td>
            <td><c:out value="${user.getRemoteAddress()}"/></td>
            <td><c:out value="${user.getDate()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
