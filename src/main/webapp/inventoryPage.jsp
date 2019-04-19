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

<meta name="viewport" content="width=device-width, initial-scale=1">

<style>


</style>

</head>

  <link type="text/css" rel="stylesheet" href="inv.css" />

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
  
  <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#id01" style="margin-bottom: 10px; margin-top: 20px; text-align:right;float:right;" 
  	>Add Ingredient +</button>	
  	
  
  <table id ="inventory_table" class="table table-hover" style="background-color: #eff2f7; width: 900px">
    <thead>
      <tr>
        <th style="width: 330px">Ingredient</th>
        <th>Quantity</th>
        <th style="width: 250px">Expiration Date</th>
        <th><p hidden><i class="fa fa-times-circle" id="exitbutton" aria-hidden="true"></i></p></th>
      </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
  <script type="text/javascript">
  
  	function add() {

        modal.style.display = "none";
  		 var table = document.getElementById("inventory_table");
  		 var row = table.insertRow(-1);
  		
  		// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
  		var cell1 = row.insertCell(0);
  		var cell2 = row.insertCell(1);
  		var cell3 = row.insertCell(2);
  		var cell4 = row.insertCell(3);
  		var exitButton = document.getElementById("exitbutton").cloneNode(true);

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

<!-- Modal -->
<div class="modal fade" id="id01" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <form>
       		<div class="form-group">
        		<label for="IngredientInput">Ingredient</label>
    			<input class="form-control" id="IngredientInput" placeholder="Enter ingredient">
    		</div>
    		
    	
       		<div class="form-group">
        		<div><label for="QuantityInput">Quantity</label></div>
    			<input class="form-control" id="QuantityInput" placeholder="Enter quantity and units"  style="width: 355px; float:left; display: inline">
    		                                      
			  <div class="dropdown" style="display: inline; float:right">
			    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">units
			    <span class="caret"></span></button>
			    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1" >
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">oz</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">lb</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">g</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">kg</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">cups</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">gallons</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">liters</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">fl oz</a></li>
			    </ul>
			  </div>
    		</div>
    		
       		<div class="form-group" style="padding-top: 35px">
        		<label for="ExpirationInput">Expiration Date</label>
    			<input class="form-control" id="ExpirationInput" placeholder="MM/DD/YYYY">
    		</div>
    		    		
        </form>
        <div>
        <button type="button" class="btn btn-danger" style="margin-left: 397px; margin-top: 5px" onClick="add()" >Add</button>

        
        </div>
      </div>
    </div>
  </div>
</div>

<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>



<%
    } else {
    	response.sendRedirect("/landingPage.jsp");
    }
%>



</body>
</html>;