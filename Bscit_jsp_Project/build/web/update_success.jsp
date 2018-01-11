<%-- 
    Document   : update_success
    Created on : Sep 13, 2016, 10:07:49 AM
    Author     : Bladestorm
--%>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>update success</title>
    </head>
    <body>
        <%
        int id=Integer.parseInt(request.getParameter("id"));
        String getCompname=request.getParameter("companyname");
        int getEst=Integer.parseInt(request.getParameter("establish"));
        String getCountry=request.getParameter("country");
        int getStatus=Integer.parseInt(request.getParameter("status"));
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            PreparedStatement stmt=con.prepareStatement("update companydata set companyname=?,establish=?,country=?,status=? where id=?");
            stmt.setString(1,getCompname);
            stmt.setInt(2,getEst);
            stmt.setString(3, getCountry);
            stmt.setInt(4, getStatus);
            stmt.setInt(5, id);
            int i=stmt.executeUpdate();
            if(i>0){
                 response.sendRedirect("update_form.jsp?msg=update success");
                }
            else{
                response.sendRedirect("company_form.jsp");
            }
        
        }
        catch(Exception ex)
        {
        out.println(ex.getMessage());
        }
        %>
    </body>
</html>
