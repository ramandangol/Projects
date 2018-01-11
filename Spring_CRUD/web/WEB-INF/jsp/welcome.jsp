<%-- 
    Document   : welcome
    Created on : Feb 27, 2017, 5:30:14 PM
    Author     : Bladestorm
--%>

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
        <%    String username = (String) session.getAttribute("username");%>
        <h1>Hello <%=username%></h1>
    </center>
    <div class="container">
        <a href="${pageContext.servletContext.contextPath}/loginDetails">
            <img src="assets/images/logindetails.png" style="width: 300px;height: 200px">
        </a>
        <a href="${pageContext.servletContext.contextPath}/studentForm">
            <img src="assets/images/stdreg1.png" style="width: 300px;height: 200px; margin-left: 50px">
        </a>
        <a href="${pageContext.servletContext.contextPath}/studentDetail">
            <img src="assets/images/stddetails.png" style="width: 300px; height: 200px; margin-left: 50px">
        </a><br>

        <a href="${pageContext.servletContext.contextPath}/newteacher">
            <img src="assets/images/newTeacher.jpg" style="width: 300px; height: 200px; margin-left: 30px; margin-top: 50px">
        </a>
        <a href="${pageContext.servletContext.contextPath}/teacherdetails">
            <img src="assets/images/teacherdetails.jpg" style="width: 300px; height: 200px; margin-left: 300px; margin-top: 50px;">
        </a> 
    </div>
</body>
</html>
