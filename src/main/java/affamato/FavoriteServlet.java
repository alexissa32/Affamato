package affamato;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class FavoriteServlet extends HttpServlet{
	
//POST IS ADDING TO FAVORITES IN DATASTORE
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	     //String ingredient = req.getParameter("ingredient");	     
	     //String title = req.getParameter("discoverTitle");
	     //int prepMinutes = Integer.parseInt(req.getParameter("prepMinutes"));
	     //int cookMinutes = Integer.parseInt(req.getParameter("cookMinutes"));
	     //String instructions = req.getParameter("instructions");
	     
		 UserService userService = UserServiceFactory.getUserService();
	     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	     User user = userService.getCurrentUser();
	     
	     String addOrRemove = req.getParameter("ar");
	     Cook cook = Cook.getCook(user);
	     
	     if(addOrRemove != null) {
		     if(addOrRemove.equals("add")) {
		    	 //Make sure not already in it
		    	 JSONArray meep = cook.getRecipeList();
		    	 boolean okayToAdd = true;
			     for(int index = 0; index < meep.length(); index++)
			     {
			    	if(meep.getString(index).equals(req.getParameter("recipe").toString().replace('|', '"').replaceAll("\\\\", "")))
			    	{
			    		okayToAdd = false;
			    		break;
			    	}
			     } //Good to go, so add
		    	 if(okayToAdd)
		    	 {
		    		 JSONArray searchRecipes = cook.getRecipeSearchResults();
		    		 JSONArray discoverResultsArray = cook.getDiscoverResults();
		    		 if (discoverResultsArray.length() > 0 && discoverResultsArray.getJSONObject(0).toString().contains(req.getParameter("recipe"))) {
		    			 
			    		 cook.addToRecipeList(discoverResultsArray.getJSONObject(0).toString().replaceAll("\\\\", ""));
		    			 
		    		 } else {
		    			 
		    		 for (int index = 0; index < searchRecipes.length(); index++) {
		    			 if (searchRecipes.getJSONObject(index).toString().contains(req.getParameter("recipe"))) {
				    		 cook.addToRecipeList(searchRecipes.getJSONObject(index).toString().replaceAll("\\\\", ""));
		    			 }
		    		 }
		    	}
		    		 //cook.addToRecipeList(req.getParameter("recipe").toString().replace('|', '"').replaceAll("\\\\", ""));
		    	 }
		     }
		     else if(addOrRemove.equals("remove")) {
		    	JSONArray meep = cook.getRecipeList();
		    	for(int index = 0; index < meep.length(); index++)
		    	{
		    		if(meep.getString(index).equals(req.getParameter("recipe").toString().replace('|', '"').replaceAll("\\\\", "")))
		    		{
		    			cook.removeFromRecipeList(index);
		    			break;
		    		}
		    	}
		     }
	     }
	    cook.updateCook();
	    String sp = req.getParameter("sp");
    	if(sp != null && sp.equals("search"))
    	{
    		resp.sendRedirect("/searchPage.jsp");
    	}
    	else
    	{
    		resp.sendRedirect("/recipesPage.jsp");
    	}
    }
    
//USAGE???   
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	     //String ingredient = req.getParameter("ingredient");	     
	     //String title = req.getParameter("discoverTitle");
	     //int prepMinutes = Integer.parseInt(req.getParameter("prepMinutes"));
	     //int cookMinutes = Integer.parseInt(req.getParameter("cookMinutes"));
	     //String instructions = req.getParameter("instructions");	    	
	     
        resp.sendRedirect("/searchPage.jsp");
    }

}
