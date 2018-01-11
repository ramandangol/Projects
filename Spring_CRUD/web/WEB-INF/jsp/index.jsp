<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Welcome to Spring Web MVC project</title>
    </head>
 <body>
    <center>
        <%@include file="navigation_home.jsp" %>
        <div class="container">

            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="assets/images/school1.jpg" alt="Los Angeles" style="width:100%; width: 100%; ">
                    </div>

                    <div class="item">
                        <img src="assets/images/library.jpg" alt="Chicago" style="width:100%;">
                    </div>

                    <div class="item">
                        <img src="assets/images/computerlab.jpg" alt="New york" style="width:100%;">
                    </div>
                </div>
 <!-- Left and right controls -->
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </center>
</body>
</html>
