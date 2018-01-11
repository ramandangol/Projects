<%-- 
    Document   : Student_Edit
    Created on : 08-Mar-2017, 18:52:05
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
       <center>
           <%@include file="navigation_admin.jsp" %>
           </center>
           <div class="container">
            <h1 class="well col-sm-8">Student Update Form</h1>
            <div class="col-lg-8 well">
                <div class="row">
                    <form method="post" action="${pageContext.servletContext.contextPath}/update">
                        <div class="col-sm-12">
                             <div class="form-group">
                                <label>ID</label>
                                <input   class="form-control" readonly="readonly" name="id" value="${student.id}">
                                
                            </div>
                            <div class="row">
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
                                    <input type="radio" class="form-group" name="gender" value="male" ${(student.gender).equalsIgnoreCase("male")?"checked":""}>   Male
                                    <input type="radio" class="form-group" name="gender" value="female" ${(student.gender).equalsIgnoreCase("female")?"checked":""}>  Female
                                </div>
                            </div>
                            <div class="row">

                                <div class="col-sm-4 form-group">
                                    <label>Date of birth     </label>
                                    <select class="form-control" name="day" >
                                        <option name="day" value="1" ${(student.day).equalsIgnoreCase("1")?"selected":""}>1</option>
                                        <option name="day" value="2" ${(student.day).equalsIgnoreCase("2")?"selected":""}>2</option>
                                        <option name="day" value="3" ${(student.day).equalsIgnoreCase("3")?"selected":""}>3</option>
                                        <option name="day" value="4" ${(student.day).equalsIgnoreCase("4")?"selected":""}>4</option>
                                        <option name="day" value="5" ${(student.day).equalsIgnoreCase("5")?"selected":""}>5</option>
                                        <option name="day" value="6" ${(student.day).equalsIgnoreCase("6")?"selected":""}>6</option>
                                        <option name="day" value="7" ${(student.day).equalsIgnoreCase("7")?"selected":""}>7</option>
                                        <option name="day" value="8" ${(student.day).equalsIgnoreCase("8")?"selected":""}>8</option>
                                        <option name="day" value="9" ${(student.day).equalsIgnoreCase("9")?"selected":""}>9</option>
                                        <option name="day" value="10" ${(student.day).equalsIgnoreCase("10")?"selected":""}>10</option>
                                        <option name="day" value="11" ${(student.day).equalsIgnoreCase("11")?"selected":""}>11</option>
                                        <option name="day" value="12" ${(student.day).equalsIgnoreCase("12")?"selected":""}>12</option>
                                        <option name="day" value="13" ${(student.day).equalsIgnoreCase("13")?"selected":""}>13</option>
                                        <option name="day" value="14" ${(student.day).equalsIgnoreCase("14")?"selected":""}>14</option>
                                        <option name="day" value="15" ${(student.day).equalsIgnoreCase("15")?"selected":""}>15</option>
                                        <option name="day" value="16" ${(student.day).equalsIgnoreCase("16")?"selected":""}>16</option>
                                        <option name="day" value="17" ${(student.day).equalsIgnoreCase("17")?"selected":""}>17</option>
                                        <option name="day" value="18" ${(student.day).equalsIgnoreCase("18")?"selected":""}>18</option>
                                        <option name="day" value="19" ${(student.day).equalsIgnoreCase("19")?"selected":""}>19</option>
                                        <option name="day" value="20" ${(student.day).equalsIgnoreCase("20")?"selected":""}>20</option>
                                        <option name="day" value="21" ${(student.day).equalsIgnoreCase("21")?"selected":""}>21</option>
                                        <option name="day" value="22" ${(student.day).equalsIgnoreCase("22")?"selected":""}>22</option>
                                        <option name="day" value="23" ${(student.day).equalsIgnoreCase("23")?"selected":""}>23</option>
                                        <option name="day" value="24" ${(student.day).equalsIgnoreCase("24")?"selected":""}>24</option>
                                        <option name="day" value="25" ${(student.day).equalsIgnoreCase("25")?"selected":""}>25</option>
                                        <option name="day" value="26" ${(student.day).equalsIgnoreCase("26")?"selected":""}>26</option>
                                        <option name="day" value="27" ${(student.day).equalsIgnoreCase("27")?"selected":""}>27</option>
                                        <option name="day" value="28" ${(student.day).equalsIgnoreCase("28")?"selected":""}>28</option>
                                        <option name="day" value="29" ${(student.day).equalsIgnoreCase("29")?"selected":""}>29</option>
                                        <option name="day" value="30" ${(student.day).equalsIgnoreCase("30")?"selected":""}>30</option>
                                        <option name="day" value="31" ${(student.day).equalsIgnoreCase("31")?"selected":""}>31</option>
                                        
                                    </select>
                                    <h5 style="color: red">${param.daymsg}</h5> 
                                </div>	
                                <div class="col-sm-4 form-group">
                                    <label></label>
                                    <select class="form-control" name="month">
                                        <option name="month" value="jan" ${(student.month).equalsIgnoreCase("jan")?"selected":""}>JANUARY</option>
                                        <option name="month" value="feb" ${(student.month).equalsIgnoreCase("feb")?"selected":""}>FEBRUARY</option>
                                        <option name="month" value="mar" ${(student.month).equalsIgnoreCase("mar")?"selected":""}>MARCH</option>
                                        <option name="month" value="apr" ${(student.month).equalsIgnoreCase("apr")?"selected":""}>APRIL</option>
                                        <option name="month" value="may" ${(student.month).equalsIgnoreCase("may")?"selected":""}>MAY</option>
                                        <option name="month" value="jun" ${(student.month).equalsIgnoreCase("jun")?"selected":""}>JUNE</option>
                                        <option name="month" value="jul" ${(student.month).equalsIgnoreCase("jul")?"selected":""}>JULY</option>
                                        <option name="month" value="aug" ${(student.month).equalsIgnoreCase("aug")?"selected":""}>AUGUST</option>
                                        <option name="month" value="sep" ${(student.month).equalsIgnoreCase("sep")?"selected":""}>SEPTEMBER</option>
                                        <option name="month" value="oct" ${(student.month).equalsIgnoreCase("oct")?"selected":""}>OCTOBER</option>
                                        <option name="month" value="nov" ${(student.month).equalsIgnoreCase("nov")?"selected":""}>NOVEMBER</option>
                                        <option name="month" value="dec" ${(student.month).equalsIgnoreCase("dec")?"selected":""}>DECEMBER</option>
                                        
                                    </select>
                                    <h5 style="color: red">${param.monthmsg}</h5> 
                                </div>	
                                <div class="col-sm-4 form-group">
                                    <label></label>
                                    <select class="form-control" name="year">
                                         
                                        <option name="year" value="2000" ${(student.year).equalsIgnoreCase("2000")?"selected":""}>2000</option>
                                        <option name="year" value="2001" ${(student.year).equalsIgnoreCase("2001")?"selected":""}>2001</option>
                                        <option name="year" value="2002" ${(student.year).equalsIgnoreCase("2002")?"selected":""}>2002</option>
                                        <option name="year" value="2003" ${(student.year).equalsIgnoreCase("2003")?"selected":""}>2003</option>
                                        <option name="year" value="2004" ${(student.year).equalsIgnoreCase("2004")?"selected":""}>2004</option>
                                        <option name="year" value="2005" ${(student.year).equalsIgnoreCase("2005")?"selected":""}>2005</option>
                                        <option name="year" value="2006" ${(student.year).equalsIgnoreCase("2006")?"selected":""}>2006</option>
                                        <option name="year" value="2007" ${(student.year).equalsIgnoreCase("2007")?"selected":""}>2007</option>
                                        <option name="year" value="2008" ${(student.year).equalsIgnoreCase("2008")?"selected":""}>2008</option>
                                        <option name="year" value="2009" ${(student.year).equalsIgnoreCase("2009")?"selected":""}>2009</option>
                                        <option name="year" value="2010" ${(student.year).equalsIgnoreCase("2010")?"selected":""}>2010</option>
                                        <option name="year" value="2011" ${(student.year).equalsIgnoreCase("2011")?"selected":""}>2011</option>
                                        <option name="year" value="2012" ${(student.year).equalsIgnoreCase("2012")?"selected":""}>2012</option>
                                        <option name="year" value="2013" ${(student.year).equalsIgnoreCase("2013")?"selected":""}>2013</option>
                                        <option name="year" value="2014" ${(student.year).equalsIgnoreCase("2014")?"selected":""}>2014</option>
                                         <option name="year" value="2015" ${(student.year).equalsIgnoreCase("2015")?"selected":""}>2015</option>
                                        <option name="year" value="2016" ${(student.year).equalsIgnoreCase("2016")?"selected":""}>2016</option>
                                        <option name="year" value="2017" ${(student.year).equalsIgnoreCase("2017")?"selected":""}>2017</option>
                                        <option name="year" value="2018" ${(student.year).equalsIgnoreCase("2018")?"selected":""}>2018</option>
                                        <option name="year" value="2019" ${(student.year).equalsIgnoreCase("2019")?"selected":""}>2019</option>
                                    </select>
                                    <h5 style="color: red">${param.yearmsg}</h5> 
                                </div>	


                            </div>
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>Grade</label>
                                    <select class="form-control" name="grade">
                                        <option name="grade" value="">--- Select Grade ---</option>
                                        <option name="grade" value="play" ${(student.grade).equalsIgnoreCase("play")?"selected":""}>Play Group</option>
                                        <option name="grade" value="nursery" ${(student.grade).equalsIgnoreCase("nursery")?"selected":""}>Nursery</option>
                                        <option name="grade" value="lkg" ${(student.grade).equalsIgnoreCase("lkg")?"selected":""}>LKG</option>
                                        <option name="grade" value="ukg" ${(student.grade).equalsIgnoreCase("ukg")?"selected":""}>UKG</option>
                                        <option name="grade" value="1" ${(student.grade).equalsIgnoreCase("1")?"selected":""}>1</option>
                                        <option name="grade" value="2" ${(student.grade).equalsIgnoreCase("2")?"selected":""}>2</option>
                                        <option name="grade" value="3" ${(student.grade).equalsIgnoreCase("3")?"selected":""}>3</option>
                                         <option name="grade" value="4" ${(student.grade).equalsIgnoreCase("4")?"selected":""}>4</option>
                                        <option name="grade" value="5" ${(student.grade).equalsIgnoreCase("5")?"selected":""}>5</option>
                                        <option name="grade" value="6" ${(student.grade).equalsIgnoreCase("6")?"selected":""}>6</option>
                                         <option name="grade" value="7" ${(student.grade).equalsIgnoreCase("7")?"selected":""}>7</option>
                                        <option name="grade" value="8" ${(student.grade).equalsIgnoreCase("8")?"selected":""}>8</option>
                                        <option name="grade" value="9" ${(student.grade).equalsIgnoreCase("9")?"selected":""}>9</option>
                                        <option name="grade" value="10" ${(student.grade).equalsIgnoreCase("10")?"selected":""}>10</option>
                                    </select>
                                    <h5 style="color: red">${param.grademsg}</h5> 
                                </div>	

                            </div>

                            <div class="form-group">
                                <label>Parents name</label>
                                <input type="text" placeholder="Enter Parents Name Here.." class="form-control" name="parent" value="${student.parent}">
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
                                    <input type="password" placeholder="Enter Password Here" class="form-control" name="password" value="${student.password}">
                                    <h5 style="color: red">${param.passwordmsg}</h5> 
                                </div>	
                                <div class="col-sm-4 form-group">
                                    <label>Confirm</label>
                                    <input type="password" placeholder="Enter Password Here" class="form-control" name="repassword" value="${student.password}">
                                    <h5 style="color: red">${param.repasswordmsg}</h5> 
                                    <h5 style="color: red">${param.passwordvalid}</h5> 
                                </div>	

                            </div>
                            <input type="submit" class="btn btn-lg btn-info" value="Update"/>	
                            <input type="reset" class="btn btn-lg btn-info" value="Clear"/>
                            <b><h5 style="color: green;">${param.msg}</h5></b> 
                        </div>
                    </form> 
                </div>
            </div>
        </div>
           
           
           
           
           
           
           
