<%-- 
    Document   : delete_confirmform
    Created on : Sep 13, 2016, 2:24:54 PM
    Author     : Bladestorm
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Retrieving Data</title>
    </head>
    <body>

        
     <div class="container">
         <%@include file="nav_logout.jsp" %> 
        
         <%
             int id=Integer.parseInt(request.getParameter("id"));
             %>
         <div class="well well-lg">
             <p>
                 Are you Sure you Want to delete:
        </p><br/>
        <form method="post" action="<c:url value="page_delete.jsp?id="/><%=id%>">
           <input type="radio" name="confirm" value="1"/>Yes <br/><br/>
           <input type="radio" name="confirm" value="0"/>No <br/><br/><br/>
           <input type="submit" value="Confirm"/>
        </form>
         </div>
         
        
        
     </div>
    </body>
</html>

