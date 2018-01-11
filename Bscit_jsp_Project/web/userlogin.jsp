<%-- 
    Document   : userlogin
    Created on : Sep 5, 2016, 11:38:16 AM
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <%
            String getuser = (String) session.getAttribute("username");
            if (getuser != null) {
                response.sendRedirect("news_session.jsp");
            }
        %> 
        <%@include file="navigation_menu.jsp" %>
        <div class="container">
            <form  method="post" action="check_session.jsp" class="form-horizontal wrapper">
                <div class="container">
                    <div class="form-group">
                        <div class="col-sm-offset-2">
                            <h1><font color="blue"><b><span class="glyphicon glyphicon-user"></span> LOGIN</b></font></h1>  
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">Username :-</label>
                        <div class="col-sm-2">
                            <input type="password" class="form-control" name="username" placeholder="Enter Username">   
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="control-label col-sm-2">Password :-</label>
                        <div class="col-sm-2">
                            <input type="password" class="form-control" name="userpass" placeholder="Enter Password">   
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2">
                            <button type="submit" class="btn btn-primary btn-sm-2" value="Login" name="Login">Login</button>
                            <button type="reset" class="btn btn-danger btn-sm-2" value="reset" name="reset">Reset</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1">
                            <a href="register_form.jsp">Register || </a>
                            <a href="changepwd_form.jsp">Forgot Password</a> 
                        </div>
                    </div>
                </div>
            </form>
        </div>
    <center><h3 class="text-danger">
            <%
                if (request.getParameter("msg") != null) {
                    out.println("<p>" + request.getParameter("msg") + "</p>");
                }
            %>
        </h3>
    </center>
</body>
</html>
