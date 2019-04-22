package affamato;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import affamato.Recipe;
import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet 
{
	//static 
	//{
	//ObjectifyService.register(Cook.class);
    //   ObjectifyService.register(Recipe.class);
       //ObjectifyService.register(Ingredient.class);
    //}
	private static final Logger log = Logger.getLogger(Cook.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{				
		//ObjectifyService.register(Cook.class);
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();	
		String parameter = req.getParameter("search");
		String type = req.getParameter("type");
		String redirect = req.getParameter("redirect");
		if(redirect == null) redirect = "/landingPage.jsp";
		/*
		boolean vegetarian = Boolean.parseBoolean(req.getParameter("vegetarian"));
		boolean glutenFree = Boolean.parseBoolean(req.getParameter("glutenFree"));
		boolean dairyFree = Boolean.parseBoolean(req.getParameter("dairyFree"));
		boolean ketogenic = Boolean.parseBoolean(req.getParameter("ketogenic"));
		boolean vegan = Boolean.parseBoolean(req.getParameter("vegan"));
		boolean quick = Boolean.parseBoolean(req.getParameter("quick"));
		boolean useInventory = Boolean.parseBoolean(req.getParameter("useInventory"));
		boolean useExpiring = Boolean.parseBoolean(req.getParameter("useExpiring"));
		FilterParameters param = new FilterParameters(vegetarian, glutenFree, dairyFree, ketogenic, vegan, quick, useInventory, useExpiring);
		*/
		//all the parameters directly passed into the constructor
    	if(parameter == null) {
    		resp.getWriter().println("search box passed null?");
    		return;
    	}
		FilterParameters param = null;
		if(type == null) {
			log.info("invalid request. Set type parameter");
			return;
		}
		if(type.equals("recipe")) {
			log.info("recipe");
			param = new FilterParameters( req.getParameter("veggie") == null ? false : true,
					req.getParameter("glutenf") == null ? false : true,
					req.getParameter("dairyf") == null ? false : true,		
					req.getParameter("keto") == null ? false : true,
					req.getParameter("vegan") == null ? false : true,
					req.getParameter("quickr") == null ? false : true,
					req.getParameter("useinv") == null ? false : true,
					req.getParameter("useexp") == null ? false : true
					);
			/*
			param = new FilterParameters(Boolean.parseBoolean(req.getParameter("veggie")), 
					Boolean.parseBoolean(req.getParameter("glutenf")), 
					Boolean.parseBoolean(req.getParameter("dairyf")), 
					Boolean.parseBoolean(req.getParameter("keto")), 
					Boolean.parseBoolean(req.getParameter("vegan")), 
					Boolean.parseBoolean(req.getParameter("quickr")), 
					Boolean.parseBoolean(req.getParameter("useinv")), 
					Boolean.parseBoolean(req.getParameter("useexp"))
					);
					*/
			
			if(param == null) {
				resp.getWriter().println("param not initialized properly");
			}
		}
		else if(type.equals("ingredient")) {
			log.info("ingredient");
			//if any initialization for ingredient is required
		}
		else {
			return;
		}
		Cook cook = Cook.getCook(user);
		//Cookie[] cookies = req.getCookies();
		//String userString = req.getParameter("user");
		//for(int i = 0 ; i < cookies.length ; i++) {
		//	if(cookies[i].getName().equals("user")) {
		//		userString = cookies[i].getValue();
		//		break;
		//	}
		//}
		/*
		Cook cook = null;
		if(userString != null) {
			cook = Cook.getCook(userString);
		}
		*/
		if(cook == null) {
			resp.setContentType("text/plain");
			if(user == null) resp.getWriter().println("user is null");
			else resp.getWriter().println("user is " + user.toString());
			resp.getWriter().println("You don't exist in the data store OR your cookie was not properly initialized. Please log out and log back in on the homepage.");
		} 
		else {
			/*
			List<Recipe> recipes = ObjectifyService.ofy().load().type(Recipe.class).list();
			StringBuilder sb = new StringBuilder();
			
			JSONObject mainObject = new JSONObject();
			JSONArray recipesJSONArray = new JSONArray();
			JSONArray returnArray = new JSONArray();
			
			int recipeCounter = 1;
			int cookieCounter = 1;
			
			for (int i = 0; i < recipes.size(); i++) 
			{
				
				Recipe r = recipes.get(i);
				
				
				if (r.title.toLowerCase().contains(parameter.toLowerCase()) && param.valid(r)) {
					
					if (recipeCounter%5 == 1 && recipeCounter != 1) {
						
						//resp.addCookie(addCookie(mainObject, recipesJSONArray, cookieCounter++));
						//reset cookie and JSON data
						mainObject = new JSONObject();
						recipesJSONArray = new JSONArray();
					}
					recipeCounter++;				
					returnArray.put(new JSONObject(r.jsonString));
					sb.append(r.title);
					sb.append("\n\n");
					recipesJSONArray.put(new JSONObject().put("title", r.title)
							.put("vegetarian", r.vegetarian).put("glutenFree", r.glutenFree)
							.put("dairyFree", r.dairyFree).put("ketogenic", r.ketogenic)
							.put("vegan", r.vegan).put("cookMinutes", r.cookMinutes)
							.put("prepMinutes", r.prepMinutes).put("id", r.id));
					
				}
			}
			
			if (!recipesJSONArray.isEmpty()) {
				resp.addCookie(addCookie(mainObject, recipesJSONArray, cookieCounter));
			}
			cook.setRecipeSearchResults(returnArray);
			ObjectifyService.ofy().save().entity(cook).now();
			*/
			JSONArray ja = null;
			if(type.equals("recipe")) {
				ja = Recipe.searchRecipe(parameter, param, cook);
				cook.setRecipeSearchResults(ja);
			}
			else {
				ja = Ingredient.searchIngredient(parameter);
				cook.setPantrySearchResults(ja);
				//resp.sendRedirect(redirectPage);
			}
			ObjectifyService.ofy().save().entity(cook).now();
			resp.sendRedirect(redirect);
			/*
			resp.setContentType("text/plain");
			resp.getWriter().println("Parameter: " + parameter);
			resp.getWriter().println(ja.toString());
			if(parameter == null) resp.getWriter().println("search parameter null");
			if(cook == null) {
				resp.getWriter().println("null cook");
			}
			else if(cook.user == null) {
				resp.getWriter().println("null user");
			}
			else {
				resp.getWriter().println("Your cook is " + cook.user.toString());
			}
			*/
		}
	}
	
	private Cookie addCookie(JSONObject mainObject, JSONArray recipesJSONArray, int cookieNumber) {
		
		//add current data
		mainObject.put("recipes", recipesJSONArray);
		Cookie cookie = new Cookie("recipes" + cookieNumber, mainObject.toString().replace('"', '^'));
		cookie.setPath("/search");
		return cookie;
		
	}
	
	
}
