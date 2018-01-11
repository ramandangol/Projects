<%-- 
    Document   : news_session
    Created on : Sep 5, 2016, 1:36:00 PM
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String user=(String)session.getAttribute("user");
        if(user==null){
        response.sendRedirect("userlogin.jsp");
        }
        %>
        <%@include file="nav_logout.jsp" %>
                          
        <h1><center><b><font color="green">welcome <%= user%></font></b></center></h1>
     </body>
</html>
