<%-- 
    Document   : adminStudent
    Created on : 18/12/2017, 7:55:17 PM
    Author     : Bladestorm
--%>

<%@page import="java.util.Calendar"%>
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
                <div class="container"  id="bodyparagraph" style="margin: 20px;">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#editstudent"><span class="glyphicon glyphicon-plus-sign">  Update Student</span></a></li>
                        <li><a data-toggle="tab" href="#studentlist"><span class="glyphicon glyphicon-menu-hamburger">  Student List</span></a></li>

                    </ul>


                    <div class="tab-content">



                        <div id="editstudent" class="tab-pane fade in active">
                            <!--                        <h3>Add Student</h3>-->
                            <div class="container">
                                <h1 class="well col-sm-8">Student Update Form</h1>
                                <div class="col-lg-8 well">
                                    <div class="row">
                                        <form method="post" action="${pageContext.servletContext.contextPath}/stdUpdate">
                                            <div class="col-sm-12">
                                                <div class="row">
                                                    <div class="col-sm-8 form-group">
                                                        <label>ID</label>
                                                        <input type="text" placeholder="" class="form-control" name="id" value="${student.id}" readonly="readonly">

                                                    </div>
                                                    <div class="col-sm-6 form-group">
                                                        <label>First Name</label>
                                                        <input type="text" placeholder="Enter First Name Here.." class="form-control" name="firstname" value="${student.firstname}">
                                                        <h5 style="color: red">${param.firstnamemsg}</h5> 
                                                    </div>
                                                    <div class="col-sm-6 form-group">
                                                        <label>Last Name</label>
                                                        <input type="text" placeholder="Enter Last Name Here.." class="form-control" name="lastname" value="${student.lastname}">
                                                        <h5 style="color: red">${param.lastnamemsg}</h5> 
                                                    </div>
                                                </div>					
                                                <div class="form-group">
                                                    <label>Address</label>
                                                    <input type="text" placeholder="Enter Address Here.." class="form-control" name="address" value="${student.address}">
                                                    <h5 style="color: red">${param.addressmsg}</h5> 
                                                </div>	
                                                <div class="row">
                                                    <div class="col-sm-4 form-group">
                                                        <label>City</label>
                                                        <input type="text" placeholder="Enter City Name Here.." class="form-control" name="city" value="${student.city}">
                                                        <h5 style="color: red">${param.citymsg}</h5> 
                                                    </div>	
                                                    <div class="col-sm-4 form-group">
                                                        <label>State</label>
                                                        <input type="text" placeholder="Enter State Name Here.." class="form-control" name="state" value="${student.state}">
                                                        <h5 style="color: red">${param.statemsg}</h5> 
                                                    </div>	
                                                    <div class="col-sm-4 form-group">
                                                        <label>Zip</label>
                                                        <input type="text" placeholder="Enter Zip Code Here.." class="form-control" name="zip" value="${student.zip}">
                                                        <h5 style="color: red">${param.zipmsg}</h5> 
                                                        <h5 style="color: red">${param.szipmsg}</h5> 
                                                    </div>		
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-12 form-group">
                                                        <label>Gender</label>
                                                        <input type="radio" class="form-group" name="gender" value="male"  ${(student.gender).equalsIgnoreCase("male")?"checked":""}>   Male
                                                        <input type="radio" class="form-group" name="gender" value="female" ${(student.gender).equalsIgnoreCase("female")?"checked":""}>  Female
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-4 form-group">
                                                        <label>Date of birth     </label>
                                                        <select class="form-control" name="day" >
                                                            <option name="day" value="">Select Day</option>
                                                            <option name="day" value="${student.day}" selected>${student.day}</option>
                                                            <option name="day" value="1">1</option>
                                                            <option name="day" value="2">2</option>
                                                            <option name="day" value="3">3</option>
                                                            <option name="day" value="4">4</option>
                                                            <option name="day" value="5" >5</option>
                                                            <option name="day" value="6" >6</option>
                                                            <option name="day" value="7" >7</option>
                                                            <option name="day" value="8" >8</option>
                                                            <option name="day" value="9" >9</option>
                                                            <option name="day" value="10">10</option>
                                                            <option name="day" value="11">11</option>
                                                            <option name="day" value="12">12</option>
                                                            <option name="day" value="13">13</option>
                                                            <option name="day" value="14">14</option>
                                                            <option name="day" value="15">15</option>
                                                            <option name="day" value="16">16</option>
                                                            <option name="day" value="17">17</option>
                                                            <option name="day" value="18">18</option>
                                                            <option name="day" value="19">19</option>
                                                            <option name="day" value="20">20</option>
                                                            <option name="day" value="21">21</option>
                                                            <option name="day" value="22">22</option>
                                                            <option name="day" value="23">23</option>
                                                            <option name="day" value="24">24</option>
                                                            <option name="day" value="25">25</option>
                                                            <option name="day" value="26">26</option>
                                                            <option name="day" value="27">27</option>
                                                            <option name="day" value="28">28</option>
                                                            <option name="day" value="29">29</option>
                                                            <option name="day" value="30">30</option>
                                                            <option name="day" value="31">31</option>
                                                        </select>
                                                        <h5 style="color: red">${param.daymsg}</h5> 
                                                    </div>	
                                                    <div class="col-sm-4 form-group">
                                                        <label></label>
                                                        <select class="form-control" name="month">
                                                            <option name="month" value="">Select Months</option>
                                                            <option name="month" value="${student.month}" selected>${student.month}</option>
                                                            <option name="month" value="JANUARY">JANUARY</option>
                                                            <option name="month" value="FEBRUARY">FEBRUARY</option>
                                                            <option name="month" value="MARCH">MARCH</option>
                                                            <option name="month" value="APRIL">APRIL</option>
                                                            <option name="month" value="MAY">MAY</option>
                                                            <option name="month" value="JUNE">JUNE</option>
                                                            <option name="month" value="JULY">JULY</option>
                                                            <option name="month" value="AUGUST">AUGUST</option>
                                                            <option name="month" value="SEPTEMBER">SEPTEMBER</option>
                                                            <option name="month" value="OCTOBER">OCTOBER</option>
                                                            <option name="month" value="NOVEMBER">NOVEMBER</option>
                                                            <option name="month" value="DECEMBER">DECEMBER</option>
                                                        </select>
                                                        <h5 style="color: red">${param.monthmsg}</h5> 
                                                    </div>	
                                                    <div class="col-sm-4 form-group">
                                                        <label></label>
                                                        <select class="form-control" name="year">
                                                            <option name="year" value="">Select Year</option>
                                                            <option name="year" value="${student.year}" selected>${student.year}</option>
                                                            <%
                                                                Calendar c = Calendar.getInstance();
                                                                int yr = c.get(Calendar.YEAR);
                                                                for (int i = 1990; i < yr; i++) {
                                                            %>
                                                            <option name="year" value="<%=i%>"><%=i%></option>
                                                            <%}
                                                            %>
                                                        </select>
                                                        <h5 style="color: red">${param.yearmsg}</h5> 
                                                    </div>	
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-4 form-group">
                                                        <label>Grade</label>
                                                        <select class="form-control" name="grade">
                                                            <option name="grade" value="">--- Select Grade ---</option>
                                                            <option name="grade" value="${student.grade}" selected="selected">${student.grade}</option>
                                                            <option name="grade" value="Play Group">Play Group</option>
                                                            <option name="grade" value="Nursery">Nursery</option>
                                                            <option name="grade" value="LKG">LKG</option>
                                                            <option name="grade" value="UKG">UKG</option>
                                                            <option name="grade" value="1">1</option>
                                                            <option name="grade" value="2">2</option>
                                                            <option name="grade" value="3">3</option>
                                                            <option name="grade" value="4">4</option>
                                                            <option name="grade" value="5">5</option>
                                                            <option name="grade" value="6">6</option>
                                                            <option name="grade" value="7">7</option>
                                                            <option name="grade" value="8">8</option>
                                                            <option name="grade" value="9">9</option>
                                                            <option name="grade" value="10">10</option>
                                                        </select>
                                                        <h5 style="color: red">${param.grademsg}</h5> 
                                                    </div>	
                                                </div>
                                                <div class="form-group">
                                                    <label>Parents name</label>
                                                    <input type="text" placeholder="Enter Parents Name Here.." class="form-control" name="parentname" value="${student.parentname}">
                                                    <h5 style="color: red">${param.parentmsg}</h5> 
                                                </div>				
                                                <div class="form-group">
                                                    <label>Phone Number</label>
                                                    <input type="text" placeholder="Enter Phone Number Here.." class="form-control" name="phone" value="${student.phone}">
                                                    <h5 style="color: red">${param.phonemsg}</h5> 
                                                </div>		
                                                <div class="form-group">
                                                    <label>Email Address</label>
                                                    <input type="text" placeholder="Enter Email Address Here.." class="form-control" name="email" value="${student.email}">
                                                    <h5 style="color: red">${param.emailmsg}</h5> 
                                                </div>	

                                                <div class="form-group">
                                                    <label>Username</label>
                                                    <input type="text" placeholder="Enter Username Name Here.." class="form-control" name="username" value="${student.username}">
                                                    <h5 style="color: red">${param.usernamemsg}</h5> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-4 form-group">
                                                        <label>Password</label>
                                                        <input type="password" placeholder="Enter Password Here" class="form-control" name="password"  value="${student.password}" id="myInput">
                                                        <h5 style="color: red">${param.passwordmsg}</h5> 
                                                    </div>	
                                                    <div class="col-sm-4 form-group">
                                                        <label>Confirm</label>
                                                        <input type="password" placeholder="Enter Password Here" class="form-control" name="repassword" value="${student.password}" >
                                                        <h5 style="color: red">${param.repasswordmsg}</h5> 
                                                        <h5 style="color: red">${param.passwordvalid}</h5> 
                                                    </div>
                                                    <div class="col-sm-8 form-group">
                                                        <label>Show Password</label>
                                                        <input type="checkbox" onClick="myFunction()" >
                                                        
                                                    </div>
                                                    <script>
                                                        function myFunction() {
                                                            var x = document.getElementById("myInput");
                                                            if (x.type === "password") {
                                                                x.type = "text";
                                                            } else {
                                                                x.type = "password";
                                                            }
                                                        }
                                                    </script>
                                                    <div class="col-sm-8 form-group">
                                                        <label>Upload Image</label>
                                                        <input type="file"   name="file">
                                                        <h5 style="color: red">${param.usernamemsg}</h5> 
                                                    </div>

                                                </div>
                                                <input type="Submit" class="btn btn-lg btn-success" value="Submit"/>	
                                                <input type="reset" class="btn btn-lg btn-info" value="Clear"/>
                                                <b><h5 style="color: green;">${param.msg}</h5></b> 
                                            </div>
                                        </form> 
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="studentlist" class="tab-pane fade">
                            <h3>Student List</h3>
                            <div class="container">
                                <div class="table-responsive"> 

                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Photo</th>
                                                <th>Student Name</th>
                                                <th>Class</th>
                                                <th>Student Email</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="stud" items="${studentlist}">
                                                <tr>
                                                    <td>${stud.id}</td>
                                                    <td><img src="${pageContext.servletContext.contextPath}/assets/studentImages/${stud.image}" style="height: 80px;width: 80px;border-radius:10%"></td>
                                                    <td>${stud.firstname}</td>
                                                    <td>${stud.grade}</td>
                                                    <td>${stud.email}</td>
                                                    <td><div class="btn-group">
                                                            <a href="${pageContext.servletContext.contextPath}/view?id=${stud.id}"><button type="button" class="btn btn-primary">View</button> </a>
                                                            <a href="${pageContext.servletContext.contextPath}/edit?id=${stud.id}"><button type="button" class="btn btn-success" >Edit</button></a>
                                                            <a href="${pageContext.servletContext.contextPath}/delete?id=${stud.id}"> <button type="button" class="btn btn-danger">Delete</button></a>
                                                        </div></div> </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
