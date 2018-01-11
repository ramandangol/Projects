<%-- 
    Document   : adminProfile
    Created on : 24/12/2017, 10:59:05 AM
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
        <%@include file="navAdmin.jsp" %>
        <div class="main" style="margin-top: 82px;">
            <div class="container">
                <div class="col-sm-3">
                    <img src="${pageContext.servletContext.contextPath}/assets/adminImages/${admindetails.file}" height="200px" width="200px" style="padding-top: 20px"><br/><br/>
                    ${admindetails.fullname}     
                </div>

                <div class="col-sm-8" style="padding: 30px">
                    <form action="${pageContext.servletContext.contextPath}/adminprofileu" method="post">
                        <div class="form-group">
                            <label for="name">Full Name</label>
                            <input type="text" name="fullname" class="form-control" id="name" aria-describedby="name" placeholder="Enter Full name" value="${admindetails.fullname}" readonly="readonly">
                            
                        </div>
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" name="username" class="form-control" id="exampleInputPassword1" placeholder="Enter Username" value="${admindetails.username}" readonly="readonly">
                        </div>
                        <div class="form-group">
                            <label for="currentpassword">Password</label>
                            <input type="password" name="currentpassword"class="form-control" id="exampleInputPassword1" placeholder="Current Password">
                        </div>
                      <div class="form-group">
                            <label for="newpassword">New Password</label>
                            <input type="password" name="newpassword" class="form-control" id="exampleInputPassword1" placeholder="New Password">
                        </div>
                        <div class="form-group">
                            <label for="confirmpassword">Confirm Password</label>
                            <input type="password" name="confirmpassword" class="form-control" id="exampleInputPassword1" placeholder="Confirm password">
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Update Profile</button>
                    </form>
                            <h3 style="color: red;">${msg}</h3>
                        <h3 style="color: green;">${msg1}</h3>
                </div>

            </div>
        </div>

    </body>
</html>
