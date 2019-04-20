package affamato;

import java.util.ArrayList;
import java.util.List;
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
	static 
	{
	   ObjectifyService.register(Cook.class);
	}
	
    @Parent Key<Cook> CookHolder;
    @Id Long id;
    @Index User user;
    //May have to use new fields that are organized
    //if ingredient and recipe end up just containing jsons
    @Index JSONArray Pantry;
    @Index JSONArray GroceryLists;
    @Index JSONArray RecipeList;
    @Index JSONArray PantrySearchResults;
    @Index JSONArray GrocerySearchResults;
    @Index JSONArray RecipeSearchResults;
    
    
    private Cook() {}
    public Cook(User user, String CookHolder) 
    {
        this.user = user;
        this.CookHolder = Key.create(Cook.class, CookHolder);
        this.RecipeList = new JSONArray();
        this.Pantry = new JSONArray();
        this.GroceryLists = new JSONArray(); 
        this.GrocerySearchResults = new JSONArray();
        this.PantrySearchResults = new JSONArray();
        this.RecipeSearchResults = new JSONArray();
    }
    
    public User getCook() 
    {
        return user; //return this instead? and write a getUser()?
    }   
    private void saveCook() {
    	ObjectifyService.ofy().save().entity(this).now();
    }
    
    public void addToGroceryList(String ID, int index) 
    {
    	this.GroceryLists.getJSONArray(index).put(new JSONObject(ID));
    	this.saveCook();
    }
    
    public void newGroceryList() {
    	this.GroceryLists.put(new JSONArray());
    }
    
    //UNTESTED METHOD correlated failures: removeFromPantry(), removeFromRecipeList()
    public void removeGroceryList(int pos) 
    {
    	this.GroceryLists.remove(pos);
    	this.saveCook();
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
    
    public void removeFromGroceryList(int pos, int index) {
    	this.GroceryLists.getJSONArray(index).remove(pos);
    }
    
    public void addToPantry(String ID) 
    {
    	this.Pantry.put(new JSONObject(ID));
    	this.saveCook();
    }
    
    //UNTESTED METHOD correlated failures: removeFromRecipeList(), removeFromGroceryList()
    public void removeFromPantry(int pos) 
    {
    	this.Pantry.remove(pos);
    	this.saveCook();
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
    	this.RecipeList.put(new JSONObject(RecipeName));
    	this.saveCook();
    }
    
    //UNTESTED METHOD correlated failures: removeFromPantry(), removeFromGroceryList()
    public void removeFromRecipeList(int pos) 
    {
    	this.RecipeList.remove(pos);
    	this.saveCook();
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
    	return this.Pantry;
    }
       
    //UNTESTED METHOD correlated failures: getPantry(), getGroceryList()
    public JSONArray getRecipeList()
    {
    	return this.RecipeList;
    }
    
    //UNTESTED METHOD correlated failures: getPantry(), getRecipeList()
    public JSONArray getGroceryLists()
    {
    	return this.GroceryLists;
    }
    
    public JSONArray getGroceryList(int pos) {
    	return this.GroceryLists.getJSONArray(pos);
    }
    
    public JSONArray getPantrySearchResults() {
    	return this.PantrySearchResults;
    }

    public JSONArray getGrocerySearchResults() {
    	return this.GrocerySearchResults;
    }

    public JSONArray getRecipeSearchResults() {
    	return this.RecipeSearchResults;
    }

    public void setPantrySearchResults(JSONArray results) {
    	this.PantrySearchResults = results;
    	this.saveCook();
    }

    public void setGrocerySearchResults(JSONArray results) {
    	this.GrocerySearchResults = results;
    	this.saveCook();
    }

    public void setRecipeSearchResults(JSONArray results) {
    	this.RecipeSearchResults = results;
    	//this.saveCook();
    }
    
    //returns a Cook given the user
    //returns null if Cook does not exist
    public static Cook getCook(User user) {
    	List<Cook> Cooks = ObjectifyService.ofy().load().type(Cook.class).list();
        for(Cook cook : Cooks) {
        	if(cook.equals(user)) {
        		cook = ObjectifyService.ofy().load().entity(cook).now();
        		return cook;
        	}
        }
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
