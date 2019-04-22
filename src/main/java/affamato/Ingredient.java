//@Author Alex Issa
package affamato;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Ingredient implements Comparable<Ingredient> 
{
    @Id Long id; //The @Id field can be of type Long, long, or String. 
    //If you use Long and save an entity with a null id, a numeric value will be 
    //generated for you using the standard GAE allocator for this kind. If you use 
    //String or the primitive long type, values will never be autogenerated.
    @Index String jsonString;
    @Index Long spoonId;
    @Index String ingredient;
    @Index Float amount;
    @Index String unit;
    @Index String unitShort;
    @Index String nutrientString;
    
    private Ingredient() {}
    
    public Ingredient(String json) 
    {
    	try {
    	this.jsonString = json;
    	JSONObject data = new JSONObject(json);
    	this.spoonId = data.getLong("id");
    	this.ingredient = data.getString("name");
    	this.amount = data.getFloat("amount");
    	this.unit = data.getString("unit");
    	this.unitShort = data.getString("unitShort");
    	JSONArray nutrients = data.getJSONObject("nutrition").getJSONArray("nutrients");
    	String building = "";
    	this.nutrientString = nutrients.toString();
    	/*
    	for(int i = 0; i < nutrients.length(); i++) {
    		JSONObject nutrient = (JSONObject) nutrients.get(i);
    		building = building + nutrient.getString("title");
    		building = building + ",";
    		building = building + nutrient.getFloat("amount");
    		building = building + ",";
    		building = building + nutrient.getString("unit");
    		building = building + ";";
    	}
    	this.nutrientString = building;
    	*/
    	}
    	
    	catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    public String getName() {
    	return this.ingredient;
    }
    
    //UNTESTED METHOD
    @Override public boolean equals(Object o) {
    	if(o instanceof Ingredient) {
    		Ingredient i = (Ingredient) o;
    		if(i.spoonId == this.spoonId) 
    		{
    			return true;
    		}
    		else {
    			return false;
    		}
    	} else if(o instanceof String) {
    		String i = (String) o;
    		if(Long.parseLong(i) == this.spoonId) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		return false;
    	}
    }
    
    @Override
    public int hashCode()
    {
    	return jsonString.hashCode();
    }
    
    @Override
    public int compareTo(Ingredient other) 
    {
        return 0;
    }
    
    public static class Tuple{
    	@Index Float amount;
    	@Index String unit;
		public Tuple(String amount, String unit) {
			this.amount = Float.valueOf(amount);
			this.unit = unit;
		}    	
    }
    
    public static JSONArray searchIngredient(String search) {
    	JSONArray returner = new JSONArray();
    	if(search == null) return returner;
    	List<Ingredient> ingredients = ObjectifyService.ofy().load().type(Ingredient.class).list();
    	for(Ingredient ing : ingredients) {
    		if(ing != null) {
	    		if(ing.getName().toLowerCase().contains(search.toLowerCase())) {
	    			returner.put(new JSONObject().put("name", ing.ingredient).put("nutrients", ing.nutrientString)
	    					.put("unit", ing.unit).put("unitShort", ing.unitShort).put("amount", ing.amount)
	    					);
	    		}
    		}
    	}
    	return returner;
    }
} 