<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
    <title>Users</title>
</head>
<body>
<section>
    <hr/>
    <h2>Users</h2>
    <hr/>
    <br><br>
    <c:set var="user" value='${requestScope["users"]}' scope="request"/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Roles</th>

        </tr>
        </thead>
        <c:forEach items="${users}" var="user">

            <jsp:useBean id="user" type="org.example.orders.model.User"/>
            <input type="hidden" name="id" value="${user.id}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td>${user.roles}</td>

                <td><a href="users/delete?u_id=${user.id}">Delete</a></td>


            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>