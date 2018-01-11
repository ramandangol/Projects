<%-- 
    Document   : user_regist
    Created on : 27-Feb-2017, 21:03:37
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/adminRegFormStyle.css" type="text/css">
    </head>
    <%@include file="navigation_home.jsp" %>
    <body>
        <div class="container">
            <div class="row main">
                <div class="main-login main-center">
                    <h5 style="color: black;font-family: bold;font-size: 20px">Admin Registration Form</h5>
                    <form class="" method="post" action="${pageContext.servletContext.contextPath}/uRegist">
 <div class="form-group">
                            <label for="username" class="cols-sm-2 control-label">Username</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="username" id="username"  placeholder="Enter your Username"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="cols-sm-2 control-label">Password</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                                    <input type="password" class="form-control" name="password" id="password"  placeholder="Enter your Password"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                                    <input type="password" class="form-control" name="repassword" id="confirm"  placeholder="Confirm your Password"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirm" class="cols-sm-2 control-label">Pin Code</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                                    <input type="password" class="form-control" name="pin" id="confirm"  placeholder="Enter Pin"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            ${param.msg}
                        </div>
                        <div class="form-group ">
                            <input type="submit" class="btn btn-success" value="Register" class="btn btn-success">
                            <input type="reset" class="btn btn-primary" value="Clear" class="btn btn-success">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
