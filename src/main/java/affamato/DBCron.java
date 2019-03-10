package affamato;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

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
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			File dirIngredient = new File("./spoonacularIngredientJSONs/");
			File directoryListing[] = dirIngredient.listFiles();
			if (directoryListing != null) 
			{
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
				    Ingredient ing = new Ingredient(jsonString);
				    ofy().save().entity(ing).now();
				    ofy().load().entity(ing);
				    br.close();			    
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}