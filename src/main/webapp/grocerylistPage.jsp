<%@ page language = "java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
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
<%@ page import="java.util.ArrayList" %>
<%@ page import="affamato.Cook" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet"> 
  <link type="text/css" rel="stylesheet" href="about.css" />

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
  <li><a style="font-family:Lobster;font-size:15pt" href="dashboardPage.jsp">Welcome</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" href="inventoryPage.jsp">My Inventory</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" class="active" href="grocerylistPage.jsp">My Grocery Lists</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" href="recipesPage.jsp">My Recipes</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" href="searchPage.jsp">Search Recipes</a></li>
</l>
</div>
 
<!-- THIS IS WHERE THE ACCORDION BEGINs -->
<div class="panel-group" id="accordion" style="float: right; padding: 10px; width: 600pt; height: 250pt">

    <div class="panel panel-default">
        <div class="panel-heading"> <!--  <span class="glyphicon glyphicon-remove-circle pull-right "></span> -->
      <h4 class="panel-title">
        <a style="font-family:Lobster;font-size:15pt" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
          Grocery List #1
        </a>
      </h4>
        </div> 
        <div id="collapseOne" class="panel-collapse collapse ">
        	<ul id="myList1" class="list">
        	
        	<!-- THIS IS WHERE I AM TRYING TO RENDER THE LIST BASED ON DATASTORE -->
				 <%
				 	JSONArray list1 = cook.getGroceryList("1");
				 	for (int i = 1; i < list1.length(); i++) {
				 	   String listItem = list1.getString(i);
				 	   pageContext.setAttribute("item",listItem); 
				 	    %>
		                 <p style="font-family:Lobster;display:inline"><b>${fn:escapeXml(item)}</b></p>
		                 <form style="display:inline" action="/grocerylist" method="get">
			                 <input type="hidden" id="listID" name="listID" value="1">
			                 <input type="hidden" id="ar" name="ar" value="remove">
			                 <input type="hidden" class="ingredient" name="ingredient" value="<%=listItem%>">
			                 <button style="display:inline" type="submit" class="fa fa-times-circle pull-right"></button> 
		                 </form>
		                 <br />	
		                <% 		 	    
				 	}
				%>
				      	
        	<form action="/grocerylist" method="get">
        		<input type="hidden" id="listID" name="listID" value="1">
        		<input type="hidden" id="ar" name="ar" value="add">
        		<input class="ingredient" name = "ingredient" type="text" placeholder="Enter Ingredient"> 
        		<button type="submit"  class="btn btn-danger">Add Ingredient +</button> <!-- removed btn-add-ingredient from class -->
        	</form>
			</ul>
        </div>
    </div>
    
    <div class="panel panel-default template">
        <div class="panel-heading"> <!--  <span class="glyphicon glyphicon-remove-circle pull-right "></span> -->
             <h4 class="panel-title">
        <a style="font-family:Lobster;font-size:15pt" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
          Grocery List #2 
        </a>
      </h4>
        </div>
        <div id="collapseTwo" class="panel-collapse collapse">
        	<ul id="myList2" class="list">
        	
        	<!-- THIS IS WHERE I AM TRYING TO RENDER THE LIST BASED ON DATASTORE -->
				 <%
				 	JSONArray list2 = cook.getGroceryList("2");	 			 	
				 	for (int i = 1; i < list2.length(); i++) {
				 	   String item = list2.getString(i);
				 	   pageContext.setAttribute("item",item); 
				 	    %>
		                 <p style="font-family:Lobster;display:inline"><b>${fn:escapeXml(item)}</b></p>
		                 <form style="display:inline" action="/grocerylist" method="get">
			                 <input type="hidden" id="listID" name="listID" value="2">
			                 <input type="hidden" id="ar" name="ar" value="remove">
			                 <input type="hidden" class="ingredient" name="ingredient" value="<%=item%>">
			                 <button style="display:inline" type="submit" class="fa fa-times-circle pull-right"></button> 
		                 </form>
		                 <br />		
		                <% 	 	    
				 	}
				%>
        	
        	<form action="/grocerylist" method="get">
        	    <input type="hidden" id="listID" name="listID" value="2">
        	    <input type="hidden" id="ar" name="ar" value="add">
        		<input class="ingredient" name = "ingredient" type="text" placeholder="Enter Ingredient"> 
        		<button type="submit"  class="btn btn-danger">Add Ingredient +</button> <!-- removed btn-add-ingredient from class -->
        	</form>
			</ul>
  				<!-- <button type="button"  class="btn btn-danger" data-toggle="modal" data-target="#myModalAdd">Add Ingredient +</button>  -->
        </div>         
    </div>
    
    <div class="panel panel-default">
        <div class="panel-heading"> <!--  <span class="glyphicon glyphicon-remove-circle pull-right "></span> -->
      <h4 class="panel-title">
        <a style="font-family:Lobster;font-size:15pt" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
          Grocery List #3
        </a>
      </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse ">
        	<ul id="myList3" class="list">
        	
          	<!-- THIS IS WHERE I AM TRYING TO RENDER THE LIST BASED ON DATASTORE -->
				 <%
				 	JSONArray list3 = cook.getGroceryList("3");				 	
				 	for (int i = 1; i < list3.length(); i++) {
				 	   String item = list3.getString(i);
				 	   pageContext.setAttribute("item",item); 
				 	    %>
		                 <p style="font-family:Lobster;display:inline"><b>${fn:escapeXml(item)}</b></p>
		                 <form style="display:inline" action="/grocerylist" method="get">
			                 <input type="hidden" id="listID" name="listID" value="3">
			                 <input type="hidden" id="ar" name="ar" value="remove">
			                 <input type="hidden" class="ingredient" name="ingredient" value="<%=item%>">
			                 <button style="display:inline" type="submit" class="fa fa-times-circle pull-right"></button>
		                 </form>
		                 <br />		
		                <% 	 	    
				 	}
				%>
        	
        	<form action="/grocerylist" method="get">
        		<input type="hidden" id="listID" name="listID" value="3">
        		<input type="hidden" id="ar" name="ar" value="add">
        		<input class="ingredient" name = "ingredient" type="text" placeholder="Enter Ingredient"> 
        		<button type="submit"  class="btn btn-danger">Add Ingredient +</button> <!-- removed btn-add-ingredient from class -->
        	</form>
			</ul>
        </div>
    </div>
    
        <div class="panel panel-default">
        <div class="panel-heading"> <!--  <span class="glyphicon glyphicon-remove-circle pull-right "></span> -->
      <h4 class="panel-title">
        <a style="font-family:Lobster;font-size:15pt" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
          Grocery List #4
        </a>
      </h4>
        </div>
        <div id="collapseFour" class="panel-collapse collapse ">
        	<ul id="myList4" class="list">
        	
          	<!-- THIS IS WHERE I AM TRYING TO RENDER THE LIST BASED ON DATASTORE -->
				 <%
				 	JSONArray list4 = cook.getGroceryList("4");				 	
				 	for (int i = 1; i < list4.length(); i++) {
				 	   String item = list4.getString(i);
				 	   pageContext.setAttribute("item",item); 
				 	    %>
		                 <p style="font-family:Lobster;display:inline"><b>${fn:escapeXml(item)}</b></p>
		                 <form style="display:inline" action="/grocerylist" method="get">
			                 <input type="hidden" id="listID" name="listID" value="4">
			                 <input type="hidden" id="ar" name="ar" value="remove">
			                 <input type="hidden" class="ingredient" name="ingredient" value="<%=item%>">
			                 <button style="display:inline" type="submit" class="fa fa-times-circle pull-right"></button>
		                 </form>
		                 <br />	
		                <% 		 	    
				 	}
				%>
        	
        	<form action="/grocerylist" method="get">
        		<input type="hidden" id="listID" name="listID" value="4">
         		<input type="hidden" id="ar" name="ar" value="add">       		
        		<input class="ingredient" name = "ingredient" type="text" placeholder="Enter Ingredient"> 
        		<button type="submit"  class="btn btn-danger">Add Ingredient +</button> <!-- removed btn-add-ingredient from class -->
        	</form>
			</ul>
        </div>
    </div>
    
        <div class="panel panel-default">
        <div class="panel-heading"> <!--  <span class="glyphicon glyphicon-remove-circle pull-right "></span> -->
      <h4 class="panel-title">
        <a style="font-family:Lobster;font-size:15pt" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
          Grocery List #5
        </a>
      </h4>
        </div>
        <div id="collapseFive" class="panel-collapse collapse ">
        	<ul id="myList5" class="list">
        	
          	<!-- THIS IS WHERE I AM TRYING TO RENDER THE LIST BASED ON DATASTORE -->
				 <%
				 	JSONArray list5 = cook.getGroceryList("5");
				 	
				 	for (int i = 1; i < list5.length(); i++) {
				 	   String item = list5.getString(i);
				 	   pageContext.setAttribute("item",item); 
				 	    %>
		                 <p style="font-family:Lobster;display:inline"><b>${fn:escapeXml(item)}</b></p>
		                 <form style="display:inline" action="/grocerylist" method="get">
			                 <input type="hidden" id="listID" name="listID" value="5">
			                 <input type="hidden" id="ar" name="ar" value="remove">
			                 <input type="hidden" class="ingredient" name="ingredient" value="<%=item%>">
			                 <button style="display:inline" type="submit" class="fa fa-times-circle pull-right"></button>
		                 </form>
		                 <br />	
		                <% 		 	    
				 	}
				%>
        	
        	<form action="/grocerylist" method="get">
        		<input type="hidden" id="listID" name="listID" value="5">
        		<input type="hidden" id="ar" name="ar" value="add">        		
        		<input class="ingredient" name = "ingredient" type="text" placeholder="Enter Ingredient"> 
        		<button type="submit"  class="btn btn-danger">Add Ingredient +</button> <!-- removed btn-add-ingredient from class -->
        	</form>
			</ul>
        </div>
    </div>
    
