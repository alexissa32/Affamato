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
		Cookie cookie = new Cookie("test", "hereItIs");
		resp.addCookie(cookie);
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		String parameter = req.getParameter("q");
		
		List<Recipe> recipes = ObjectifyService.ofy().load().type(Recipe.class).list();
		StringBuilder sb = new StringBuilder();
		
		JSONObject mainObject = new JSONObject();
		JSONArray recipesJSONArray = new JSONArray();
		
		for (Recipe r : recipes) 
		{
			if (r.title.toLowerCase().contains(parameter.toLowerCase())) {
				sb.append(r.title);
				sb.append("\n\n");
				recipesJSONArray.put(new JSONObject().put("title", r.title)
						.put("vegetarian", r.vegetarian).put("glutenFree", r.glutenFree)
						.put("dairyFree", r.dairyFree).put("ketogenic", r.ketogenic)
						.put("vegan", r.vegan).put("cookMinutes", r.cookMinutes)
						.put("prepMinutes", r.prepMinutes));
				
			}
		}
		
		mainObject.put("recipes", recipesJSONArray);
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Parameter: " + parameter);
		resp.getWriter().println(sb.toString());
		//resp.addCookie(new Cookie("searchRecipeResult", mainObject.toString().replace('"', '\'')));
		
		
		if (user != null) 
		{
			//---------------------USER-BASED SEARCH aka filtering---------------------\\

			resp.getWriter().println("Hello," + user.getNickname());	
		}
		else 
		{
			//---------------------GENERIC SEARCH---------------------\\
		
			resp.getWriter().println("Hello non-user");
		}	
	}
}
