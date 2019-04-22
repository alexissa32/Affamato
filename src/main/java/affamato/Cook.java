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
    @Index String PantrySearchResults;
    @Index String GrocerySearchResults;
    @Index String RecipeSearchResults;
    
    private Cook() {}
    public Cook(User user, String CookHolder) 
    {
        this.user = user;
        this.CookHolder = Key.create(Cook.class, CookHolder);
        this.RecipeList = "";
        this.Pantry = "";
        this.GroceryLists = ""; 
        this.GrocerySearchResults = "";
        this.PantrySearchResults = "";
        this.RecipeSearchResults = "";
    }
    /*
    public Cook(Cook old, JSONArray newResults, String resultsType) {
    	this.user = old.user;
    	this.CookHolder = old.CookHolder;
    	this.RecipeList = old.RecipeList;
    	this.Pantry = old.Pantry;
    	this.GroceryLists = old.GroceryLists;
    	this.RecipeList = old.RecipeList;
    	this.PantrySearchResults = old.PantrySearchResults;
    	this.GrocerySearchResults = old.GrocerySearchResults;
    	this.RecipeSearchResults = old.RecipeSearchResults;
    	if(resultsType.equals("pantry")) {
    		this.PantrySearchResults = newResults;
    	}
    	else if(resultsType.equals("grocery")) {
    		this.GrocerySearchResults = newResults;
    	}
    	else if(resultsType.equals("recipe")) {
    		this.RecipeSearchResults = newResults;
    	}
    }
    */
    
    public User getCook() 
    {
        return user; //return this instead? and write a getUser()?
    }   
    //private void saveCook() {
    //	ObjectifyService.ofy().save().entity(this).now();
    //}
    
    public void addToGroceryList(String ID, int index) 
    {
    	//this.GroceryLists.getJSONArray(index).put(new JSONObject(ID));
    	//this.saveCook();
    }
    
    public void newGroceryList() {
    	//this.GroceryLists.put(new JSONArray());
    }
    
    //UNTESTED METHOD correlated failures: removeFromPantry(), removeFromRecipeList()
    public void removeGroceryList(int pos) 
    {
    	//this.GroceryLists.remove(pos);
    	//this.saveCook();
    	/**
    	JSONArray updated = new JSONArray();
    	try
    	{
    		for(int x = 0; x < this.GroceryList.length(); x++)
    		{
    			if(x != pos)
    			{
    				updated.put(this.GroceryList.get(x));
    			}
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	this.GroceryList = updated; */
    }
    /*
    public void removeFromGroceryList(int pos, int index) {
    	this.GroceryLists.getJSONArray(index).remove(pos);
    }
    
    public void addToPantry(String ID) 
    {
    	this.Pantry.put(new JSONObject(ID));
    	//this.saveCook();
    }
    */
    //UNTESTED METHOD correlated failures: removeFromRecipeList(), removeFromGroceryList()
    public void removeFromPantry(int pos) 
    {
    	//this.Pantry.remove(pos);
    	//this.saveCook();
    	/**
    	JSONArray updated = new JSONArray();
    	try
    	{
    		for(int x = 0; x < this.Pantry.length(); x++)
    		{
    			if(x != pos)
    			{
    				updated.put(this.Pantry.get(x));
    			}
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	this.Pantry = updated; */
    }
    
    public void addToRecipeList(String RecipeName) 
    {
    	//this.RecipeList.put(new JSONObject(RecipeName));
    	//this.saveCook();
    }
    
    //UNTESTED METHOD correlated failures: removeFromPantry(), removeFromGroceryList()
    public void removeFromRecipeList(int pos) 
    {
    	//this.RecipeList.remove(pos);
    	//this.saveCook();
    	/**
    	JSONArray updated = new JSONArray();
    	try
    	{
    		for(int x = 0; x < this.RecipeList.length(); x++)
    		{
    			if(x != pos)
    			{
    				updated.put(this.RecipeList.get(x));
    			}
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	this.RecipeList = updated; */
    }
    
    //UNTESTED METHOD correlated failures: getRecipeList(), getGroceryList()
    public JSONArray getPantry() 
    {
    	JSONArray pantry = new JSONArray(this.Pantry);
    	return pantry;
    }
       
    //UNTESTED METHOD correlated failures: getPantry(), getGroceryList()
    public JSONArray getRecipeList()
    {
    	JSONArray recipeList = new JSONArray(this.RecipeList);
    	return recipeList;
    }
    
    //UNTESTED METHOD correlated failures: getPantry(), getRecipeList()
    public JSONArray getGroceryLists()
    {
    	JSONArray groceryLists = new JSONArray(this.GroceryLists);
    	return groceryLists;
    }
    
    public JSONArray getGroceryList(int pos) {
    	JSONArray groceryLists = new JSONArray(this.GroceryLists);
    	return groceryLists.getJSONArray(pos);
    }
    
    public JSONArray getPantrySearchResults() {
    	JSONArray results = new JSONArray(this.PantrySearchResults);
    	return results;
    }

    public JSONArray getGrocerySearchResults() {
    	JSONArray results = new JSONArray(this.GrocerySearchResults);
    	return results;
    }

    public JSONArray getRecipeSearchResults() {
    	JSONArray results = new JSONArray(this.RecipeSearchResults);
    	return results;
    }

    public void setPantrySearchResults(JSONArray results) {
    	this.PantrySearchResults = results.toString();
    	//this.saveCook();
    }

    public void setGrocerySearchResults(JSONArray results) {
    	this.GrocerySearchResults = results.toString();
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
        log.info("cooks is null");
        return null;
        
        
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
    //Old code for when we had strings
    /*
    public void addToGroceryList(String ID) {
    	this.GroceryList = this.GroceryList + ID + ",";
    	
    }
    
    //UNTESTED METHOD correlated failures: removeFromPantry(), removeFromRecipeList()
    public void removeFromGroceryList(String ID) {
    	String[] tokens = this.GroceryList.split(",");
    	String newGroceryList = "";
    	for(int i = 0; i < tokens.length; i++){
    		if(!ID.equals(tokens[i])) {
    			newGroceryList = newGroceryList + tokens[i];
    		}
    	}
    	this.GroceryList = newGroceryList;
    }
    
    public void addToPantry(String ID) {
    	this.Pantry = this.Pantry + ID + ",";
    }
    
    //UNTESTED METHOD correlated failures: removeFromRecipeList(), removeFromGroceryList()
    public void removeFromPantry(String ID) {
    	String[] tokens = this.Pantry.split(",");
    	String newPantry = "";
    	for(int i = 0; i < tokens.length; i++){
    		if(!ID.equals(tokens[i])) {
    			newPantry = newPantry + tokens[i];
    		}
    	}
    	this.Pantry = newPantry;
    }
    
    public void addToRecipeList(String RecipeName) {
    	this.RecipeList = this.RecipeList + RecipeName + ";";
    }
    
    //UNTESTED METHOD correlated failures: removeFromPantry(), removeFromGroceryList()
    public void removeFromRecipeList(String RecipeName) {
    	String[] tokens = this.RecipeList.split(";");
    	String newRecipeList = "";
    	for(int i = 0; i < tokens.length; i++){
    		if(!RecipeName.equals(tokens[i])) {
    			newRecipeList = newRecipeList + tokens[i];
    		}
    	}
    	this.RecipeList = newRecipeList;
    }
    
    
    //UNTESTED METHOD correlated failures: getRecipeList(), getGroceryList()
    public List<Ingredient> getPantry() {
    	ArrayList<Ingredient> pantryList = new ArrayList<Ingredient>();
    	String s = this.Pantry;
    	String[] tokens = s.split(",");
    	List<Ingredient> ingredients = ObjectifyService.ofy().load().type(Ingredient.class).list();
    	for (String token : tokens)
    	{
    		if(! token.equals("")){
    			int index = ingredients.indexOf(token);
    			if(index != -1) {
    				Ingredient ing = ingredients.get(index);
    				pantryList.add(ing);
    			}
    		}
    	}

    		
    	return pantryList;
    }
    
    
    //UNTESTED METHOD correlated failures: getPantry(), getGroceryList()
    public List<Recipe> getRecipeList(){
    	ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    	
    	String s = this.RecipeList;
    	String[] tokens = s.split(";");
    	
    	List<Recipe> recipes = ObjectifyService.ofy().load().type(Recipe.class).list();
    	for (String token : tokens)
    	{
    		if(! token.equals("")){
    			int index = recipes.indexOf(token);
    			if(index != -1) {
    				Recipe rec = recipes.get(index);
    				recipeList.add(rec);
    			}
    		}
    	}
    	
    	return recipeList;
    }
    
    //UNTESTED METHOD correlated failures: getPantry(), getRecipeList()
    public List<Ingredient> getGroceryList(){
    	ArrayList<Ingredient> groceryList = new ArrayList<Ingredient>();
    	
    	String s = this.GroceryList;
    	String[] tokens = s.split(",");
    	
    	List<Ingredient> ingredients = ObjectifyService.ofy().load().type(Ingredient.class).list();
    	for (String token : tokens)
    	{
    		if(! token.equals("")){
    			int index = ingredients.indexOf(token);
    			if(index != -1) {
    				Ingredient ing = ingredients.get(index);
    				groceryList.add(ing);
    			}
    		}
    	}
    	
    	return groceryList;
    }
    */
