<%-- 
    Document   : company_session
    Created on : Sep 8, 2016, 3:56:17 PM
    Author     : Bladestorm
--%>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>check</title>
    </head>
    <body>
        <%
                
            String compname=request.getParameter("companyname");
            String country=request.getParameter("country");
            Integer est=Integer.parseInt(request.getParameter("establish"));
            Integer status=Integer.parseInt(request.getParameter("status"));
                
            if(compname.isEmpty() || country.isEmpty()){
                response.sendRedirect("company_form.jsp?msg=field is empty");
            }else{
                try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            PreparedStatement stmt=con.prepareStatement("insert into companydata(companyname,establish,country,status) values(?,?,?,?)");
            stmt.setString(1, compname);
            stmt.setInt(2, est);
            stmt.setString(3, country);
            stmt.setInt(4, status);
            int i=stmt.executeUpdate();
            if(i>0){
                %>
                <div class="well well-lg">Record Successfully Inserted</div>
                <%
                response.sendRedirect("company_form.jsp?msg='Record successfully inserted.'");
            }
            
            }
            catch(Exception e){
                out.println(e.getMessage());
            }
            }
               
                
                %>
    </body>
</html>
