
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
        Hello, ${username} <a href="ShoppingList?action=logout">Logout</a>
        <h1>List</h1>
        <form method="post" action="ShoppingList?action=add">
            <input type="text" name="itemtoadd">
            <input type="submit" value="Add">
        </form>
        <form method="post" action="ShoppingList?action=delete">
            <ul>
                <c:forEach var="item" items="${items}">
                    <input type="radio" name="thisitem" value="${item}"> ${item} <br>
                </c:forEach>
            </ul>
            <br> <input type="submit" value="Delete">
        </form>

    </body>
</html>
