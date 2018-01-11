<%-- 
    Document   : changepwd_form
    Created on : Sep 6, 2016, 12:27:13 PM
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="navigation_menu.jsp" %>
        <form  method="post" action="changepwd_session.jsp" class="form-horizontal">
            <div class="container">
                <div class="form-group">
                    <div class="col-sm-offset-2">
                        <h1>Forgot password</h1>  
                    </div>
                  
                </div>
                    
                
                <div class="form-group">
                    <label class="control-label col-sm-2">Username :-</label>
                    <div class="col-sm-2">
                     <input type="text" class="form-control" name="username">   
                    </div>
                </div>
                    
                <div class="form-group">
                    <label class="control-label col-sm-2">New Password :-</label>
                    <div class="col-sm-2">
                     <input type="password" class="form-control" name="password">   
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">Verify Password :-</label>
                    <div class="col-sm-2">
                     <input type="password" class="form-control" name="verpassword">   
                    </div>
                </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2">
                            <button type="submit" class="btn btn-primary btn-sm" value="submit" name="submit">Change</button>
                        </div>
                        
                    </div>
            </div>
            ${param.msg}
            
            
        </form>
    </body>
</html>
