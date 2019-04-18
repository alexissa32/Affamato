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
        const userAction = async () => {

            await fetch('https://api.github.com/repos/alexissa32/Affamato/stats/contributors')
                .then(function(response) {
                    return response.json();
                })
                .then(function(myJson) {
                    document.getElementById("numCommits").innerHTML =
                        "The Affamato repository has a total of <strong>" + (myJson[0].total+myJson[1].total+myJson[2].total+myJson[3].total+myJson[4].total+myJson[5].total) + "</strong> user commits.";
                    document.getElementById("0Commits").innerHTML =
                        myJson[0].author.login + " has  <strong>" + myJson[0].total + "</strong> commits";
                    document.getElementById("1Commits").innerHTML =
                        myJson[1].author.login + " has  <strong>" + myJson[1].total + "</strong> commits";
                    document.getElementById("2Commits").innerHTML =
                        myJson[2].author.login + " has  <strong>" + myJson[2].total + "</strong> commits";
                    document.getElementById("3Commits").innerHTML =
                        myJson[3].author.login + " has  <strong>" + myJson[3].total + "</strong> commits";
                    document.getElementById("4Commits").innerHTML =
                        myJson[4].author.login + " has  <strong>" + myJson[4].total + "</strong> commits";
                    document.getElementById("5Commits").innerHTML =
                        myJson[5].author.login + " has  <strong>" + myJson[5].total + "</strong> commits";
                });
            await fetch('https://api.github.com/repos/alexissa32/Affamato/issues')
            .then(function(response) {
                return response.json();
            })
            .then(function(myJson2) {
                document.getElementById("numIssues").innerHTML =
                    "The Affamato repository has a total of <strong>" + myJson2[0].number + "</strong> user issues open.";

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
<p><strong>Major:</strong> ECE major with Software Engineering and Computer Architecture Cores <br />
    <strong>Responsibilities:</strong> Phase 2 Lead, Scraping APIs to Database <br />
    <strong>Bio:</strong> Cameron enjoys taking selfies at the EER.<br />
    <strong>Unit Tests:</strong> 0</p>
</div>

<div id="pane2" style="height:250px;">
<h3>Justin Henry:</h3>
<img class="two" src="images/justin.png"  hspace = "20px" lt=""/>
<p><strong>Major:</strong> Electrical and Computer Engineering - Software Engineering and Design + Energy Systems and Renewable Energy <br />
    <strong>Responsibilities:</strong> Scraping APIs to Database <br />
    <strong>Bio:</strong> Originally from Kansas, Justin enjoys playing basketball and bad puns.<br />
    <strong>Unit Tests:</strong> 0</p>
</div>

<div id="pane2" style="height:350px;">
<h3>Alex Issa:</h3>
<img class="two" src="images/alex.jpg"  width = "275" height = "250" hspace = "20px" lt=""/>
<p><strong>Major:</strong> ECE major with Software Engineering Core<br />
    <strong>Responsibilities:</strong> Phase 1 Lead, Database and Backend Support <br />
    <strong>Bio:</strong> Alex enjoys complaining about the White House administration and spamming the group Slack at 3am.<br />
    <strong>Unit Tests:</strong> 0</p>
</div>    

<div id="pane2" style="height:300px;">
<h3>Julia Rebello:</h3>
<img class="two" src="images/julia.jpg" hspace = "20px" alt=""/>
<p><strong>Major:</strong> ECE major with Software Engineering primary tech core <br />
    <strong>Responsibilities:</strong> Front End and General Support <br />
    <strong>Bio:</strong> Born in Rio de Janeiro, Julia loves beaches and warm weather.<br />
    <strong>Unit Tests:</strong> 0</p>
</div> 

<div id="pane2" style="height:265px;">
<h3>Samir Riad:</h3>
<img class="two" src="images/samir.jpg"  hspace = "20px" alt=""/>
<p><strong>Major:</strong> ECE major with Software Engineering primary tech core <br />
    <strong>Responsibilities:</strong> Front End <br />
    <strong>Bio:</strong> Samir likes to party responsibly.<br />
    <strong>Unit Tests:</strong> 0</p>
</div>

<div id="pane2" style="height:350px;">
<h3>Rooshi Patidar:</h3>
<img class="two" src="images/rooshi.jpg"  width = "275" height = "250" hspace = "20px" alt=""/>
<p><strong>Major:</strong> ECE Software Engineering and Business Major <br />
    <strong>Responsibilities:</strong> Java Spring Backend <br />
    <strong>Bio:</strong> Rooshi is a black belt in Android Studio.<br />
    <strong>Unit Tests:</strong> 0</p>
</div>

<div id="pane2" style="height:450px;">
<h3>Statistics:</h3>
<h4 align="left" id="0Commits"></h4>
<h4 align="left" id="1Commits"></h4>
<h4 align="left" id="2Commits"></h4>
<h4 align="left" id="3Commits"></h4>
<h4 align="left" id="4Commits"></h4>
<h4 align="left" id="5Commits"></h4>

<h3 align="left" id="numCommits"></h3>
<h3 align="left" id="numIssues"></h3>
<h3 align="left">Total Number of Unit Tests: 0</h3>
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