<!--        <h1>Student Update Form</h1>
        <form action="${pageContext.servletContext.contextPath}/update" method="post">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input readonly="readonly" name="id" value="${student.id}"></td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstname" value="${student.firstname}"></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastname" value="${student.lastname}"></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="address" value="${student.address}"></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td><input type="radio" name="gender" value="male" ${(student.gender).equalsIgnoreCase("male")?"checked":""}>Male
                        <input type="radio" name="gender" value="female" ${(student.gender).equalsIgnoreCase("female")?"checked":""}>Female
                    </td>
                </tr>
                <tr>
                    <td>Facalty</td>
                            <td><select name="facalty">
                                    <option></option>
                                    <option name="facalty" value="science" ${(student.facalty).equalsIgnoreCase('science')?"selected":""}>Science</option>
                                    <option name="facalty" value="computer" ${(student.facalty).equalsIgnoreCase('computer')?"selected":""}>Computer</option>
                                    <option name="facalty" value="management" ${(student.facalty).equalsIgnoreCase('management')?"selected":""}>Management</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td><input type="checkbox" name="status" value="1" ${(student.status)==true?"checked":""}></td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" value="${student.username}"</td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password" value="${student.password}"</td>
                </tr>
                <tr>
                    <td></td> 
                    <td><input type="submit" value="Update">
                        <input type="reset" value="Reset">
                    </td>
                </tr>
            </table>
        </form>-->
            ${param.msg}
    
    </body>
</html>
