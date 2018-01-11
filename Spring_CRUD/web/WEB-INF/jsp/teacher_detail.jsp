<%-- 
    Document   : teacher_detail
    Created on : 27-Oct-2017, 09:54:23
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
                <h1>Teacher Details</h1>
                <table class="table table-bordered table-hover" id="myTable">
                    <thead class="thead-inverse" style="background: #4f4b4b;color: #ffffff">
                         <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Address</th>
                <th>Gender</th>
                <th>Date of Birth</th>
                <th>Faculty</th>
               <th>Phone no</th>
               <th>Email</th>
               
                <th>Action</th>
            </tr>
                    </thead>
                    
                    <c:forEach items="${tl}" var="tche">
                        <tr class="success">
                            <td>${tche.id}</td>
                            <td>${tche.firstname}   ${tche.lastname}</td>
                            <td>${tche.address} , ${tche.city} , ${tche.state} - ${tche.zip}</td>
                           <td>${tche.gender}</td>
                           <td>${tche.day} - ${tche.month} - ${tche.year}</td>
                            <td>${tche.faculty}</td>
                            <td>${tche.phone}</td>
                            <td>${tche.email}</td>
                             <td>
                        <a href="${pageContext.servletContext.contextPath}/deleteT?id=${tche.id}">Delete</a>
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
