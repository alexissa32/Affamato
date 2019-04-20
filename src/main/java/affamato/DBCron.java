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
import com.googlecode.objectify.ObjectifyService;
import affamato.Recipe;
import affamato.Ingredient;
import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class DBCron extends HttpServlet
{
	static 
	{
       ObjectifyService.register(Recipe.class);
       ObjectifyService.register(Ingredient.class);
       ObjectifyService.register(Cook.class);
    }
	
	//private static final Logger _logger = Logger.getLogger(DBCron.class.getName()); //Debug
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			//String path = System.getProperty("java.class.path");
			File dirIngredient = new File("./WEB-INF/classes/affamato/spoonacularIngredientJSONs/"); //("./src/main/java/spoonacularIngredientJSONs/");
			File dirRecipe = new File("./WEB-INF/classes/affamato/recipeJSONs/");
			
			//String current = new File(".").getCanonicalPath(); //Debug
			//_logger.info(current); //Debug
			
			File directoryListing[] = dirIngredient.listFiles();
			File directoryListingTwo[] = dirRecipe.listFiles();
			
			if (directoryListing != null && directoryListingTwo != null) 
			{
				//Clear out the previous cron job's stuff
				List<com.googlecode.objectify.Key<Ingredient>> keys = ofy().load().type(Ingredient.class).keys().list();
				ofy().delete().keys(keys).now();
				
				//Load all the new (And technically old) recipes and ingredients
				for (File f : directoryListing) 
				{
					BufferedReader br;
					br = new BufferedReader(new FileReader(f));
					StringBuilder sb = new StringBuilder();
					String line = br.readLine(); 
					while (line != null) 
					{ 
						sb.append(line);//.append("\n"); 
						line = br.readLine(); 
					}
					String jsonString = sb.toString();
					//_logger.info(jsonString); //Debug
				    Ingredient ing = new Ingredient(jsonString);
				    ofy().save().entity(ing).now();
				    ofy().load().entity(ing);
				    br.close();			    
				} 
				
				List<com.googlecode.objectify.Key<Recipe>> keys2 = ofy().load().type(Recipe.class).keys().list();
				ofy().delete().keys(keys2).now();
				
				for (File f : directoryListingTwo) 
				{
					BufferedReader br;
					br = new BufferedReader(new FileReader(f));
					StringBuilder sb = new StringBuilder();
					String line = br.readLine(); 
					while (line != null) 
					{ 
						sb.append(line);//.append("\n"); 
						line = br.readLine(); 
					}
					String jsonString = sb.toString();
					//_logger.info(jsonString); //Debug
				    Recipe rec = new Recipe(jsonString);
				    ofy().save().entity(rec).now();
				    ofy().load().entity(rec);
				    br.close();			    
				} 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req,resp);
	}
}
