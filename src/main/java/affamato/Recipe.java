//@Author Alex Issa
package affamato;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Recipe implements Comparable<Recipe> 
{
    @Id Long id; //The @Id field 	can be of type Long, long, or String. 
    //If you use Long and save an entity with a null id, a numeric value will be 
    //generated for you using the standard GAE allocator for this kind. If you use 
    //String or the primitive long type, values will never be autogenerated.
    @Index String jsonString;
    @Index String title;
    @Index Boolean vegetarian;
    @Index Boolean glutenFree;
    @Index Boolean dairyFree;
    @Index Boolean ketogenic;
    @Index Boolean vegan;
    @Index Integer cookMinutes;
    @Index Integer prepMinutes;
    @Index String instructions;
    
    private Recipe() {}
    public Recipe(String json) 
    {
    	jsonString = json;
    	JSONObject jo = null;
    	try 
    	{
			jo = new JSONObject(json);
			Object titleJSON = jo.get("title");
			title = titleJSON.toString();
			this.vegetarian = jo.getBoolean("vegetarian");
			this.glutenFree = jo.getBoolean("glutenFree");
			this.dairyFree = jo.getBoolean("dairyFree");
			this.ketogenic = jo.getBoolean("ketogenic");
			this.vegan = jo.getBoolean("vegan");
    		if(jo.has("cookingMinutes")) this.cookMinutes = jo.getInt("cookingMinutes");
    		else this.cookMinutes = 0;
			if(jo.has("preparationMinutes")) this.prepMinutes = jo.getInt("preparationMinutes");
			else this.prepMinutes = 0;
			if(jo.has("instructions")) this.instructions = jo.getString("instructions");
			else this.instructions = "";
			
			JSONArray extIngredients = jo.getJSONArray("extendedIngredients");
			/*this.IngredientIDArray = new ArrayList<Integer>();
			for(int i = 0; i < extIngredients.length(); i++) 
			{
				IngredientIDArray.add(extIngredients.getInt(0));
			}
			*/
			
		} 
    	catch (JSONException e) 
    	{
			e.printStackTrace();
		}
    }

    @Override
    public int compareTo(Recipe other) 
    {
    	return 0;
    }
} 
