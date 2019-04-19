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
<title>User Dashboard Grocery Lists</title>
</head>
<%//credit to robschmuecker for code related to dynamic accordion panels 
//http://jsfiddle.net/robschmuecker/m5TMF/163/
%>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <link type="text/css" rel="stylesheet" href="about.css" />

<body id="dashboardbody">
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
        pageContext.setAttribute("user", user);
%>
<div class="topnav">
  <a class="active" href="dashboardPage.jsp">My Dashboard</a>
  <a href="landingPage.jsp">Home</a>
  <a href="aboutPage.jsp">About</a>
  <a style="float:right" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Log Out</a>
  <!--  
    <div class="search-container">
	    <form action="/grocerylist" method="post">
	      <input type="text" placeholder="Search for Ingredients..." name="search"> 
	      <button style="width: 36px; height: 36px" type="submit"><i class="fa fa-search"></i></button>
	    </form>
  	</div>
  	-->
</div>
<div class="vertnav">
<br>
<l>
  <li><a href="dashboardPage.jsp">Welcome</a></li>
  <li><a href="inventoryPage.jsp">My Inventory</a></li>
  <li><a class="active" href="grocerylistPage.jsp">My Grocery Lists</a></li>
  <li><a href="recipesPage.jsp">My Recipes</a></li>
</l>
</div>
 
<div class="panel-group" id="accordion" style="float: right; padding: 10px; width: 600pt; height: 250pt">
    <div class="panel panel-default">
        <div class="panel-heading"> <span class="glyphicon glyphicon-remove-circle pull-right "></span>
 
      <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
          Grocery List #1
        </a>
      </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse ">

            <button type="button"  class="btn btn-danger" data-toggle="modal" data-target="#myModalSearch">Add Ingredient +</button>
            
        </div>
    </div>
    <div class="panel panel-default template">
        <div class="panel-heading"> <span class="glyphicon glyphicon-remove-circle pull-right "></span>
             <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
          Grocery List #2 
        </a>
      </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse">

            <button type="button"  class="btn btn-danger" data-toggle="modal" data-target="#myModalSearch">Add Ingredient +</button>
            
        </div>         
    </div>
</div>

<br />

<div class="container">
<button style="float: right" type="button"  class="btn btn-lg btn-primary" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-plus"></i> Add New Grocery List</button>
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Grocery List Name</h4>
        </div>
        <div class="modal-body">
              <input type="text" id="GLname" placeholder="Name That Grocery List!">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-lg btn-primary btn-add-panel" data-dismiss="modal">OK</button>
        </div>
      </div>
      
    </div>
  </div>
</div>

<div class="container">
  <div class="modal fade" id="myModalSearch" role="dialog">
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Search Affamato</h4>
        </div>
        <div class="modal-body">
        
	        <div class="search-container">
			    <form action="/grocerylist" method="post">
			      <input type="text" placeholder="Search for Ingredients..." name="search"> 
			      <button style="width: 36px; height: 36px" type="submit"><i class="fa fa-search"></i></button>
			    </form>
	  	    </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-lg btn-primary " data-dismiss="modal">OK</button>
        </div>
      </div>
      
    </div>
  </div>
</div>

<script>
var $template = $(".template");

var hash = 2;
$(".btn-add-panel").on("click", function () {
    var $newPanel = $template.clone();
    $newPanel.find(".collapse").removeClass("in");
    
    if (document.getElementById("GLname").value == ""){   
        $newPanel.find(".accordion-toggle").attr("href", "#" + (++hash))
        .text("Grocery List #" + hash); 
    } else {
        $newPanel.find(".accordion-toggle").attr("href", "#" + (++hash)) 
    	.text(document.getElementById("GLname").value); 	
    }
    
    $newPanel.find(".panel-collapse").attr("id", hash);
    $("#accordion").append($newPanel.fadeIn());
});

$(document).on('click', '.glyphicon-remove-circle', function () {
    $(this).parents('.panel').get(0).remove();
});
</script> 
  

<%
    } else {
    	response.sendRedirect("/landingPage.jsp");
    }
%>

</body>
</html>