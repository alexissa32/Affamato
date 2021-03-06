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
<title>User Dashboard Search Page</title>
</head>
<%//credit to robschmuecker for code related to dynamic accordion panels 
//http://jsfiddle.net/robschmuecker/m5TMF/163/
//credit to http://jsfiddle.net/evfnLn0x/ for dropdown checkbox filter on navigation bar
%>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Rajdhani" rel="stylesheet"> 
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
  <a style="font-family:Lobster;font-size:15pt" class="active" href="dashboardPage.jsp">My Dashboard</a>
  <a style="font-family:Lobster;font-size:15pt" href="landingPage.jsp">Home</a>
  <a style="font-family:Lobster;font-size:15pt" href="aboutPage.jsp">About</a>
  <a style="font-family:Lobster;font-size:15pt;float:right" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Log Out</a>
    <div class="search-container">
	    <form action="/search" method="get">
	      <input type="text" placeholder="Search for Recipes..." name="search">
	      <input type="hidden" name = "type" value = "recipe">
	      <input type="hidden" name="redirect" value="/searchPage.jsp">
	      <button style="width: 36px; height: 36px" type="submit"><i class="fa fa-search"></i></button>
        
	        <div style="float:right; color:white; padding-top:10px; padding-left:5px; padding-right:5px" id="list1" class="dropdown-check-list" tabindex="100">
        		<span class="anchor">Select Filter</span>
        		<ul id="items" class="items" style="position: absolute; color: black; background-color: white">
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

<div class="vertnav" style="top:55px">
<l>
  <li><a style="font-family:Lobster;font-size:15pt" href="dashboardPage.jsp">Welcome</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" href="inventoryPage.jsp">My Inventory</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" href="grocerylistPage.jsp">My Grocery Lists</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" href="recipesPage.jsp">My Recipes</a></li>
  <li><a style="font-family:Lobster;font-size:15pt" class="active" href="searchPage.jsp">Search Recipes</a></li>
</l>
</div>

