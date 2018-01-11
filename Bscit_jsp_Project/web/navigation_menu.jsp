<%-- 
    Document   : navigation_menu
    Created on : Sep 7, 2016, 1:12:18 PM
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="Bootstrap/css/bootstrap.css"/>" type="text/css" rel="stylesheet"/>
        <script src="<c:url value="Bootstrap/js/bootstrap.js"/>" type="text/javascript"></script>
        <script src="<c:url value="Bootstrap/jquery-3.1.0.js"/>" type="text/javascript"></script>
         <link rel="stylesheet" href="design.css" type="text/css">
        <title>navigation</title>
    </head>
    <body style="background-color: skyblue;">
        <div class="container">
          
         <nav class="navbar navbar-inverse">
             <div class="navbar-header">
                 <a class="navbar-brand" href="#">CRS</a>
             </div>
             <ul class="nav navbar-nav">
                 <li><a href="index.jsp" class="active"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                 <li><a href="#" class="active"><span class="glyphicon glyphicon-envelope"></span> Services</a></li>
                 <li><a href="#" class="active"><span class="glyphicon glyphicon-info-sign"></span> About us</a></li>
                 <li><a href="#" class="active"><span class="glyphicon glyphicon-user"></span> Contact us</a></li>
                  <li><a href="userlogin.jsp" class="active"><span class="glyphicon glyphicon-user"></span> Login </a></li>
             </ul>
         </nav>
    </body>
</html>
