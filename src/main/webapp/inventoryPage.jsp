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
<%-- <%@ page import="affamato.Cook" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>  --%>
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
        pageContext.setAttribute("user", user);
        //Cook cook = Cook.getCook(user);
        
        //JSONObject test = new JSONObject()
          //      .put("ingredient", "McChicken")
            //    .put("quantity", "2")
              //  .put("expiration", "10/11/12");
        //cook.addToPantry(test);
        //JSONArray pantry = cook.getPantry();
        //pageContext.setAttribute("pantrySize", pantry.length());
        //pageContext.setAttribute("pantry", pantry.toString());]
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
  <h2 style="text-align:left;float:left;">Inventory</h2>
  
  <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#id01" style="margin-bottom: 10px; margin-top: 20px; text-align:right;float:right;" 
  	>Add Ingredient +</button>	
  	
  
  <table id ="inventory_table" class="table table-hover" style="background-color: #eff2f7; width: 900px">
    <thead>
      <tr>
        <th style="font-family:Lobster;width: 330px">Ingredient</th>
        <th style="font-family:Lobster">Quantity</th>
        <th style="font-family:Lobster;width: 250px">Expiration Date</th>
        <th><p hidden=true><i class="fa fa-times-circle" id="exitbutton" aria-hidden="true"></i></p></th>
      </tr>
    </thead>
    <tbody>
     <%
				 	JSONArray list1 = cook.getPantry();
				 	for (int i = 1; i < list1.length(); i++) {
				 	   JSONObject js = list1.getJSONObject(i);
				 	   String ing = js.getString("ingredient");
				 	   String quantity = js.getString("quantity");
				 	   String exp = js.getString("expiration");
				 	   pageContext.setAttribute("ing",ing); 
				 	   pageContext.setAttribute("exp", exp);
				 	   pageContext.setAttribute("quantity", quantity);
				 	    %>
		                 <tr>
		                 <td>
		                 <b>${fn:escapeXml(ing)}</b>
		                 </td>
		                 <td>
		                 ${fn:escapeXml(quantity)}
		                 </td>
		                 <td>
		                 ${fn:escapeXml(exp)}
		                 </td>
		                 <td>
		                 <form style="display:inline" action="/inventory" method="get">
			                 <input type="hidden" id="ar" name="ar" value="remove">
			                 <input type="hidden" class="ingredient" name="ing" value="<%=js%>">
			                 <button style="display:inline" type="submit" class="fa fa-times-circle pull-right"></button> 
		                 </form>
		                 </td>
		                 </tr>
		                <% 		 	    
				 	}
				%>
    </tbody>
  </table>
  <script type="text/javascript">
  //$(document).ready(function(){
	  function add() {
	  
		var table = document.getElementById("inventory_table");
	  	//for(i = 0; i < pantry size use escape xml; i++) {
	  		var row = table.insertRow(-1);
	  		var cell1 = row.insertCell(0);
	  		var cell2 = row.insertCell(1);
	  		var cell3 = row.insertCell(2);
	  		var cell4 = row.insertCell(3);
	  		var exitButton = document.getElementById("exitbutton").cloneNode(true);
	  		
	  		var ingredient = document.getElementById("IngredientInput").value;
	  		var quantity = document.getElementById("QuantityInput").value;
	  		var unit = document.getElementById('dropdowntext').textContent;
	  		var expiration = document.getElementById("ExpirationInput").value;
	  		cell1.innerHTML = quantity;
	  		cell2.innerHTML = quantity + " " + unit;
	  		cell3.innerHTML = expiration;
	  		cell4.appendChild(exitButton);
	  		//cell1.innerHTML = "please help";
	  		//var pantry = pantry size use escape xml;
	  		//var parse = JSON.parse(pantry);
	  		//cell2.innerHTML = pantry;
	  		//cell4.appendChild(exitButton)
	  		
	  		document.getElementById("IngredientInput").value = "";
	  		document.getElementById("QuantityInput").value = "";
	  		document.getElementById('dropdowntext').textContent = "units";
	  		document.getElementById("ExpirationInput").value = "";

	  		//var json = {"ingredient": "bleh", "quantity": quantity, "unit": unit, "expiration": expiration};
	  	//}
  }
	  //) for line 105
  </script>
</div>

<script>	// remove button
$(document).on('click', '.fa-times-circle', function () {
	   $(this).closest('tr').remove()
});
</script> 

<script>	// modal close
// Get the modal
var modal = document.getElementById('id01');
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
    		
<script>	// units
	function units(unit) {
    	document.getElementById('dropdowntext').innerHTML = unit;
    }
</script>

<!-- Modal -->
<div class="modal fade" id="id01" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
  <form action = "/inventory" method = "get">
    <div class="modal-content">
      <div class="modal-body">
       		<div class="form-group">
        		<label for="IngredientInput">Ingredient</label>
    			<input class="form-control" id="IngredientInput" name = "IngredientInput" placeholder="Enter ingredient">
			    
    		<!--  <input class="form-control" id="IngredientInput" placeholder="Enter ingredient"> -->	
    		</div>
    		
    	
       		<div class="form-group">
        		<div><label for="QuantityInput">Quantity</label></div>
    			<input class="form-control" id="QuantityInput" name = "QuantityInput" placeholder="Enter quantity and units"  style="width: 495px; float:left; display: inline">
    		                                      
			  <div class="dropdown" style="display: inline; float:right">
			    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown" style="width: 70px">
			    		<div id="dropdowntext" style="height: 10px ; display: inline">units</div>
			    		<div class="caret" style="position: relative"></div>
			    </button>
			    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1" >
			      <li role="presentation"><a role="menuitem" tabindex="-1" onClick="units('units')">units</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" onClick="units('oz')">oz</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" onClick="units('lb')">lb</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" onClick="units('g')">g</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" onClick="units('kg')">kg</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" onClick="units('cups')">cups</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" onClick="units('liters')">liters</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" onClick="units('fl oz')">fl oz</a></li>
			    </ul>
			  </div>
    		</div>
    		
       		<div class="form-group" style="padding-top: 35px">
        		<label for="ExpirationInput">Expiration Date</label>
    			<input class="form-control" id="ExpirationInput" name = "ExpirationInput" placeholder="MM/DD/YYYY">
    		</div>
    		    		
        <div>
        
        </div>
      </div>
    </div>
    <input type="hidden" id = "ar" name = "ar" value = "add">
    <button type="button" id="boi" class="btn btn-danger" data-dismiss="modal" 
    style="margin-left: 548px; margin-top: 10px" type="submit">Add</button>
      </form>
  </div>
</div>

</body>





<%
    } else {
    	response.sendRedirect("/landingPage.jsp");
    }
%>


</html>