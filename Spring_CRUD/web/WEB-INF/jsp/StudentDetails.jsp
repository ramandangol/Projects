<%-- 
    Document   : StudentDetails
    Created on : 08-Mar-2017, 13:25:05
    Author     : Bladestorm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <input id="myInput" onkeyup="myFunction()" class="form-control" type="text" placeholder="Search for firstname" >
            </div><br/>
            <h1>Student Details</h1>
            <table class="table table-bordered table-hover" id="myTable">
                <thead class="thead-inverse" style="background: #4f4b4b;color: #ffffff">
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Address</th>
                        <th>Gender</th>
                        <th>Grade</th>
                        <th>Parent Name</th>
                        <th>Phone no</th>

                        <th>Action</th>
                    </tr>
                </thead>
                <c:forEach items="${sl}" var="stud">
                    <tr class="success">
                        <td>${stud.id}</td>
                        <td>${stud.firstname}</td>
                        <td>${stud.lastname}</td>
                        <td>${stud.address}</td>
                        <td>${stud.gender}</td>
                        <td>${stud.grade}</td>
                        <td>${stud.parent}</td>
                        <td>${stud.phone}</td>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/stdSingleDetails?id=${stud.id}">Details</a> ||
                            <a href="${pageContext.servletContext.contextPath}/updateForm?id=${stud.id}">Update</a> ||
                            <a href="${pageContext.servletContext.contextPath}/delete?id=${stud.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>


        ${param.msg}
    </center>
    <script>
        function myFunction() {
            var input, filter, table, tr, td, i;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[1];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
</body>
</html>
