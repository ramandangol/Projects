<%-- 
    Document   : student_register
    Created on : 02-Mar-2017, 12:54:36
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
        <center><%@include file="navigation_admin.jsp" %></center>
        <div class="container">
            <h1 class="well col-sm-8">Student Registration Form</h1>
            <div class="col-lg-8 well">
                <div class="row">
                    <form method="post" action="${pageContext.servletContext.contextPath}/stdRegist">
                        <div class="col-sm-12">
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label>First Name</label>
                                    <input type="text" placeholder="Enter First Name Here.." class="form-control" name="firstname">
                                    <h5 style="color: red">${param.firstnamemsg}</h5> 
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label>Last Name</label>
                                    <input type="text" placeholder="Enter Last Name Here.." class="form-control" name="lastname">
                                    <h5 style="color: red">${param.lastnamemsg}</h5> 
                                </div>
                            </div>					
                            <div class="form-group">
                                <label>Address</label>
                                <input type="text" placeholder="Enter Address Here.." class="form-control" name="address">
                                <h5 style="color: red">${param.addressmsg}</h5> 
                            </div>	
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>City</label>
                                    <input type="text" placeholder="Enter City Name Here.." class="form-control" name="city">
                                    <h5 style="color: red">${param.citymsg}</h5> 
                                </div>	
                                <div class="col-sm-4 form-group">
                                    <label>State</label>
                                    <input type="text" placeholder="Enter State Name Here.." class="form-control" name="state">
                                    <h5 style="color: red">${param.statemsg}</h5> 
                                </div>	
                                <div class="col-sm-4 form-group">
                                    <label>Zip</label>
                                    <input type="text" placeholder="Enter Zip Code Here.." class="form-control" name="zip">
                                    <h5 style="color: red">${param.zipmsg}</h5> 
                                    <h5 style="color: red">${param.szipmsg}</h5> 
                                </div>		
                            </div>
                            <div class="row">
                                <div class="col-sm-12 form-group">
                                    <label>Gender</label>
                                    <input type="radio" class="form-group" name="gender" value="male" checked>   Male
                                    <input type="radio" class="form-group" name="gender" value="female">  Female
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>Date of birth     </label>
                                    <select class="form-control" name="day" >
                                        <option name="day" value="">Select Day</option>
                                        <option name="day" value="1">1</option>
                                        <option name="day" value="2">2</option>
                                        <option name="day" value="3">3</option>
                                        <option name="day" value="4">4</option>
                                        <option name="day" value="5">5</option>
                                        <option name="day" value="6">6</option>
                                        <option name="day" value="7">7</option>
                                        <option name="day" value="8">8</option>
                                        <option name="day" value="9">9</option>
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
                                        <option name="month" value="jan">JANUARY</option>
                                        <option name="month" value="feb">FEBRUARY</option>
                                        <option name="month" value="mar">MARCH</option>
                                        <option name="month" value="apr">APRIL</option>
                                        <option name="month" value="may">MAY</option>
                                        <option name="month" value="jun">JUNE</option>
                                        <option name="month" value="jul">JULY</option>
                                        <option name="month" value="aug">AUGUST</option>
                                        <option name="month" value="sep">SEPTEMBER</option>
                                        <option name="month" value="oct">OCTOBER</option>
                                        <option name="month" value="nov">NOVEMBER</option>
                                        <option name="month" value="dec">DECEMBER</option>
                                    </select>
                                    <h5 style="color: red">${param.monthmsg}</h5> 
                                </div>	
                                <div class="col-sm-4 form-group">
                                    <label></label>
                                    <select class="form-control" name="year">
                                        <option name="year" value="">Select Year</option>
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
                                        <option name="grade" value="play">Play Group</option>
                                        <option name="grade" value="nursery">Nursery</option>
                                        <option name="grade" value="lkg">LKG</option>
                                        <option name="grade" value="ukg">UKG</option>
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
                                <input type="text" placeholder="Enter Parents Name Here.." class="form-control" name="parent">
                                <h5 style="color: red">${param.parentmsg}</h5> 
                            </div>				
                            <div class="form-group">
                                <label>Phone Number</label>
                                <input type="text" placeholder="Enter Phone Number Here.." class="form-control" name="phone">
                                <h5 style="color: red">${param.phonemsg}</h5> 
                            </div>		
                            <div class="form-group">
                                <label>Email Address</label>
                                <input type="text" placeholder="Enter Email Address Here.." class="form-control" name="email">
                                <h5 style="color: red">${param.emailmsg}</h5> 
                            </div>	

                            <div class="form-group">
                                <label>Username</label>
                                <input type="text" placeholder="Enter Username Name Here.." class="form-control" name="username">
                                <h5 style="color: red">${param.usernamemsg}</h5> 
                            </div>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>Password</label>
                                    <input type="password" placeholder="Enter Password Here" class="form-control" name="password">
                                    <h5 style="color: red">${param.passwordmsg}</h5> 
                                </div>	
                                <div class="col-sm-4 form-group">
                                    <label>Confirm</label>
                                    <input type="password" placeholder="Enter Password Here" class="form-control" name="repassword">
                                    <h5 style="color: red">${param.repasswordmsg}</h5> 
                                    <h5 style="color: red">${param.passwordvalid}</h5> 
                                </div>	

                            </div>
                            <input type="Submit" class="btn btn-lg btn-info" value="Submit"/>	
                            <input type="reset" class="btn btn-lg btn-info" value="Clear"/>
                            <b><h5 style="color: green;">${param.msg}</h5></b> 
                        </div>
                    </form> 
                </div>
            </div>
        </div>
    </body>
    </html>
