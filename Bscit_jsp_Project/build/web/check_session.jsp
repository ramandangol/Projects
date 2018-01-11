<%-- 
    Document   : check_session
    Created on : Sep 5, 2016, 1:35:42 PM
    Author     : Bladestorm
--%>
<%@page  import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>check page</title>
    </head>
    <body>
       <%
            String user=request.getParameter("username");
            String pass=request.getParameter("userpass");
            if(user==null || pass==null){
                if(user!=null){
                response.sendRedirect("news_session.jsp");
            }
            else{
                response.sendRedirect("userlogin.jsp");
            }

                
                  response.sendRedirect("userlogin.jsp?error='Please fill in all the Fields'");
            }
            else{
               
            try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
        PreparedStatement stmt = con.prepareStatement("select * from user_login where username=? and pasword=?");
        stmt.setString(1, user);
        stmt.setString(2, pass);
        ResultSet rs = stmt.executeQuery();
        
        
        if(rs.next()){
            if(user.equals(rs.getString(2)) && pass.equals(rs.getString(3))){
                session=request.getSession();
            session.setAttribute("user",user);
            response.sendRedirect("news_session.jsp");
        }
        }else{
            response.sendRedirect("userlogin.jsp?msg='Your username or password is wrong....try again '");
            
        }
    }
    catch(Exception ex){
        out.println(ex.getMessage());
    }
    }
    %>          
    </body>
</html>
