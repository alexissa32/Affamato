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
<title>User Dashboard Recipes</title>
</head>
  <link type="text/css" rel="stylesheet" href="about.css" />
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
	    <form action="/recipes" method="post">
	      <input type="text" placeholder="Search..." name="search">
	      <button type="submit"><i class="fa fa-search"></i></button>
		    <div style="float:right; color:white; padding-top:10px" id="list1" class="dropdown-check-list" tabindex="100">
		        <span class="anchor">Select Filters</span>
		        <ul class="items">
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
        checkList.getElementsByClassName('anchor')[0].onclick = function (evt) {
            if (checkList.classList.contains('visible'))
                checkList.classList.remove('visible');
            else
                checkList.classList.add('visible');
        }

        checkList.onblur = function(evt) {
            checkList.classList.remove('visible');
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
<%
    } else {
    	response.sendRedirect("/landingPage.jsp");
    }
%>

</body>
</html>