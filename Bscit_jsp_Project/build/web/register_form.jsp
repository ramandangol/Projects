<%-- 
    Document   : register_form
    Created on : Sep 6, 2016, 11:35:50 AM
    Author     : Bladestorm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Register form</title>
    </head>
    <body >
        
        <%@include file="navigation_menu.jsp" %>
        <form  method="post" action="register_session.jsp" class="form-horizontal" >
            <font color="Green">
            <div class="container">
                <div class="form-group">
                    <div class="col-sm-offset-2">
                        <h1>NEW ACCOUNT</h1>  
                    </div>
                  
                </div>
                    
                
                <div class="form-group">
                    <label class="control-label col-sm-2">Username :-</label>
                    <div class="col-sm-2">
                     <input type="text" class="form-control" name="username">   
                    </div>
                </div>
                    
                <div class="form-group">
                    <label class="control-label col-sm-2">Password :-</label>
                    <div class="col-sm-2">
                     <input type="password" class="form-control" name="password">   
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">Pin :-</label>
                    <div class="col-sm-2">
                     <input type="password" class="form-control" name="pin">   
                    </div>
                </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2">
                            <button type="submit" class="btn btn-primary btn-sm" value="register" name="register">Register</button>
                        </div>
                        
                    </div>
            </div>
            
            
        </form>
        <%
            if (request.getParameter("msg")!=null){
                out.print(request.getParameter("msg"));
            }
            %>
            </font>
    </body>
</html>
