package affamato;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import affamato.Recipe;
import affamato.Ingredient;
import static com.googlecode.objectify.ObjectifyService.ofy;


public class SearchServlet extends HttpServlet {
	
	static 
	{
       ObjectifyService.register(Recipe.class);
       //ObjectifyService.register(Ingredient.class);
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		List<Recipe> recipes = ObjectifyService.ofy().load().type(Recipe.class).filter("jsonString contains", "cheese").list();
		StringBuilder sb = new StringBuilder();
		for (Recipe r : recipes) {
			sb.append(r.jsonString);
			sb.append("\n\n");
		}
		
		resp.setContentType("text/plain");
		resp.getWriter().println(sb.toString());

		
		if (user != null) {
			//---------------------USER-BASED SEARCH aka filtering---------------------\\

			resp.getWriter().println("Hello," + user.getNickname());
			
		} else {
			//---------------------GENERIC SEARCH---------------------\\
			
			resp.getWriter().println("Hello non-user");
		}
		
	}
	
}