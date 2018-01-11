<%-- 
    Document   : adminLoginForm
    Created on : 18/12/2017, 10:08:12 PM
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
        <%@include file="navHome.jsp" %>
        <div class="container"> 

            <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title"><h4 style="color:green;font-size: 30px;font-family: bold;">Administrator Login</h4></div>
                        <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="${pageContext.servletContext.contextPath}/updatePassword">Forgot password?</a></div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

                        <form  action="${pageContext.servletContext.contextPath}/dashboard" method="post"  id="loginform" class="form-horizontal" role="form">

                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username or email">                                        
                            </div>

                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                            </div>

                            <div class="input-group">
                                <h6 style="color:red">${param.msg}</h6>
                            </div>

<!--                            <div class="input-group">
                                <div class="checkbox">
                                    <label>
                                        <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                    </label>
                                </div>
                            </div>-->


                            <div style="margin-top:10px" class="form-group">
                                <!-- Button -->

                                <div class="col-sm-12 controls">
                                    <input type="submit" class="btn btn-success" value="Login" class="btn btn-success" style="margin-left: 60px;">
                                    <input type="reset" class="btn btn-primary" value="Clear" class="btn btn-success" style="margin-left: 30px;">
                                
                                </div>
                                
                            </div>


                            <div class="form-group">
                                <div class="col-md-12 control">
                                    <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                        Don't have an account! 
                                        <a href="${pageContext.servletContext.contextPath}/regist" >
                                            Sign Up Here
                                        </a>
                                    </div>
                                </div>
                            </div>    
                        </form>     

                    </div>                     
                </div>  
            </div>

    </body>
</html>
