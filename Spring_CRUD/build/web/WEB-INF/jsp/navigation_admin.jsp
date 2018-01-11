<%-- 
    Document   : navigation_admin
    Created on : 16-Jul-2017, 16:01:30
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="${pageContext.servletContext.contextPath}/assets/js/jquery.js"></script>
        <link href="${pageContext.servletContext.contextPath}/assets/Bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/assets/Bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/Bootstrap/js/bootstrap.js" type="text/javascript"></script>
            <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/design.css" type="text/css">
  <title>JSP Page</title>
    </head>
    <body>
       <div class="container">
            <h2 style="color: #4351CA ; font-size: 60px;font-weight: bold">School Management System</h2>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="${pageContext.servletContext.contextPath}/">Home</a></li>
                            <li><a href="#">About School</a></li>
                            <li><a href="#">News & Events</a></li>
                            <li><a href="#">Contact</a>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="${pageContext.servletContext.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                        </ul>
                    </div>
                </div>
            </nav>

        </div>
    </body>
</html>
