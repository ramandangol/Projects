<%-- 
    Document   : adminDash
    Created on : 08/12/2017, 11:47:30 PM
    Author     : Bladestorm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <head>
        <title>Navigation header</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/Bootstrap/css/bootstrap.css">
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/Bootstrap/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/Bootstrap/js/bootstrap.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/design.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <body>
        <div>
            <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#" id="nav-headFont">Administrator</a>
                        <a class="navbar-brand" id="nav-title">School Management System</a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                           
                             <a class="dropdown-toggle" data-toggle="dropdown" href=""><img src="${pageContext.servletContext.contextPath}/assets/adminImages/${admindetails.file}" id="nav-img"> Welcome ${admindetails.fullname}<span class="caret"></span></a>
                       
                           
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.servletContext.contextPath}/profile">Profile</a></li>
                                <li><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>

                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- sidebar navigation -->
            <div class="sidenav" style="">
                <a href="${pageContext.servletContext.contextPath}/dashboard"><span class="glyphicon glyphicon-home">  Dashboard</span></a>
                <a href="${pageContext.servletContext.contextPath}/dashstudent"> <span class="glyphicon glyphicon-user">  Student</span></a>
                <a href="#clients"><span class="glyphicon glyphicon-user">  Teacher</span></a>
                <a href="#contact"><span class="glyphicon glyphicon-home">  Staff</span></a>
            </div>
        </div>

</body>
</html>
