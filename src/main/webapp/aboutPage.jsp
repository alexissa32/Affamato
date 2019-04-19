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
<html lang="en">
<head>

    <script>
		var justinCommits = 0;
		var juliaCommits = 0;
		var cameronCommits = 0;
		var alexCommits = 0;
		var samirCommits = 0;
		var rooshiCommits = 0;
		
    	var justinIssues = 0;
    	var juliaIssues = 0;
    	var cameronIssues = 0;
    	var alexIssues = 0;
    	var samirIssues = 0;
    	var rooshiIssues = 0;
    	
        const userAction = async () => {

            await fetch('https://api.github.com/repos/alexissa32/Affamato/stats/contributors')
                .then(function(response) {
                    return response.json();
                })
                .then(function(myJson) {
                    document.getElementById("numCommits").innerHTML =
                        "Total Number of Commits: <strong>" + (myJson[0].total+myJson[1].total+myJson[2].total+myJson[3].total+myJson[4].total+myJson[5].total)+ "</strong>";
                    
      				 for( i = 0; i < 6 ; i++){
 						if(myJson[i].author.login == "JLRebello"){
 							juliaCommits = myJson[i].total;
 						} else if (myJson[i].author.login == "sriad123"){
 							samirCommits = myJson[i].total;
 						} else if (myJson[i].author.login == "justinhenry"){
 							justinCommits = myJson[i].total;
 						} else if (myJson[i].author.login == "cameronclark0821"){
 							cameronCommits = myJson[i].total;
 						} else if (myJson[i].author.login == "rooshimadethis"){
 							rooshiCommits = myJson[i].total;
 						} else if (myJson[i].author.login == "alexissa32"){
 							alexCommits = myJson[i].total;
 						}
 					}    
 				 document.getElementById("juliaCommits").innerHTML = "Commits: " + juliaCommits;
 				 document.getElementById("rooshiCommits").innerHTML = "Commits: " + rooshiCommits;
 				 document.getElementById("cameronCommits").innerHTML = "Commits: " + cameronCommits;
 				 document.getElementById("samirCommits").innerHTML = "Commits: " + samirCommits;
 				 document.getElementById("alexCommits").innerHTML = "Commits: " + alexCommits;
 				 document.getElementById("justinCommits").innerHTML = "Commits: " + justinCommits; 
                });
            
            await fetch('https://api.github.com/repos/alexissa32/Affamato/issues')
            .then(function(response) {
                return response.json();
            })
            .then(function(myJson2) {
                document.getElementById("numIssues").innerHTML =
                 "Total Number of Issues: <strong>" + myJson2[0].number + "</strong>";
				 for( i = 0; i < myJson2.length ; i++){
						if(myJson2[i].user.login == "JLRebello"){
							juliaIssues++;
						} else if (myJson2[i].user.login == "sriad123"){
							samirIssues++;
						} else if (myJson2[i].user.login == "justinhenry"){
							justinIssues++;
						} else if (myJson2[i].user.login == "cameronclark0821"){
							cameronIssues++;
						} else if (myJson2[i].user.login == "rooshimadethis"){
							rooshiIssues++;
						} else if (myJson2[i].user.login == "alexissa32"){
							alexIssues++;
						}
					}
				 document.getElementById("juliaIssues").innerHTML = "Issues Raised: " + juliaIssues;
				 document.getElementById("rooshiIssues").innerHTML = "Issues Raised: " + rooshiIssues;
				 document.getElementById("cameronIssues").innerHTML = "Issues Raised: " + cameronIssues;
				 document.getElementById("samirIssues").innerHTML = "Issues Raised: " + samirIssues;
				 document.getElementById("alexIssues").innerHTML = "Issues Raised: " + alexIssues;
				 document.getElementById("justinIssues").innerHTML = "Issues Raised: " + justinIssues;
            });
        };
        userAction();
    </script>

    <!-- Description of the site, its purpose, its intended users
Group name
Group members
For each member: name, photo, bio, major, responsibilities, number of commits, number of issues, number of unit tests
Stats: total number of commits, total number of unit tests, total number of issues
Data: links to the data sources, description of how each was scraped
Tools: tools used, describe their use
Link to the GitHub repo
-->

    <title>Affamato by Team Falcon</title>
</head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
  <link type="text/css" rel="stylesheet" href="about.css" />
<body id="aboutbody" background="white">
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user == null) {
%>
<div class="topnav">
  <a class="active" href="aboutPage.jsp">About</a>
  <a href="landingPage.jsp">Home</a>
  <a style="float:right" href="<%= userService.createLoginURL(request.getRequestURI()) %>">Log In</a>
</div>
<%
    } else {
%>
<div class="topnav">
  <a class="active" href="aboutPage.jsp">About</a>
  <a href="landingPage.jsp">Home</a>
  <a href="dashboardPage.jsp">My Dashboard</a>
  <a style="float:right" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Log Out</a>
</div>
<%
	}
%>
<br>
<h1 style="font-family:TimesNewRoman; color: white; text-shadow: 3px 4px black;">About Affamato:</h1>
<div id="pane2" 	style="height:150px;">
<p>Affamato is a pantry/fridge assistant web application that helps users do a multitude of food related tasks.
    It allows users to create grocery lists, track what items are in their pantry, track perishability of items,
    and recommend recipes based on what's in the pantry, different cuisine types, and different health choices.
    Our goal is to help users save money, reduce their food waste/go green, and broaden their palates.</p>
