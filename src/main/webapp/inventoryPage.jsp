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
<title>User Dashboard Inventory</title>
</head>
<%//credit to robschmuecker for code related to making the x button delete a row
//http://jsfiddle.net/robschmuecker/m5TMF/163/
%>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <link type="text/css" rel="stylesheet" href="about.css" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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
	    <form action="/inventory" method="post">
	      <input type="text" placeholder="Search..." name="search">
	      <button style="width: 36px; height: 36px" type="submit"><i class="fa fa-search"></i></button>
	    </form>
  	</div>
</div>
<div class="vertnav">
<br>
<l>
  <li><a href="dashboardPage.jsp">Welcome</a></li>
  <li><a class="active" href="inventoryPage.jsp">My Inventory</a></li>
  <li><a href="grocerylistPage.jsp">My Grocery Lists</a></li>
  <li><a href="recipesPage.jsp">My Recipes</a></li>
</l>
</div>

<div class="container" style="padding-left: 250px; width: 1165px; float: left">
  <h2 style="text-align:left;float:left;">Inventory</h2>
  
  <button type="button" class="btn btn-danger" style="margin-bottom: 10px; margin-top: 20px; text-align:right;float:right;" onclick="add()">Add Ingredient +</button>	
  
  <table id ="inventory_table" class="table table-hover" style="background-color: #eff2f7; width: 900px">
    <thead>
      <tr>
        <th style="width: 330px">Ingredient</th>
        <th>Quantity</th>
        <th style="width: 250px">Expiration Date</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>Anna</td>
        <td>hi</td>
        <td style="width: 10px"><i class="fa fa-times-circle" aria-hidden="true"></i></td>
      </tr>
      <tr>
        <td>2</td>
        <td>Debbie</td>
        <td>boi</td>
        <td style="width: 10px"><i class="fa fa-times-circle" aria-hidden="true"></i></td>
      </tr>
      <tr>
        <td>3</td>
        <td>John</td>
        <td>hoi</td>
        <td style="width: 10px"><i id="hi" class="fa fa-times-circle" aria-hidden="true"></i></td>
      </tr>
    </tbody>
  </table>
  <script type="text/javascript">
  
  	function add() {
  		var table = document.getElementById("inventory_table");
  		// Create an empty <tr> element and add it to the 1st position of the table:
  		var row = table.insertRow(-1);

  		// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
  		var cell1 = row.insertCell(0);
  		var cell2 = row.insertCell(1);
  		var cell3 = row.insertCell(2);
  		var cell4 = row.insertCell(3);
  		var exitButton = document.getElementById("hi").cloneNode(true);

  		// Add some text to the new cells:
  		cell1.innerHTML = "NEW CELL1";
  		cell2.innerHTML = "NEW CELL2";
  		cell3.innerHTML = "bleh";
  		cell4.appendChild(exitButton);
  	}
  </script>
</div>

<script>
$(document).on('click', '.fa-times-circle', function () {
	   $(this).closest('tr').remove()
});
</script> 


<%
    } else {
    	response.sendRedirect("/landingPage.jsp");
    }
%>



</body>
</html>;