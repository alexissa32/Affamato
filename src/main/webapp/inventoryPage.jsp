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
<%@ page import="affamato.Cook" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard Inventory</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
.modal-dialog {
    transform: translate(0, -50%);
    top: 15%;
    margin: 0 auto;
}
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
  <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">

<body id="dashboardbody">
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
	 	Cook cook = Cook.getCook(user);
        pageContext.setAttribute("user", user);
%>
<div class="topnav">
  <a style="font-family:Lobster;font-size:15pt" class="active" href="dashboardPage.jsp">My Dashboard</a>
  <a style="font-family:Lobster;font-size:15pt" href="landingPage.jsp">Home</a>
  <a style="font-family:Lobster;font-size:15pt" href="aboutPage.jsp">About</a>
  <a style="font-family:Lobster;font-size:15pt;float:right" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Log Out</a>
</div>
<div class="vertnav">
<br>
<l>
  <li><a style="font-family:Lobster;font-size:15pt" href="dashboardPage.jsp">Welcome</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" class="active" href="inventoryPage.jsp">My Inventory</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" href="grocerylistPage.jsp">My Grocery Lists</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" href="recipesPage.jsp">My Recipes</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" href="searchPage.jsp">Search Recipes</a></li>
</l>
</div>

<div class="container" style="font-family:Lobster;font-size:15pt;padding-left: 250px; width: 1165px; float: left">
  <h1 style="text-align:left;float:left;">Inventory</h1>
  <!--  
    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#id01" style="margin-bottom: 10px; margin-top: 20px; text-align:right;float:right;" 
  	>Add Ingredient +</button>
  	-->
  <!--  
  <table id ="inventory_table" class="table table-hover" style="background-color: #eff2f7; width: 900px">
    <thead>
      <tr>
        <th style="font-family:Lobster;width: 330px">Ingredient</th>
        <th style="font-family:Lobster">Quantity</th>
        <th style="font-family:Lobster;width: 250px">Expiration Date</th>
      </tr>
    </thead>


  </table> 	-->	
</div>
      <!-- THIS IS WHERE I AM TRYING TO RENDER THE LIST BASED ON DATASTORE -->
<div class="container" style="width: 75%; float: right">   
<h2 style="font-family:Lobster;font-size:20pt;float:left">Ingredient--------Quantity--------Expiration Date</h2> <br />     
		  <%
			JSONArray inventory = cook.getPantry();
			for (int i = 0; i < inventory.length(); i++) {
				 JSONObject listItem = inventory.getJSONObject(i);
				 String ingredient1 = listItem.getString("ingredient");
				 String quantity1 = listItem.getString("quantity");
				 String expiration1 = listItem.getString("expiration");
				 String units1 = listItem.getString("units");
				 pageContext.setAttribute("ingredient",ingredient1); 
				 pageContext.setAttribute("quantity",quantity1);
				 pageContext.setAttribute("expiration",expiration1);
				 pageContext.setAttribute("units",units1);
			%>
	 <!-- THIS IS WHERE WE NEED TO ADD STUFF TO THE TABLE -->
	 
	 	 	<!--  var table = document.getElementById("inventory_table");	
	 	 	var row = table.insertRow(0);
	 	 	var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			cell1.innerHTML = "NEW CELL1";
			cell2.innerHTML = "NEW CELL2";
			cell3.innerHTML = "NEW CELL 3";
			cell4.innerHTML = "NEW CELL 4";-->
			
		    <p style="font-family:Lobster;font-size:15pt;display:inline"><b>${fn:escapeXml(ingredient)}</b></p>
		    <p style="font-family:Lobster;font-size:15pt;display:inline"><b>${fn:escapeXml(quantity)}</b></p>
            <p style="font-family:Lobster;font-size:15pt;display:inline"><b>${fn:escapeXml(units)}</b></p>     
		    <p style="font-family:Lobster;font-size:15pt;display:inline"><b>${fn:escapeXml(expiration)}</b></p>
		    
	 <!-- THIS IS WHERE WE NEED TO ADD A REMOVE BUTTON FOR EACH ELEMENT -->	    
		    <form style="display:inline" action="/inventory" method="post">
			      <input type="hidden" name="ar" value="remove">
			      <input type="hidden" name="IngredientInput" value="<%=ingredient1%>">
			      <input type="hidden" name="QuantityInput" value="<%=quantity1%>">
			      <input type="hidden" name="ExpirationInput" value="<%=expiration1%>">
			      <input type="hidden" name="UnitsInput" value="<%=units1%>">
			      <button style="display:inline" type="submit" class="fa fa-times-circle pull-right"></button> 
		    </form>
		    <br />	
		    <% 		 	    
		}
		%>
</div> 
  <br />
  <br />
    <form style="float:right"action="/inventory" method="post">
    	<input style="display:inline" name="IngredientInput" type="text" placeholder="Enter Ingredient"> 
    	<input style="display:inline" name="QuantityInput" type="text" placeholder="Enter Quantity"> 
    	<input style="display:inline" name="ExpirationInput" type="text"  placeholder="Expiration dd/mm/yyyy">
		<input style="display:inline" name="UnitsInput" type="text"  placeholder="Enter Units">	    	
    	<input name="ar" type="hidden" value="add">
  		<button type = "submit" class = "btn btn-danger" style="display:inline;font-family:Lobster">Add Ingredient +</button>  	  
    </form> 
    

<!--   Modal
<div class="modal fade" id="id01" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-body"> 
        <form action="/inventory" method="post">
       		<div class="form-group">
        		<label for="IngredientInput">Ingredient</label>
    			<input class="form-control" name="IngredientInput" placeholder="Enter ingredient">
			</div>    		
    	
       		<div class="form-group">
        		<div><label for="QuantityInput">Quantity</label></div>
    			<input class="form-control" name="QuantityInput" placeholder="Enter quantity"  style="width: 495px; float:left; display: inline">		                                     
    		</div>
    		
       		<div class="form-group">
        		<div><label for="UnitsInput">Units</label></div>
    			<input class="form-control" name="UnitsInput" placeholder="Enter units"  style="width: 495px; float:left; display: inline">		                                     
    		</div>    
    				
       		<div class="form-group" style="padding-top: 35px">
        		<label for="ExpirationInput">Expiration Date</label>
    			<input class="form-control" name="ExpirationInput" placeholder="MM/DD/YYYY">
    		</div>
       		<button type="submit" class="btn btn-danger">Add</button> 		    		
        </form>
        <div>
        
        </div>
      </div>
    </div>
  </div>
</div>
-->
<%
    } else {
    	response.sendRedirect("/landingPage.jsp");
    }
%>


</body>
</html>