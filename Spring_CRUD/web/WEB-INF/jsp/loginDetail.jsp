<%-- 
    Document   : loginDetail
    Created on : 01-Mar-2017, 11:36:57
    Author     : Bladestorm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <%@include file="navigation_admin.jsp" %>
        <div class="container">
            <h1>Login list</h1>
            <table class="table table-hover table-bordered">
                <thead class="thead-inverse" style="background: #4f4b4b;color: #ffffff">
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody >
                    <c:forEach items="${ul}" var="user">
                        <tr class="success">
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td><a href="${pageContext.servletContext.contextPath}/admindelete?id=${user.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <h5>${param.msg}</h5>
        </div>
    </center>
</body>
</html>
