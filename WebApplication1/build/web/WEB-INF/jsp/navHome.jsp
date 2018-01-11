<%-- 
    Document   : navHome
    Created on : 18/12/2017, 10:04:04 PM
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/Bootstrap/css/bootstrap.css">
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/Bootstrap/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/Bootstrap/js/bootstrap.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/design.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/adminRegFormStyle.css" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <body>
        <div>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				
                            <a class="navbar-brand" id="nav-title" href="${pageContext.servletContext.contextPath}/">School Management System</a>
			</div>

			  <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href=""><span class="glyphicon glyphicon-log-in"></span> Login<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.servletContext.contextPath}/adminloginpage">Admin</a></li>
                                    <li><a href="${pageContext.servletContext.contextPath}/parentlogin">Student</a></li>
                                </ul>
                            </li>
                        </ul>
		</div>
	</nav>
	
</div>
   
    </body>
</html>
