<%-- 
    Document   : logout_session
    Created on : Sep 6, 2016, 8:02:24 AM
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
        <%
        session.invalidate();
        response.sendRedirect("userlogin.jsp");
        %>
    </body>
</html>
