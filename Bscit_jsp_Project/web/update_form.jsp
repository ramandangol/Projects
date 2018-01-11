<%-- 
    Document   : page_update
    Created on : Sep 12, 2016, 8:06:32 AM
    Author     : Bladestorm
--%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>update form</title>
    </head>
    <body>
        <%@include file="nav_logout.jsp" %>
        <%
            String getId=request.getParameter("id");
            int id=Integer.parseInt(getId);
            
         try{
             Class.forName("com.mysql.jdbc.Driver");
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
             PreparedStatement stmt=con.prepareStatement("Select * from companydata where id=?");
             stmt.setInt(1,id);
             ResultSet rs=stmt.executeQuery();
             if(rs.next()){
             %>
          <form class="form-horizontal" method="post" action="update_success.jsp">
            <div class="form-group">
                    <label class="control-label col-sm-2">ID :-</label>
                    <div class="col-sm-2">
                        <input type="hidden" class="form-control" name="id" value="<%=rs.getInt(1)%>">   
                    </div>
            </div>
            <div class="form-group">
                    <label class="control-label col-sm-2">Company Name :-</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="companyname" value="<%=rs.getString(2)%>">   
                    </div>
            </div>
            <div class="form-group">
                    <label class="control-label col-sm-2">Establish :-</label>
                    <div class="col-sm-2">
                        <select type="year" class="form-control" name="establish" value="<%=rs.getInt(3)%>">
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
                        <select  name="country" class="form-control" value="<%=rs.getString(4)%>">
                <option value="Nepal">Nepal</option>
                <option value="China">China</option>
                <option value="India">India</option>
                <option value="Japan">Japan</option>
            </select>   
                    </div>
                </div>
            <div class="form-group">   
                <label class="control-label col-sm-2">Status :-</label>
                <%
                if(rs.getInt(5)==1){
                %>
                <label class="radio-inline">
                    <input type="radio" name="status"  value="1" checked="checked"/>Active
                                     </label>
                                     <label class="radio-inline">
                                         <input type="radio" name="status" value="0"/>Inactive
                                     </label>
                <%
                }else{
                %>
                <label class="radio-inline">
                    <input type="radio" name="status" value="1"/>Active
                </label>
                <label class="radio-inline">
                    <input type="radio" name="status" value="0"/>Inactive
                </label>
                <%
                }
                %>
                        
                    </div>
            <div class="form-group">
                        <div class="col-sm-offset-2">
                            <button type="submit" class="btn btn-primary btn-sm-2" value="submit" name="submit">Submit</button>
                            <button type="reset" class="btn btn-danger btn-sm-2" value="reset" name="reset">Reset</button>
                        </div>
               
                    </div>
                </div>
            
                <%
             }
         
         }
         catch(Exception ex){
            out.println(ex.getMessage());
         }
        %>
        <%
        String msg= request.getParameter("msg");
        if(msg!=null){
            out.println(msg);
        }
        %>
        </form>
    </body>
</html>
