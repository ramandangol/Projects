<%-- 
    Document   : changepwd_session
    Created on : Sep 5, 2016, 1:37:00 PM
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>password change</title>
    </head>
    <body>
        <%
        String getuser=request.getParameter("username");
        String getpass=request.getParameter("password");
        String getrepass=request.getParameter("verpassword");
        if(getuser.isEmpty() || getpass.isEmpty()|| getrepass.isEmpty()){
        response.sendRedirect("changepwd_form.jsp?msg=Field is empty");
        
        }
        else if(getpass.equals(getrepass)){
           
            try{
                Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
                    PreparedStatement stmt=con.prepareStatement("Select * from user_login where username=?" );
                    stmt.setString(1,getuser);
                    ResultSet rs=stmt.executeQuery();
                    
                    if(rs.next()){
                       PreparedStatement pstmt=con.prepareStatement("Update user_login set pasword=? where username=?");
                       pstmt.setString(1, getpass);
                       pstmt.setString(2, getuser);
                       int update=pstmt.executeUpdate();
                       
                       if(update >0){
                           response.sendRedirect("changepwd_form.jsp?msg= you have new password");
                       }
                    }
                    
            
            }
            catch(Exception ex){
                out.println(ex.getMessage());
                }
        }
        else{
                response.sendRedirect("changepwd_form.jsp?msg='Sorry.. Record doesnt match.'");
            }
        
        %>
    </body>
</html>
