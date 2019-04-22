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
    
    public User getCook() 
    {
        return user; //return this instead? and write a getUser()?
    }   
    //private void saveCook() {
    //	ObjectifyService.ofy().save().entity(this).now();
    //}
    
    //pass an ingredient JSONstring and the index of the grocery list
    public void addToGroceryList(String ID, int index) 
    {
    	if(this.GroceryLists.equals("")) {
    		newGroceryList();
    	}
    	JSONArray gLists = new JSONArray(this.GroceryLists);
    	if(gLists.length() > index) {
    	JSONArray gList = gLists.getJSONArray(index);
    	JSONObject ingredient = new JSONObject(ID);
    	gList.put(ingredient); //does this update gLists?
    	this.GroceryLists = gLists.toString();
    	}
    }
    
    //makes empty grocery list
    public void newGroceryList() {
    	JSONArray newList= new JSONArray();
    	if(this.GroceryLists.equals("")) {
    		JSONArray Lists = new JSONArray();
    		Lists.put(newList);
    		this.GroceryLists = Lists.toString();
    	}
    	else {
    		JSONArray gList = new JSONArray(this.GroceryLists);
    		gList.put(new JSONArray());
    		this.GroceryLists = gList.toString();
    	}
    	
    	
    }
    
    //UNTESTED METHOD correlated failures: removeFromPantry(), removeFromRecipeList()
    //removes the grocery list from the specified index
    public void removeGroceryList(int index) 
    {
    	if(this.GroceryLists.equals("")) {}
    	else {
    	JSONArray gLists = new JSONArray(this.GroceryLists);
    	if(gLists.length()>index) {
    	gLists.remove(index);	//UNCAUGHT EXCEPTION FOR INDEX OUT OF BOUNDS
    	this.GroceryLists = gLists.toString();
    	}}
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
    
    //removes the ingredient at position pos in the grocery list with index "index"
    public void removeFromGroceryList(int pos, int index) {
    	if(this.GroceryLists.equals("")) {}
    	else {
    	JSONArray gLists = new JSONArray(this.GroceryLists);
    	if(gLists.length() > index) {
    	JSONArray gList = gLists.getJSONArray(index);
    	if(gList.length() > pos) {
    	gList.remove(pos);//UNCAUGHT EXCEPTIONS FOR INDEX OUT OF BOUNDS
    	this.GroceryLists = gLists.toString();
    	}}}
    }
    
    //pass a json string
    public void addToPantry(String ID) 
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
    
    //UNTESTED METHOD correlated failures: removeFromRecipeList(), removeFromGroceryList()
    public void removeFromPantry(int pos) 
    {
    	if(this.Pantry.equals("")) {}
    	else {
    		JSONArray pantry = new JSONArray(this.Pantry);
    		if(pantry.length() > pos) {
    		pantry.remove(pos);	//UNCAUGHT EXCEPTION IF POS IS OUT OF BOUNDS
    		this.Pantry = pantry.toString();
    	}}
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
    
    //pass a recipe json string
    public void addToRecipeList(String RecipeString) 
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
    	JSONObject recipe = new JSONObject(RecipeString);
    	editor.put(recipe);
    	this.RecipeList = editor.toString();
    	//this.saveCook();
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
    //returns null if DNE yet
    public JSONArray getPantry() 
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
    public JSONArray getGroceryLists()
    {
    	if(this.GroceryLists.equals("")) return new JSONArray();
    	JSONArray groceryLists = new JSONArray(this.GroceryLists);
    	return groceryLists;
    }
    
    public JSONArray getGroceryList(int pos) {
    	
    	JSONArray groceryLists;
    	if(this.GroceryLists.equals("")) groceryLists = new JSONArray();
    	else groceryLists = new JSONArray(this.GroceryLists);
    	try {
    		return groceryLists.getJSONArray(pos);
    	} catch(Exception e) {
    		groceryLists.put(pos, new JSONArray());
    		return groceryLists.getJSONArray(pos);
    	}
    	
    }
    
    public JSONArray getPantrySearchResults() {
    	if(this.PantrySearchResults.equals("")) return new JSONArray();
    	JSONArray results = new JSONArray(this.PantrySearchResults);
    	return results;
    }

    public JSONArray getGrocerySearchResults() {
    	if(this.GrocerySearchResults.equals("")) return new JSONArray();
    	JSONArray results = new JSONArray(this.GrocerySearchResults);
    	return results;
    }

    public JSONArray getRecipeSearchResults() {
    	if(this.RecipeSearchResults.equals("")) return new JSONArray();
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
