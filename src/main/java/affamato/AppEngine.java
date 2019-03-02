package affamato;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

import java.io.FileNotFoundException;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.googlecode.objectify.ObjectifyService;
import affamato.Recipe;
import affamato.Ingredient;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class AppEngine extends HttpServlet 
{
	static 
	{
       ObjectifyService.register(Recipe.class);
       ObjectifyService.register(Ingredient.class);
    }
	private static boolean databaseUpload = true;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("Hello App Engine!\r\n");
		
		if(databaseUpload)
		{
			File dirIngredient = new File("./spoonacularIngredientJSONs/");
			File directoryListing[] = dirIngredient.listFiles();
			if (directoryListing != null) 
			{
				for (File f : directoryListing) 
				{
					BufferedReader br = new BufferedReader(new FileReader(f)); 
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
			    }
			} 
			else 
			{
			    // Handle the case where dir is not really a directory.
			    // Checking dir.isDirectory() above would not be sufficient
			    // to avoid race conditions with another process that deletes
			    // directories.
			}
			
			//File dirRecipe = new File("./");
			
			databaseUpload = false;
		}
	}
}