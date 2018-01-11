<%-- 
    Document   : Parents_Loginpage
    Created on : 09-Jul-2017, 15:31:18
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
        <%@include file="navigation_home.jsp" %>

        <div class="container"> 

            <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">Parents Login </div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

                        <form  action="${pageContext.servletContext.contextPath}/plogin" method="post"  id="loginform" class="form-horizontal" role="form">

                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="login-username" type="text" class="form-control" name="parentuser" value="" placeholder="Enter your username">                                        
                            </div>

                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="login-password" type="password" class="form-control" name="parentpass" placeholder="Enter your password">
                            </div>

                            <div class="input-group">
                                <h6 style="color:red">${param.msg}</h6>
                            </div>


                            <div style="margin-top:10px" class="form-group">
                                <div class="col-sm-12 controls">
                                    <input type="submit" class="btn btn-success" value="Login" class="btn btn-success">
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

