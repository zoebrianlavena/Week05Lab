
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        Hello, ${username}
        <a href="ShoppingList?action=logout">Logout</a>
        <h1>List</h1>
        <form method="post">
            <input type="text" name="itemtoadd">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
        </form>
        <ul>
            <c:forEach var="item" items="${items}">
                <input checked type="radio" name="radio" value="${item}"> ${item} <br>
            </c:forEach>
        </ul>
        <form method="post" action="ShoppingList?action=delete">
            <br> <input type="submit" value="Delete">
        </form>

    </body>
</html>
