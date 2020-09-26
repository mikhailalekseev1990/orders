<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
    <title>Orders</title>
    <link rel="stylesheet" href="../../css/style.css">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>

</head>
<body>
<section>
    <hr/>
    <h2>Orders</h2>
    <hr/>

        <a href="${pageContext.request.contextPath}/orders/create">Add Restaurant</a>
        <br><br>

    <c:set var="user" value='${requestScope["user"]}' scope="request"/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Menu</th>
            <%--            <th></th>--%>
            <%--            <th></th>--%>
        </tr>
        </thead>
        <c:forEach items="${request}" var="request">

            <jsp:useBean id="restaurant" type="org.example.orders.model.Request"/>
            <input type="hidden" name="r_id" value="${request.id}">
            <tr style="color:${restaurant.id==user.vote_restaurant_id ? 'blue' : 'red'}">
                <td>${request.id}</td>
                <td>${request.status}</td>
                <td>${request.text}</td>

                    <td><a href="orders/update/${request.id}">Update</a></td>
                    <td><a href="orders/delete/${request.id}">Delete</a></td>


                    <td><a href="orders/changeStatus?r_id=${request.id}"> changeStatus </a></td>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>