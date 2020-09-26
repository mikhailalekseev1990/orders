<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h3><a href="profile.jsp">Home</a></h3>
    <hr>
    <h2>${param.action == 'order_create' ? 'Create order' : 'Edit order'}</h2>
    <jsp:useBean id="order" class="org.example.orders.model.Request" scope="request"/>

    <form method="post" action="orders">
        <dd><input type="hidden" name="id" value="${order.id}"></dd>
        <div>id ${order.id}</div>
        <dl>
            <dt>Order:</dt>
            <dd><input type="text" value="${order.status}" name="status" required></dd>
            <dd><input type="text" value="${order.text}" name="text" required></dd>

        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>