</div>
<!-- THIS IS WHERE THE ACCORDION ENDs -->
<br />

<!--  <button style="float: right" type="button"  class="btn btn-lg btn-primary" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-plus"></i> Add New Grocery List</button> -->

<!-- This modal is for the "enter grocery list name" popup -- leave for now-->
<div class="container">
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

<!-- This modal is for the "add ingredient" popup -- leave for now-->
<div class="container">
  <div class="modal fade" id="myModalAdd" role="dialog">
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Enter Ingredient</h4>
        </div>
        <div class="modal-body">
			<input id="ingredient" type="text" placeholder="Enter Ingredient"> 
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-lg btn-primary btn-add-ingredient" data-dismiss="modal">OK</button>
        </div>
      </div>
      
    </div>
  </div>
</div>

<!-- This script module is for the "add grocery list" button and clones the template of the grocery list accordion -- leave for now-->
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

<p hidden><span class="fa fa-times-circle pull-right" id="exitbutton"></span></p>

<!-- This script module is for the "add ingredient" button which does not communicate with the datastore -- leave for now -->
<script >
$(".btn-add-ingredient").on("click", function () {
     var exitButton = document.getElementById("exitbutton").cloneNode(true);
	 var node = document.createElement("LI");	 
	 var addedIngredient = $(this).siblings('.ingredient').val(); 
	 $(this).siblings('.ingredient').val(''); //reset ingredient add
	 var textnode = document.createTextNode(addedIngredient); 
     node.appendChild(textnode);
     node.appendChild(exitButton);
     //need to add to the add ingredient's parent not the modal button's parent
     $(this).parents('.list').append(node);
     //if you don't want to use the list, and just the panel,
     //$(this).parents('.panel-collapse').append(node);                
});
$(document).on('click', '#exitbutton', function () {
    $(this).parent().remove();
});
</script>   

<%
    } else {
    	response.sendRedirect("/landingPage.jsp");
    }
%>

</body>
</html>