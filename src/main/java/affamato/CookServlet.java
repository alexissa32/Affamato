package affamato;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

public class CookServlet extends HttpServlet{

	static 
	{
		 ObjectifyService.register(Cook.class);
		 ObjectifyService.register(Recipe.class);
	     ObjectifyService.register(Ingredient.class);
	 }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		
		ObjectifyService.register(Cook.class);
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String CookHolderName = user.toString();
        String Param = req.getParameter("type");
        String jsonString = req.getParameter("jsonString");
        List<Cook> Cooks = ObjectifyService.ofy().load().type(Cook.class).list();
        Cook Cook = null;
        for(int i = 0; i < Cooks.size(); i++)
        {
      		if(Cooks.get(i).getCook().equals(user))
      		{
      			Cook = Cooks.get(i);
      		}
      	}
        if(Cook != null) {
        	
        	//for these three need to know how to get ID
        	//as is - need to load ingredient to add in the jsp and send the string
        	//could switch to sending google id and finding the specific recipe/ingredient here
        if(Param.equals("addRecipe")) {
        	Cook.addToRecipeList(jsonString);
        }
        
        else if(Param.equals("addToPatry")) {
        	Cook.addToPantry(jsonString);
        }
        
        else if(Param.equals("addToGroceryList")) {
        	Cook.addToGroceryList(jsonString, 0);
        }
        
        }
        
        
        
		
	}
}
