<%-- 
    Document   : navigation_admin
    Created on : 06-Jul-2017, 13:41:36
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
      <script src="${pageContext.servletContext.contextPath}/assets/js/jquery.js"></script>
        <link href="${pageContext.servletContext.contextPath}/assets/Bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/assets/Bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/Bootstrap/js/bootstrap.js" type="text/javascript"></script>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/design.css" type="text/css">

  <title>navigation</title>
    </head>
    <body >

        <div class="container">
            <h2 style="color: #4351CA ; font-size: 60px;font-weight: bold"><center>School Management System</center></h2>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="${pageContext.servletContext.contextPath}/">Home</a></li>
                            <li><a href="#">About School</a></li>
                            <li><a href="#">News & Events</a></li>
                            <li><a href="#">Contact</a></li>
                           
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href=""><span class="glyphicon glyphicon-log-in"></span> Login<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.servletContext.contextPath}/loginpage">Admin</a></li>
                                    <li><a href="${pageContext.servletContext.contextPath}/parentlogin">Student</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

        </div>

    </body>
</html>

