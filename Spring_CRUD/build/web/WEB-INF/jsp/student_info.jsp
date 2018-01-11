<%-- 
    Document   : student_details
    Created on : 09-Jul-2017, 16:09:02
    Author     : Bladestorm
--%>

<%@page import="com.spring.controller.StudentController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <%@include file="navigation_admin.jsp" %>
        
       <div class="container">
           <div class="col-sm-4">
               <table class="table table-bordered" style="font-family: bold; font-size: 18px;">
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
               <td>${student.parent}</td>
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
           
       </div>
       
        
    </center>
    </body>
</html>
