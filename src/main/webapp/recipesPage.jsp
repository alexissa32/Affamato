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
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="affamato.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

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
        Cook cook = Cook.getCook(user);
%>
<div class="topnav">
  <a class="active" href="dashboardPage.jsp">My Dashboard</a>
  <a href="landingPage.jsp">Home</a>
  <a href="aboutPage.jsp">About</a>
  <a style="float:right" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Log Out</a>

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
  <li><a href="searchPage.jsp">Search Recipes</a></li>
</l>
</div>
<div class="panel-group" id="accordion" style="float: right; padding: 10px; width: 600pt; height: 250pt">
    
<%
    JSONArray ja = cook.getRecipeList();
	int size = ja.length();
	List<String> favRecipes = new ArrayList<String>();
	for(Integer i = 0; i < ja.length(); i++){
		favRecipes.add(ja.getString(i));
		pageContext.setAttribute("title", ja.getString(i));
		//pageContext.setAttribute("prepMins", ja.getJSONObject(i).getInt("prepMinutes") + "");
		//pageContext.setAttribute("cookMins", ja.getJSONObject(i).getInt("cookMinutes") + "");
		//pageContext.setAttribute("instructions", ja.getJSONObject(i).getString("instructions"));
		pageContext.setAttribute("num", i.toString());
		pageContext.setAttribute("body", ja.getString(i));
		
		String recipe = ja.getString(i).replaceAll("\"", "|");
		%>
		
		
		<div class="panel panel-default">
        <div class="panel-heading"> <!--<span class="glyphicon glyphicon-remove-circle pull-right "></span>-->

		<form style="display:inline" action="/favorite" method="post">
			 <input type="hidden" id="listID" name="listID" value="1">
			 <input type="hidden" id="ar" name="ar" value="remove">
			 <input type="hidden" class="recipe" name="recipe" value="<%=recipe%>">
			 <button style="display:inline" type="submit" class="fa fa-times-circle pull-right" id="exitbutton"></button>
		</form>

             <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse${fn:escapeXml(num)}">
          ${fn:escapeXml(title)}
        </a>
      </h4>
        </div>
        	<div id="collapse${fn:escapeXml(num)}" class="panel-collapse collapse ">
            	<h3>Body: ${fn:escapeXml(body)}</h3>
        	</div>
    </div>
		
		<%
		//pageContext.setAttribute("name" + i.toString(), ja.getJSONObject(i).getString("title"));
	}
	pageContext.setAttribute("recipeList", favRecipes);
	pageContext.setAttribute("size", ja.length());
%>
    <!-- 
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
    
     -->

<script>
var $template = $(".template");
var hash = 2;
//$(".btn-add-panel").on("click", function () {
//	
	//JSONObject json = Recipe.randomRecipe();
	//String title = json.getString("title");
	//pageContext.setAttribute("randomTitle", title);
	//pageContext.setAttribute("randomJSON", json.toString());
	
	
//    var $newPanel = $template.clone();
  //  $newPanel.find(".collapse").removeClass("in"); 
    //$newPanel.find(".accordion-toggle").attr("href", "#" + (++hash))
    //.text("test"); 
   // .text(${fn:escapeXml(randomTitle)}); 
    
    //$newPanel.find(".panel-body").text(${fn:escapeXml(randomJSON)});
      //$newPanel.find(".panel-body").attr("href", "#" + (++hash)).text("testbody")
    
    //$newPanel.find(".panel-collapse").attr("id", hash);
    //$("#accordion").append($newPanel.fadeIn());
//});
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