</br>
</br>
</br>
<div class="panel-group" id="accordion" style="position: absolute;left: 50%;transform: translate(-50%);width: 50%;">
<form action="/recipes" method="get">
<button style="font-family:Lobster;font-size:15pt;position: absolute;left: 50%;transform: translate(-50%);width: 100%;" class="btn btn-lg btn-primary btn-add-panel" type="submit"> <i class="glyphicon glyphicon-plus"></i> Discover!</button>
</form>
</br> 
</br>
</br> 
    <%
    if (cook.getDiscoverResults().length() > 0) {
	pageContext.setAttribute("discoverTitle", cook.getDiscoverResults().getJSONObject(0).getString("title"));
	pageContext.setAttribute("title", cook.getDiscoverResults().getJSONObject(0).getString("title"));
	pageContext.setAttribute("prepMins", cook.getDiscoverResults().getJSONObject(0).getInt("prepMinutes") + "");
	pageContext.setAttribute("cookMins", cook.getDiscoverResults().getJSONObject(0).getInt("cookMinutes") + "");
	pageContext.setAttribute("instructions", cook.getDiscoverResults().getJSONObject(0).getString("instructions"));
	pageContext.setAttribute("num", Integer.toString(0));
	pageContext.setAttribute("ingredients", cook.getDiscoverResults().getJSONObject(0).getJSONArray("ingredients").toString());
	pageContext.setAttribute("link",cook.getDiscoverResults().getJSONObject(0).getString("url"));
	
	String recipe = cook.getDiscoverResults().getJSONObject(0).toString().replaceAll("\"", "|");
	String recipeTitle = cook.getDiscoverResults().getJSONObject(0).getString("title");
	%>
	<div class="panel panel-default">
    <div class="panel-heading"> <!--<span class="glyphicon glyphicon-remove-circle pull-right "></span>-->
    <%
    if( !cook.hasRecipe(recipeTitle)){
    %>
	<form style="display:inline" action="/favorite" method="post">
		 <input type="hidden" id="listID" name="listID" value="1">
		 <input type="hidden" id="sp" name="sp" value="search">
		 <input type="hidden" id="ar" name="ar" value="add">
		 <input type="hidden" class="recipe" name="recipe" value="<%=recipeTitle%>">
		 <button style="display:inline;float:right" class="glyphicon glyphicon-heart-empty pull-right" type="submit"></button>
		 </form>
	<%
	}
	else{
	%>
		<form style="display:inline" action="/favorite" method="post">
		 <input type="hidden" id="sp" name="sp" value="search">
		 <input type="hidden" id="ar" name="ar" value="remove">
		 <input type="hidden" class="recipe" name="recipe" value="<%=recipeTitle%>">
		 <button style="display:inline;float:right" class="glyphicon glyphicon-heart pull-right" type="submit"></button>
		 </form>
	<%
	}
	%>
         <h4 class="panel-title">
    <a style="display:inline;font-family:Lobster;font-size:15pt" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
      Discover!: ${fn:escapeXml(discoverTitle)}  
    </a>
  </h4>

   </div>     
       <div id="collapseThree" class="panel-collapse collapse ">
           <a href="${fn:escapeXml(link)}" style="font-family:Rajdhani">Link to Source Page: ${fn:escapeXml(link)}</a>
           <br>
           <br>
           <p style="font-family:Rajdhani;font-size:12pt">Cooking Time: ${fn:escapeXml(cookMins)}</p>
           <p style="font-family:Rajdhani;font-size:12pt">Prep Time: ${fn:escapeXml(prepMins)}</p>
           <p style="font-family:Rajdhani;font-size:12pt">Instructions: ${fn:escapeXml(instructions)}</p>
           <p style="font-family:Rajdhani;font-size:12pt">Ingredients:</p> 	
            <% 
            //${fn:escapeXml(ingredients)}
            JSONArray ing = cook.getDiscoverResults().getJSONObject(0).getJSONArray("ingredients");
            for(int in = 0; in < ing.length(); in++)
            {
            	String s = ing.getJSONObject(in).getString("originalName");
            	pageContext.setAttribute("s", s);
            	%>
            	<p style="font-family:Rajdhani;font-size:12pt">-${fn:escapeXml(s)}</p>
            	<%
            }
            %>
       </div>
  </div>

<%
    }
    JSONArray ja = cook.getRecipeSearchResults();
    JSONArray favoritedRecipes = cook.getRecipeList();
	int size = ja.length();
	List<String> recipes = new ArrayList<String>();
	for(Integer i = 0; i < ja.length(); i++){
		recipes.add(ja.getJSONObject(i).getString("title"));
		pageContext.setAttribute("title", ja.getJSONObject(i).getString("title"));
		pageContext.setAttribute("prepMins", ja.getJSONObject(i).getInt("prepMinutes") + "");
		pageContext.setAttribute("cookMins", ja.getJSONObject(i).getInt("cookMinutes") + "");
		pageContext.setAttribute("instructions", ja.getJSONObject(i).getString("instructions"));
		pageContext.setAttribute("num", i.toString());
		pageContext.setAttribute("ingredients", ja.getJSONObject(i).getJSONArray("ingredients").toString());
		pageContext.setAttribute("link", ja.getJSONObject(i).getString("url"));
		
		String recipe = ja.getJSONObject(i).toString().replaceAll("\"", "|");
		String recipeTitle = ja.getJSONObject(i).getString("title");
		%>
		
		
		<div class="panel panel-default">
        <div class="panel-heading"> <!--<span class="glyphicon glyphicon-remove-circle pull-right "></span>-->
        <%
        if( !cook.hasRecipe(recipeTitle)){
        %>
		<form style="display:inline" action="/favorite" method="post">
			 <input type="hidden" id="listID" name="listID" value="1">
			 <input type="hidden" id="sp" name="sp" value="search">
			 <input type="hidden" id="ar" name="ar" value="add">
			 <input type="hidden" class="recipe" name="recipe" value="<%=recipeTitle%>">
			 <button style="display:inline;float:right" class="glyphicon glyphicon-heart-empty pull-right" type="submit"></button>
			 </form>
		<%
		}
		else{
		%>
			<form style="display:inline" action="/favorite" method="post">
			 <input type="hidden" id="sp" name="sp" value="search">
			 <input type="hidden" id="ar" name="ar" value="remove">
			 <input type="hidden" class="recipe" name="recipe" value="<%=recipeTitle%>">
			 <button style="display:inline;float:right" class="glyphicon glyphicon-heart pull-right" type="submit"></button>
			 </form>
		<%
		}
		%>
	   
      <h4 class="panel-title">
        <a style="display:inline;font-family:Lobster;font-size:15pt" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse${fn:escapeXml(num)}">
          ${fn:escapeXml(title)}
        </a>
      </h4>

        </div>
        
        	<div id="collapse${fn:escapeXml(num)}" class="panel-collapse collapse ">
            	<a href="${fn:escapeXml(link)}" style="font-family:Rajdhani">Link to Source Page: ${fn:escapeXml(link)}</a>
                <br>
                <br>
            	<p style="font-family:Rajdhani;font-size:12pt">Cooking Time: ${fn:escapeXml(cookMins)}</p>
            	<p style="font-family:Rajdhani;font-size:12pt">Prep Time: ${fn:escapeXml(prepMins)}</p>
            	<p style="font-family:Rajdhani;font-size:12pt">Instructions: ${fn:escapeXml(instructions)}</p>
            	<p style="font-family:Rajdhani;font-size:12pt">Ingredients:</p>
            	
            	<% 
            	//${fn:escapeXml(ingredients)}
            	JSONArray ing = ja.getJSONObject(i).getJSONArray("ingredients");
            	for(int in = 0; in < ing.length(); in++)
            	{
            		String s = ing.getJSONObject(in).getString("originalName");
            		pageContext.setAttribute("s", s);
            		%>
            		<p style="font-family:Rajdhani;font-size:12pt">-${fn:escapeXml(s)}</p>
            		<%
            	}
            	%>
        	</div>
    </div>
		
		<%
		//pageContext.setAttribute("name" + i.toString(), ja.getJSONObject(i).getString("title"));
	}
	pageContext.setAttribute("recipeList", recipes);
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
     
     
    
</div>
<br />

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
function delayedReload() {
  	setTimeout(function(){window.location.reload();}, 3000);
  	return true;
}
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