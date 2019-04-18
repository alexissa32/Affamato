package affamato;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import affamato.Recipe;
import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet 
{
	static 
	{
       ObjectifyService.register(Recipe.class);
       //ObjectifyService.register(Ingredient.class);
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{				
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		
		//TODO: Get all the parameters to filter later
		String parameter = req.getParameter("q");
		boolean vegetarian = Boolean.parseBoolean(req.getParameter("vegetarian"));
		boolean glutenFree = Boolean.parseBoolean(req.getParameter("glutenFree"));
		boolean dairyFree = Boolean.parseBoolean(req.getParameter("dairyFree"));
		boolean ketogenic = Boolean.parseBoolean(req.getParameter("ketogenic"));
		boolean vegan = Boolean.parseBoolean(req.getParameter("vegan"));
		boolean quick = Boolean.parseBoolean(req.getParameter("quick"));
		boolean useInventory = Boolean.parseBoolean(req.getParameter("useInventory"));
		boolean useExpiring = Boolean.parseBoolean(req.getParameter("useExpiring"));
		FilterParameters param = new FilterParameters(vegetarian, glutenFree, dairyFree, ketogenic, vegan, quick, useInventory, useExpiring);
		
		
		List<Recipe> recipes = ObjectifyService.ofy().load().type(Recipe.class).list();
		StringBuilder sb = new StringBuilder();
		
		JSONObject mainObject = new JSONObject();
		JSONArray recipesJSONArray = new JSONArray();
		
		int recipeCounter = 1;
		int cookieCounter = 1;
		
		for (int i = 0; i < recipes.size(); i++) 
		{
			
			Recipe r = recipes.get(i);
			
			//TODO: May need a better way to filter multiple filters
			if (r.title.toLowerCase().contains(parameter.toLowerCase()) && param.valid(r)) {
				
				if (recipeCounter%5 == 1 && recipeCounter != 1) {
					
					resp.addCookie(addCookie(mainObject, recipesJSONArray, cookieCounter++));
					//reset cookie and JSON data
					mainObject = new JSONObject();
					recipesJSONArray = new JSONArray();
				}
				recipeCounter++;
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
				
		resp.setContentType("text/plain");
		resp.getWriter().println("Parameter: " + parameter);
		resp.getWriter().println(sb.toString());
		

	}
	
	private Cookie addCookie(JSONObject mainObject, JSONArray recipesJSONArray, int cookieNumber) {
		
		//add current data
		mainObject.put("recipes", recipesJSONArray);
		Cookie cookie = new Cookie("recipes" + cookieNumber, mainObject.toString().replace('"', '^'));
		cookie.setPath("/search");
		
		return cookie;
		
	}
	
	
}