</div>
<div style="float: center">
  <img src="images/roundLogo.png" height="300px" width="410px" alt=""/>
</div> 
<br>
<br>
<h1 style="font-family:TimesNewRoman; color: white; text-shadow: 3px 4px black;">Team Falcon Members:</h1>
<div id="pane2" style="height:400px;">
<h3>Cameron Clark:</h3>
<img class="two" src="images/cameron.jpg"  width = "210" height = "275" hspace = "20px" lt=""/>
<p><strong>Major:</strong> ECE Major focusing on Software Engineering and Computer Architecture<br />
    <strong>Responsibilities:</strong> Phase 2 Lead, Scraping APIs to Database <br />
    <strong>Bio:</strong> Cameron enjoys taking selfies at the EER.<br />
	<strong id="cameronIssues"></strong><br />
	<strong id="cameronCommits"></strong><br />
    <strong>Unit Tests:</strong> 0</p>
</div>

<div id="pane2" style="height:250px;">
<h3>Justin Henry:</h3>
<img class="two" src="images/justin.png"  hspace = "20px" lt=""/>
<p><strong>Major:</strong> ECE Major focusing on Software Engineering, Design and Energy Systems and Renewable Energy<br />
    <strong>Responsibilities:</strong> Scraping APIs to Database <br />
    <strong>Bio:</strong> Originally from Kansas, Justin enjoys playing basketball and bad puns.<br />
	<strong id="justinIssues"></strong><br />
    <strong id="justinCommits"></strong><br />
    <strong>Unit Tests:</strong> 0</p>
</div>

<div id="pane2" style="height:350px;">
<h3>Alex Issa:</h3>
<img class="two" src="images/alex.jpg"  width = "275" height = "250" hspace = "20px" lt=""/>
<p><strong>Major:</strong> ECE Major focusing on Software Engineering <br />
    <strong>Responsibilities:</strong> Phase 1 Lead, Database and Backend Support <br />
    <strong>Bio:</strong> Alex enjoys complaining about the White House administration and spamming the group Slack at 3am.<br />
	<strong id="alexIssues"></strong><br />
	<strong id="alexCommits"></strong><br />
    <strong>Unit Tests:</strong> 0</p>
</div>    

<div id="pane2" style="height:300px;">
<h3>Julia Rebello:</h3>
<img class="two" src="images/julia.jpg" hspace = "20px" alt=""/>
<p><strong>Major:</strong> ECE Major focusing on Software Engineering <br />
    <strong>Responsibilities:</strong> Front End and General Support <br />
    <strong>Bio:</strong> Born in Rio de Janeiro, Julia loves beaches and warm weather.<br />
	<strong id="juliaIssues"></strong><br />
	<strong id="juliaCommits"></strong><br />
    <strong>Unit Tests:</strong> 0</p>
</div> 

<div id="pane2" style="height:265px;">
<h3>Samir Riad:</h3>
<img class="two" src="images/samir.jpg"  hspace = "20px" alt=""/>
<p><strong>Major:</strong> ECE Major focusing on Software Engineering <br />
    <strong>Responsibilities:</strong> Front End <br />
    <strong>Bio:</strong> Samir likes to party responsibly.<br />
	<strong id="samirIssues"></strong><br />
	<strong id="samirCommits"></strong><br />
    <strong>Unit Tests:</strong> 0</p>
</div>

<div id="pane2" style="height:350px;">
<h3>Rooshi Patidar:</h3>
<img class="two" src="images/rooshi.jpg"  width = "275" height = "250" hspace = "20px" alt=""/>
<p><strong>Major:</strong> Business and ECE Major focusing on Software Engineering <br />
    <strong>Responsibilities:</strong> Java Spring Backend <br />
    <strong>Bio:</strong> Rooshi is a black belt in Android Studio.<br />
	<strong id="rooshiIssues"></strong><br />
	<strong id="rooshiCommits"></strong><br />
    <strong>Unit Tests:</strong> 0</p>
</div>

<div id="pane2" style="height:180px;">
<h3>Repository Statistics:</h3>

<h4 align="left" id="numCommits"></h4>
<h4 align="left" id="numIssues"></h4>
<h4 align="left">Total Number of Unit Tests: 0</h4>
</div>

<div id="pane2" style="height:175px;">
<h3>Data:</h3>
<p><a href="https://developer.yummly.com">Yummly API</a></p>
<p><a href="https://spoonacular.com/food-api">Spoonacular API</a></p>
<p>Yummly and Spoonacular were scraped using Python scripts written by Team Falcon</p>
</div>

<div id="pane2" style="height:120px;">
<h3>Github Repository:</h3>
<p><a href="https://github.com/alexissa32/Affamato">Github</a></p>
</div>

<div id="pane2" style="height:200px;">
<h3>Tools:</h3>
<p>In this phase, we obtained a URL for Affamato from Namecheap and set up team 
communication on Slack, which has been integrated with the Affamato Github repository. 
Phase One user stories have all been added to our issue board. Our user interface has been 
developed with DHTML, CSS, reactjs and bootstrap. Our database is set up as a Google Cloud Platform 
Datastore. We have implemented scraping with Python scripts from Spoonacular and Yummly APIs. 
So far we have scraped over 900 ingredients and 600 recipes.</p>
</div>

<div id="status"></div>
</body>
</html>