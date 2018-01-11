<%-- 
    Document   : studentViewDetails
    Created on : 27/12/2017, 3:59:15 PM
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
        <div>
            <%@include file="navAdmin.jsp" %>
            <div class="main" style="margin-top: 82px;">
                <div class="container">
                    <h1>Student Details</h1>

                    <div class="col-sm-4">
                        <table class="table table-bordered" style="font-family: bold; font-size: 18px; margin-top: 20px;">
                            <tr>
                                <td>ID</td>
                                <td>${student.id}</td>
                            </tr>
                            <tr>
                                <td>Name</td>
                                <td>${student.firstname}  ${student.lastname}</td>
                            </tr>
                            <tr>
                                <td>Address </td>
                                <td>${student.address}</td>
                            </tr>
                            <tr>
                                <td>Full Address </td>
                                <td>${student.city} , ${student.state} , ${student.zip}</td>
                            </tr>
                            <tr>
                                <td>Gender</td>
                                <td>${student.gender}</td>
                            </tr>
                            <tr>
                                <td>Date of Birth</td>
                                <td>${student.day} - ${student.month} - ${student.year}</td>
                            </tr>
                            <tr>
                                <td>Grade</td>
                                <td>${student.grade}</td>
                            </tr>
                            <tr>
                                <td>Parents Name</td>
                                <td>${student.parentname}</td>
                            </tr>
                            <tr>
                                <td>Phone no.</td>
                                <td>${student.phone}</td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td>${student.email}</td>
                            </tr>
                            <tr>
                                <td>Username</td>
                                <td>${student.username}</td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td>${student.password}</td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-sm-4"><img src="${pageContext.servletContext.contextPath}/assets/studentImages/${student.image}" style="height: 300px;width: 300px; border-radius: 20%;"></div>
                </div>

            </div>
        </div>
    </body>
</html>
