<%-- 
    Document   : adminForgotPassword
    Created on : 20/12/2017, 2:42:35 PM
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
         <center>
        <%@include file="navHome.jsp" %>
        <div class="container"> 
            <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">Update Password</div>
                    </div>     
                    <div style="padding-top:30px" class="panel-body" >
                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                        <form  action="${pageContext.servletContext.contextPath}/updatePass" method="post"  id="loginform" class="form-horizontal" role="form">
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="Enter your username">                                        
                            </div>
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="login-password" type="password" class="form-control" name="password" placeholder="Enter new password">
                            </div>
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="login-password" type="password" class="form-control" name="repassword" placeholder="Confirm password">
                            </div>
                             <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="login-password" type="password" class="form-control" name="pin" placeholder="Enter Pin">
                            </div>
                            <div class="input-group">
                                <h6 style="color:red">${msg}</h6>
                                <h6 style="color:green">${msg1}</h6>
                            </div>
                            <div style="margin-top:10px" class="form-group">
                                <!-- Button -->
                                <div class="col-sm-12 controls">
                                    <input type="submit" class="btn btn-success" value="Update Password" class="btn btn-success">
                                    <input type="reset" class="btn btn-primary" value="Clear" class="btn btn-success">
                                </div>
                            </div>
                        </form>     
                    </div>                     
                </div>  
            </div>
    </center>
    </body>
</html>
