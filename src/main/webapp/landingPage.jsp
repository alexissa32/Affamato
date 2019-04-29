<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Welcome to Affamato</title>
</head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="about.css" />
<body>
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user == null) {
%>

<div class="topnav">
	<a style="font-family:Lobster;font-size:15pt" class="active">Welcome to Affamato</a>
    <a style="font-family:Lobster;font-size:15pt" href="aboutPage.jsp">About</a>
	<a style="font-family:Lobster;font-size:15pt; float:right" href="<%= userService.createLoginURL(request.getRequestURI()) %>">Log In</a>
</div>

<%
    } else {
    	Cookie cookie = null;
    	Cookie[] cookies = null;
        
    	// Get an array of Cookies associated with the this domain
    	cookies = request.getCookies();
    	if( cookies != null ) {            
            for (int i = 0; i < cookies.length; i++) {
               if(cookies[i].getName().equals("user")){
            	   if(cookies[i].getValue().equals(user.toString())){
            	   		cookie = cookies[i];
            	   }
               }
            }
        }	   	
        if(cookie == null){
        	//response.setHeader("CookHolderName", "${fn:escapeXml(CookHolderName)}");
        	//response.setHeader("CookFlag" , "Cook");
        	response.sendRedirect("/cook?CookFlag=Cook");
        } 
%>

<div class="topnav">
  <a style="font-family:Lobster;font-size:15pt" class="active">Welcome to Affamato</a>
  <a style="font-family:Lobster;font-size:15pt" href="aboutPage.jsp">About</a>
  <a style="font-family:Lobster;font-size:15pt" href="dashboardPage.jsp">My Dashboard</a>
  <a style="font-family:Lobster;font-size:15pt; float:right" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Log Out</a>
</div>

<%
    } 
%>
 <div id="landingbody" style="height:750px">
	<!-- CAROUSEL -->
	<div class="container" style="right:50px;top:100px;position:absolute;width:100px;min-width:50%;height:300px">
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
	        <img src="images/veggies.jpg"  style="width:100%;">
	        <div class="carousel-caption">
	          <h3 style="font-family:Lobster;font-size:30pt">Manage Your Pantry</h3>
	        </div>
	      </div>
	
	      <div class="item">
	        <img src="images/cooking.jpg"  style="width:100%;">
	        <div class="carousel-caption">
	          <h3 style="font-family:Lobster;font-size:30pt">Discover New Recipes</h3>       
	        </div>
	      </div>
	    
	      <div class="item">
	        <img src="images/laptop.jpg"  style="width:100%;">
	        <div class="carousel-caption">
	          <h3 style="font-family:Lobster;font-size:30pt">Optimize Your Savings</h3>  
	        </div>
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
</div>

<!-- DID YOU KNOW PANEL -->
<div class="container-fluid bg-3 text-center" style="background-color:darkred">    
  <h3 style="font-family:Lobster;font-size:25pt;color:white" class="margin">Did You Know?</h3><br>
  <div class="row">
    <div class="col-sm-4">
      <p style="font-family:Lobster;font-size:15pt;color:white">In the United States, food waste is estimated at between 30-40 percent of the food supply.  </p>
      <img src="images/foodwaste.jpg" class="img-responsive margin" style="width:100%" alt="Image">
    </div>
    <div class="col-sm-4"> 
      <p style="font-family:Lobster;font-size:15pt;color:white">In 2010, food loss at the retail and consumer levels corresponded to approximately 133 billion pounds and $161 billion worth of food.</p>
      <img src="images/groceries.jpg" class="img-responsive margin" style="width:100%" alt="Image">
    </div>
    <div class="col-sm-4"> 
      <p style="font-family:Lobster;font-size:15pt;color:white">At Affamato, we don’t just want to offer you a way to save money. We want to help you help the world.</p>
      <img src="images/apple.jpg" class="img-responsive margin" style="width:100%" alt="Image">
    </div>
  </div>
  </br>
</div>

<footer class="container-fluid bg-4 text-center" style="background-color:darkgray">
  <p><a style="font-family:Lobster;font-size:20pt;color:white" href="aboutPage.jsp">Brought to you by Falcon Group</a></p> 
</footer>
</body>
</html>