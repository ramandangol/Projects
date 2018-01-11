<%-- 
    Document   : register_session
    Created on : Sep 5, 2016, 1:36:35 PM
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>register session</title>
    </head>
    <body >
        <%
        String user=request.getParameter("username");
        String pass=request.getParameter("password");
        String pin = request.getParameter("pin");
        if(user.isEmpty() || pass.isEmpty() || pin.isEmpty()){
        response.sendRedirect("register_form.jsp?msg=Field is empty... Try Again!");
        }else if(!pin.equals("7777")){
            response.sendRedirect("register_form.jsp?msg=Pin not matched");
        }
        
        else{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            PreparedStatement stmt=con.prepareStatement("SELECT * FROM user_login where username=? and pasword=?");
            stmt.setString(1,user);
            stmt.setString(2,pass);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                response.sendRedirect("register_form.jsp?msg='Data already exits'");
               
            }
            else{

            PreparedStatement pstmt=con.prepareStatement("INSERT into user_login(username,pasword) values(?,?)");
            
            pstmt.setString(1,user);
            pstmt.setString(2,pass);
            int rs2=pstmt.executeUpdate();
            
            if(rs2>0){
                response.sendRedirect("register_form.jsp?msg=Data has been inserted");
                
            }
        }
            
        }
        catch(Exception ex){
            out.println(ex.getMessage());
        }
        }
        %>
    </body>
</html>
