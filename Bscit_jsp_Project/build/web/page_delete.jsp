<%-- 
    Document   : page_delete
    Created on : Sep 12, 2016, 8:06:43 AM
    Author     : Bladestorm
--%>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>page delete</title>
    </head>
    <body>
       <%
       int id=Integer.parseInt(request.getParameter("id"));
       int confirm=Integer.parseInt(request.getParameter("confirm"));
       if(confirm==1){
       try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
           PreparedStatement stmt=con.prepareStatement("delete from companydata where id=?");
           stmt.setInt(1, id);
           int i=stmt.executeUpdate();
           if(i>0){
               %>
               <div class="well well-lg">Record Successfully Deleted</div>
               <%
           }else{
           response.sendRedirect("company_form.jsp");
            }
       }
       catch(Exception ex){
        out.println(ex.getMessage());
        }
       }
    else{
        response.sendRedirect("company_form.jsp");
        }
       %>
    </body>
</html>
