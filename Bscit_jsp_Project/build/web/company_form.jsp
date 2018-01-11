<%-- 
    Document   : company_form
    Created on : Sep 7, 2016, 9:17:16 AM
    Author     : Bladestorm
--%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>company form</title>
    </head>
    <body>
        <%@include file="nav_logout.jsp" %>
        <h1>Company Form</h1>
        <form class="form-horizontal" method="post" action="company_session.jsp">
            <div class="form-group">
                    <label class="control-label col-sm-2">Company Name :-</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="companyname">   
                    </div>
                </div>
            <div class="form-group">
                    <label class="control-label col-sm-2">Establish :-</label>
                    <div class="col-sm-2">
                        <select type="year" class="form-control" name="establish">
                            <%
                            Calendar c= Calendar.getInstance();
                            int year= c.get(Calendar.YEAR);
                            for (int i=1950;i<year;i++){
                            %>
                            <option value="<%= i%>"><%=i%></option>
                            <%
                            }
                            %>
               
                
                
                
            </select>   
                    </div>
                </div>
            <div class="form-group">
                    <label class="control-label col-sm-2">Country :-</label>
                    <div class="col-sm-2">
                        <select  name="country" class="form-control">
                <option value="Nepal">Nepal</option>
                <option value="China">China</option>
                <option value="India">India</option>
                <option value="Japan">Japan</option>
            </select>   
                    </div>
                </div>
            <div class="form-group">   
                <label class="control-label col-sm-2">Status :-</label>
                <label class="radio-inline">
                               <input type="radio" name="status"  value="1"/>Active
                                     </label>
                                     <label class="radio-inline">
                                         <input type="radio" name="status" value="0"/>Inactive
                                     </label>
                        
                    </div>
            <div class="form-group">
                        <div class="col-sm-offset-2">
                            <button type="submit" class="btn btn-primary btn-sm-2" value="submit" name="submit">Submit</button>
                            <button type="reset" class="btn btn-danger btn-sm-2" value="reset" name="reset">Reset</button>
                        </div>
               
                    </div>
                            <h1>${param.msg}</h1>
                </div>
            
    </form><br><br>
     <div class="container">
        <div class="row">
                <div class="col-md-8">
                    
                 <table class="table table-bordered table-striped">
           <tr>
                <th>ID</th>
                <th>Company list</th>
                
               <th>Action</th>
                
            </tr>
            <% 
            int total=0;
            int active=0;
            int inactive=0;
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            PreparedStatement pstmt=con.prepareStatement("select * from companydata");
            ResultSet rs=pstmt.executeQuery();
             while(rs.next()){
            total++;
            if(rs.getInt(5)==0){
            inactive++;
            }
            else{
            active++;
            }
             
                %>
                <tr>
                    <td><%=rs.getInt(1)%></td>
                    <td><%= rs.getString(2)%></td>
                    <td><a href="<c:url value="page_view.jsp?id="/><%=rs.getInt(1)%>">View</a>
                    |
                    <a href="<c:url value="update_form.jsp?id="/><%=rs.getInt(1)%>">Update</a>
                    |
                    <a href="<c:url value="delete_confirmform.jsp?id="/><%=rs.getInt(1)%>">Delete</a></td>
                </tr>
                
                <%
            }
            
            }
            
            catch(Exception e){
                out.println(e.getMessage());
            }
            %>
        </table>
                </div>
                <div class="col-md-4">
                    <table class="table table-bordered table-striped">
                        <tr>
                            <th>Total Company</th>
                            <td><%= total%></td>
                        </tr>
                        
                        <tr>
                            <th>Active</th>
                            <td><%= active%></td>
                        </tr>
                        
                        <tr>
                            <th>Inactive</th>
                            <td><%= inactive%></td>
                        </tr>
                        
                    </table>
                </div>
        </div>    
            </div>
    
    </body>
</html>
