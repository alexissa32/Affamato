package affamato;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Cook 
{
    @Parent Key<Cook> CookHolder;
    @Id Long id;
    @Index User user;
    //May have to use new fields that are organized
    //if ingredient and recipe end up just containing jsons
    @Index String Pantry;
    @Index String GroceryLists;
    @Index String RecipeList;
    //@Index String PantrySearchResults;
    @Index String DiscoverResults;
    @Index String RecipeSearchResults;
    
    private Cook() {}
    private Cook(User user, String CookHolder) 
    {
        this.user = user;
        //this.CookHolder = Key.create(Cook.class, CookHolder);
        JSONArray rList = new JSONArray();
        this.RecipeList = rList.toString();
        
        JSONArray pList = new JSONArray();
        this.Pantry = pList.toString();
        
        JSONArray glist = new JSONArray();
        for(int i = 1; i < 6; i++) {
        	JSONArray putter = new JSONArray();
        	String s = "Grocery List " + Integer.toString(i);
        	putter.put(s);
        	glist.put(putter);
        }
        this.GroceryLists = glist.toString();
        
        this.DiscoverResults = new JSONArray().toString();
        this.RecipeSearchResults = new JSONArray().toString();
    }
    
    public User getCook() 
    {
        return user; //return this instead? and write a getUser()?
    }   
   
    
    public String testt() {
        return "oh god please work";
    }
    
    public void updateCook() {
    	this.saveCook();
    }
    private void saveCook() {
    	ObjectifyService.ofy().save().entity(this).now();
    }
    
    //pass an ingredient JSONstring and the index of the grocery list
    public synchronized void addToGroceryList(String Ingredient, Integer index) 
    {
    	if(this.GroceryLists.equals("")) {
    		newGroceryList("Grocery List 1");
    	}
    	JSONArray gLists = new JSONArray(this.GroceryLists);
    	while(gLists.length() < index + 1) {
    		Integer i = gLists.length() + 1;
    		newGroceryList("Grocery List " + i.toString());
    		gLists = new JSONArray(this.GroceryLists);
    	}
    	JSONArray gList = gLists.getJSONArray(index);
    	gList.put(Ingredient); //does this update gLists?
    	this.GroceryLists = gLists.toString();
    }
    

    
    //makes empty grocery list
    public void newGroceryList(String name) {
    	JSONArray newList= new JSONArray();
    	if(this.GroceryLists.equals("")) {
    		JSONArray Lists = new JSONArray();
    		Lists.put(newList);
    		this.GroceryLists = Lists.toString();
    	}
    	else {
    		JSONArray gList = new JSONArray(this.GroceryLists);
    		gList.put(newList);
    		this.GroceryLists = gList.toString();
    	}
    	
    	
    }
    
    public synchronized void removeFromGroceryList(String Ingredient, Integer index) {
    	JSONArray gLists = new JSONArray(this.GroceryLists);	//this gets initial json array
    	JSONArray newList = gLists.getJSONArray(index);
    	JSONArray replace = new JSONArray();
    	replace = removeStringFromArray(Ingredient, newList);
    	gLists.put(index, replace);
    	this.GroceryLists = gLists.toString();
    }
    
   
    
    //pass a json string
    public synchronized void addToPantry(String ID) 
    {
    	JSONArray editor;
    	if(this.Pantry.equals("")) {
    		JSONArray edit = new JSONArray();
    		editor = edit;
    	}
    	else {
    	JSONArray edit = new JSONArray(this.Pantry);
    	editor = edit;
    	}
    	JSONObject ingredient = new JSONObject(ID);
    	editor.put(ingredient);
    	this.Pantry = editor.toString();	
    }
    
    public synchronized void addToPantry(JSONObject ingredient) {
    	JSONArray editor  = new JSONArray(this.Pantry);
    	editor.put(ingredient);
    	this.Pantry = editor.toString();
    }
    
    public synchronized void removeFromPantry(String x) {
    	JSONArray pantry = new JSONArray(this.Pantry);
    	pantry = removeJSONFromArray(x, pantry);
		this.Pantry = pantry.toString();
    }

	private JSONArray removeJSONFromArray(String x, JSONArray pantry) {
		for(int i = 0; i < pantry.length(); i++) {
			String ing = pantry.getJSONObject(i).toString();
			if(ing.equals(x)) {
				pantry.remove(i);
				break;
			}
		}
		return pantry;
	}
	private JSONArray removeStringFromArray(String x, JSONArray pantry) {
	for(int i = 0; i < pantry.length(); i++) {
		String ing = pantry.getString(i);
		if(ing.equals(x)) {
			pantry.remove(i);
			break;
		}
	}
	return pantry;
}

	//Index version
	//JSONArray pantry = new JSONArray(this.Pantry);
	//if(pantry.length() > pos) {
	//pantry.remove(pos);	//UNCAUGHT EXCEPTION IF POS IS OUT OF BOUNDS
	//this.Pantry = pantry.toString();
    
    //UNTESTED METHOD correlated failures: removeFromRecipeList(), removeFromGroceryList()
    public synchronized void removeFromPantry(JSONObject j) 
    {
    	JSONArray PantryJ = new JSONArray(this.Pantry);
    	for(int index = 0; index < PantryJ.length(); index++)
    	{
    		JSONObject o = PantryJ.getJSONObject(index);
    		if(j.toString().equals(o.toString()))
    		{
    			PantryJ.remove(index);
    			break;
    		}
    	}
    	this.Pantry = PantryJ.toString();
    }
    
    //pass a recipe json string
    //public void addToRecipeList(String RecipeString) 
    //{
    //	JSONArray editor;
    //	if(this.RecipeList.equals("")) {
    //		JSONArray edit = new JSONArray();
    //		editor = edit;
    //	}
    //	else {
    //	JSONArray edit = new JSONArray(this.RecipeList);
    //	editor = edit;
    //	}
    //	editor.put(RecipeString); //Looks like an object that is the whole recipe
    //	this.RecipeList = editor.toString();
    //}
    
    public void addToRecipeList(JSONObject jsonObject) 
    {
    	JSONArray editor;
    	if(this.RecipeList.equals("")) {
    		JSONArray edit = new JSONArray();
    		editor = edit;
    	}
    	else {
    	JSONArray edit = new JSONArray(this.RecipeList);
    	editor = edit;
    	}
    	editor.put(jsonObject); //Looks like an object that is the whole recipe
    	this.RecipeList = editor.toString();
    }

    
    //UNTESTED METHOD correlated failures: removeFromPantry(), removeFromGroceryList()
    public void removeFromRecipeList(int pos) 
    {
    	if(this.RecipeList.equals("")) {}
    	else {
    	JSONArray recipes = new JSONArray(this.RecipeList);
    	if(recipes.length()>pos) {
    	recipes.remove(pos);
    	this.RecipeList = recipes.toString();
    	}}
    }
    
    public boolean hasRecipe(String recipeTitle) {
    	JSONArray ja = new JSONArray(this.RecipeList);
    	for(int i = 0 ; i < ja.length() ; i++) {
    		//JSONObject potMatch = ja.getJSONObject(i);
    		if(ja.toString().contains(recipeTitle)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //UNTESTED METHOD correlated failures: getRecipeList(), getGroceryList()
    //returns null if DNE yet
    public synchronized JSONArray getPantry() 
    {
    	if(this.Pantry.equals("")) return new JSONArray();
    	JSONArray pantry = new JSONArray(this.Pantry);
    	return pantry;
    }
       
    //UNTESTED METHOD correlated failures: getPantry(), getGroceryList()
    public JSONArray getRecipeList()
    {
    	if(this.RecipeList.equals("")) return new JSONArray();
    	JSONArray recipeList = new JSONArray(this.RecipeList);
    	return recipeList;
    }
    
    //UNTESTED METHOD correlated failures: getPantry(), getRecipeList()
    public synchronized JSONArray getGroceryLists()
    {
    	if(this.GroceryLists.equals("")) return new JSONArray();
    	JSONArray groceryLists = new JSONArray(this.GroceryLists);
    	return groceryLists;
    }
    
    public synchronized JSONArray getGroceryList(String ID) {
    	int pos = Integer.parseInt(ID) - 1;
    	JSONArray groceryLists;
    	if(this.GroceryLists.equals("")) {groceryLists = new JSONArray();
    	return new JSONArray();}
    	else {groceryLists = new JSONArray(this.GroceryLists);}
    	try {
    		return groceryLists.getJSONArray(pos);
    	} catch(Exception e) {
    		groceryLists.put(pos, new JSONArray());
    		return groceryLists.getJSONArray(pos);
    	}
    	
    }

    public JSONArray getDiscoverResults() {
    	if(this.DiscoverResults.equals("")) return new JSONArray();
    	JSONArray results = new JSONArray(this.DiscoverResults);
    	return results;
    }

    public JSONArray getRecipeSearchResults() {
    	if(this.RecipeSearchResults.equals("")) return new JSONArray();
    	JSONArray results = new JSONArray(this.RecipeSearchResults);
    	
    	return results;
    }

    public void clearAllSearchResults() {
    	JSONArray empty = new JSONArray();
    	this.RecipeSearchResults = empty.toString();
    	this.DiscoverResults = empty.toString();
    	//this.PantrySearchResults = empty.toString();
    }
    public void setDiscoverResults(JSONArray results) {
    	this.DiscoverResults = results.toString();
    	//this.saveCook();
    }

    public void setRecipeSearchResults(JSONArray results) {
    	this.RecipeSearchResults = results.toString();
    	//this.saveCook();
    }
    
    
    private static final Logger log = Logger.getLogger(Cook.class.getName());
    //returns a Cook given the user
    //returns null if Cook does not exist
    public static Cook getCook(User user) {
    	
    	List<Cook> Cooks = ObjectifyService.ofy().load().type(Cook.class).list();
        for(Cook cook : Cooks) {
        	if(cook.equals(user)) {
        		log.info("loaded cooks");
        		//cook = ObjectifyService.ofy().load().entity(cook).now();
        		return cook;
        	}
        }
        log.info("cooks was null");
        Cook cook = new Cook(user, user.toString());
        return cook;
        
        
    }
    
    //returns a Cook given the user
    //returns null if Cook does not exist
    public static Cook getCook(String user) {
    	
    	List<Cook> Cooks = ObjectifyService.ofy().load().type(Cook.class).list();
        for(Cook cook : Cooks) {
        	if(cook.equals(user)) {
        		log.info("loaded cooks");
        		//cook = ObjectifyService.ofy().load().entity(cook).now();
        		return cook;
        	}
        }
        log.info("cooks is null");
        return null;
        
        
    }
    
    //Cooks are equal if their users are equal
    //accepts a user too
    @Override
    public boolean equals(Object o) {
    	if(this == o) return true;
    	if(o instanceof User) {
    		User u = (User) o;
    		if(this.user.toString().equals(u.toString())) return true;
    	}
    	else if(o instanceof Cook) {
    		Cook c = (Cook) o;
    		if(this.user.toString().equals(c.user.toString())) return true;
    	}
    	else if(o instanceof String) {
    		String s = (String) o;
    		if(this.user.toString().equals(s)) return true;
    	}
    	return false;
    }
}