<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="post">
            <p>
                <label for="email">Username: </label>
                <input type="text" name="email" id="email" value="${email}" required></p>
            <p>
                <label for="password">Password: </label>
                <input type="password" name="password" id="password" required></p>
            <button type="submit">Log in</button>
        </form>
        <p>${message}</p>
    </body>
</html>
