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
<title>User Dashboard Recipes Page</title>
</head>
<%//credit to robschmuecker for code related to dynamic accordion panels 
//http://jsfiddle.net/robschmuecker/m5TMF/163/
//credit to http://jsfiddle.net/evfnLn0x/ for dropdown checkbox filter on navigation bar
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
    <div class="search-container">
	    <form action="/recipes" method="post">
	      <input type="text" placeholder="Search for Recipes..." name="search">
	      <button style="width: 36px; height: 36px" type="submit"><i class="fa fa-search"></i></button>
        
	        <div style="float:right; color:white; padding-top:10px; padding-left:5px; padding-right:5px" id="list1" class="dropdown-check-list" tabindex="100">
        		<span class="anchor">Select Filter</span>
        		<ul id="items" class="items" style="position: absolute; color: black; background-color: white">
		            <li><input type="checkbox" name="veggie"/>Vegetarian </li>
		            <li><input type="checkbox" name="vegan"/>Vegan</li>
		            <li><input type="checkbox" name="glutenf"/>Gluten-Free </li>
		            <li><input type="checkbox" name="keto"/>Ketogenic </li>
		            <li><input type="checkbox" name="dairyf"/>Dairy-Free </li>
		            <li><input type="checkbox" name="quickr"/>Quick Recipe </li>
		            <li><input type="checkbox" name="useinv"/>Use Inventory </li>
		            <li><input type="checkbox" name="useexp"/>Use Expiring Items </li>
        		</ul>
    	     </div>
	        
        </form>
  	</div>

    <script type="text/javascript">
        
        var checkList = document.getElementById('list1');
		var items = document.getElementById('items');
        checkList.getElementsByClassName('anchor')[0].onclick = function (evt) {
            if (items.classList.contains('visible')){
                items.classList.remove('visible');
                items.style.display = "none";
            }
            
            else{
                items.classList.add('visible');
                items.style.display = "block";
            }
        }
        items.onblur = function(evt) {
            items.classList.remove('visible');
        }
        
    </script>
</div>
<div class="vertnav">
<br>
<l>
  <li><a href="dashboardPage.jsp">Welcome</a></li>
  <li><a href="inventoryPage.jsp">My Inventory</a></li>
  <li><a href="grocerylistPage.jsp">My Grocery Lists</a></li>
  <li><a class="active" href="recipesPage.jsp">My Recipes</a></li>
</l>
</div>
<div class="panel-group" id="accordion" style="float: right; padding: 10px; width: 600pt; height: 250pt">
    <div class="panel panel-default">
        <div class="panel-heading"> <span class="glyphicon glyphicon-remove-circle pull-right "></span>

             <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
          Recipe #1
        </a>
      </h4>

        </div>
        <div id="collapseOne" class="panel-collapse collapse ">
            <div class="panel-body"> Get JSON</div>
        </div>
    </div>
    <div class="panel panel-default template">
        <div class="panel-heading"> <span class="glyphicon glyphicon-remove-circle pull-right "></span>

             <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
          Recipe #2 
        </a>
      </h4>

        </div>
        <div id="collapseThree" class="panel-collapse collapse">
            <div class="panel-body">Get JSON</div>
        </div>
    </div>
</div>
<br />

<button style="float:right" class="btn btn-lg btn-primary btn-add-panel"> <i class="glyphicon glyphicon-plus"></i> Discover!</button>

<script>
var $template = $(".template");

var hash = 2;
$(".btn-add-panel").on("click", function () {
    var $newPanel = $template.clone();
    $newPanel.find(".collapse").removeClass("in"); 
    $newPanel.find(".accordion-toggle").attr("href", "#" + (++hash))
    .text("Surprise Recipe #" + hash); 
    
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