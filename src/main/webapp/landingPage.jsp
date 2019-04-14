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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Welcome to Affamato</title>
</head>
  <link type="text/css" rel="stylesheet" href="about.css" />
<body>
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user == null) {
%>
<div class="topnav">
  <a class="active">Welcome to Affamato</a>
  <a href="aboutPage.jsp">About</a>
  <!-- 
  <a style="float:right" href="<%//= userService.createLoginURL(request.getRequestURI()) %>">Log In</a>
   -->
  <form style="float:right" action="/cook" method="post">

      <div><input type="submit" name="submit" value="Log In" /></div>

      <input type="hidden" name="CookHolderName" value="${fn:escapeXml(CookHolderName)}"/>

	  <input type="hidden" name="CookFlag" value= "Cook"/>
	
  </form>
  
</div>
<br>
<br>
<div id="pane" 	style="height:300px; width:400px; float: right; padding-top: 50px; padding-bottom: 80px; padding-right: 60px">
<h1>Manage Your Pantry</h1>
<h1>Discover New Recipes</h1>
<h1>Optimize Your Savings</h1>
</div>
<%
    } else {
    	
%>
<div class="topnav">
  <a class="active">Welcome to Affamato</a>
  <a href="aboutPage.jsp">About</a>
  <a href="dashboardPage.jsp">My Dashboard</a>
  <a style="float:right" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Log Out</a>
</div>
<br>
<br>
<div id="pane" 	style="height:300px; width:400px; float: right; padding-top: 50px; padding-bottom: 80px; padding-right: 60px">
<h1>Manage Your Pantry</h1>
<h1>Discover New Recipes</h1>
<h1>Optimize Your Savings</h1>
</div>

<%
    } 
%>
</body>
</html>