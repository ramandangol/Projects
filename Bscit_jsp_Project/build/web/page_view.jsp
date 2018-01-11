<%-- 
    Document   : page_view
    Created on : Sep 12, 2016, 8:00:50 AM
    Author     : Bladestorm
--%>

<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>page view</title>
    </head>
    <%@include file="nav_logout.jsp" %>
    <body>
        <div class="container">
        <div class="row">
            <div class="col-md-8">
                <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <th>Company list</th>
                <th>Establish</th>
                <th>Country</th>
                <th>Status</th>
            </tr>
            <%
                   int id=Integer.parseInt(request.getParameter("id"));
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:MySql://localhost:3306/student", "root", "");
        PreparedStatement pstmt=con.prepareStatement("Select * from companydata where id=?");
        pstmt.setInt(1, id);
          ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
           
            
            %>
             <tr>
              
                <td><%=rs.getInt(1)%></td>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getInt(3)%></td>
                <td><%=rs.getString(4)%></td>
                 <td><%=rs.getInt(5)==0?"Deactive":"Active"%></td>
               
            </tr>
           <%
        }
        }
        catch(Exception ex){
         out.println(ex.getMessage());
     }
       %>
            
                </table>
            </div>
        </div>
        </div>
    </body>
</html>
