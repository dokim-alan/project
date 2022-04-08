<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UsersTab</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <h1>User Management System</h1>
        <h2>User List</h2>
            <form action="user" method="POST">
                <div class="">
                        <table class="table">
                            <thead> 
                                <tr>
                                    <th>E-mail</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Active</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${users}" >
                                    <tr>
                                        <td>${user.email}</td>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td>${user.active ? "Y" : "N"}</td>
                                        <td>
<!--
                                <button type="submit" class="btn-primary " name="action" value="edit?${user.email}">EDIT</button>
                                <button type="submit" class="btn-secondary" name="action" value="delete?${user.email}">DELETE</button>

value="${user.email}"
-->
                                            <!-- <form action="user" method="POST">  -->
                                                <button type="submit" name="action" id="action" value="edit?${user.email}">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                                                     <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                                    </svg>
                                                </button>

                                                <button type="submit" name="action" id="action" value="delete?${user.email}">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                                                    <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/>
                                                    </svg>
                                                    </button> 
                                             <!-- </form> -->
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table> 
                    </div>
            </form>
        
        <h2>Create User</h2>
        <div class="container">
            <div class="row">
                <div class="col">
                    <form action="user" method="POST">
                        <table class="table">
                            <thead> 
                                <tr>
                                    <th>E-mail</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Password</th>
                                    <th>Roles</th>
                                    <th>Active</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="email" name="email">
                                    </td>
                                    <td><input type="text" name="firstName">
                                    </td>
                                    <td><input type="text" name="lastName">
                                    </td>
                                    <td><input type="password" name="password">
                                    </td>
                                    <td>
                                        <select name="role">
                                            <option value="">Roles</option>
                                            <c:forEach var="role" items="${roles}">
                                                <option value="${role.name}">${role.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input type="checkbox" name="active">
                                    </td>
                                    <td><button type="submit" class="btn-primary" name="action" value="create">CREATE</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        
        <h2>Update User</h2>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <form action="user" method="POST">
                            <table class="table">
                            <thead> 
                                <tr>
                                    <th>E-mail</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Password</th>
                                    <th>Roles</th>
                                    <th>Active</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="email" name="email" value="${user.email}"> <!-- readonly> -->
                                    </td>
                                    <td><input type="text" name="firstName" value="${user.firstName}">
                                    </td>
                                    <td><input type="text" name="lastName" value="${user.lastName}">
                                    </td>
                                    <td><input type="password" name="password" value="${user.password}">
                                    </td>
                                    <td>
                                        <select name="role">
                                            <option value="">Roles</option>
                                            <c:forEach var="role" items="${roles}">
                                                <option value="${role.name}">${role.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input type="checkbox" name="active">
                                    </td>
                                    <td><button type="submit" class="btn-primary" name="action" value="update">UPDATE</button>
                                    </td>
                                </tr>
                            </tbody>
                            </table>
                        </form>
                    <p>${message}</p>
                    </div>
                </div>
            </div>
    </body>            
</html>
