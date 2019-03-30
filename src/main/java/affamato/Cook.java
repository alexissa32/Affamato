package affamato;

import java.util.ArrayList;
import java.util.List;

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
    @Index String GroceryList;
    @Index String RecipeList;
     

    private Cook() {}
    public Cook(User user, String CookHolder) 
    {
        this.user = user;
        this.CookHolder = Key.create(Cook.class, CookHolder);
        this.RecipeList = "";
        this.Pantry = "";
        this.GroceryList = "";
      
    }
    
    public User getCook() 
    {
        return user;
    }   
    
    public void addToGroceryList(String ID) {
    	this.GroceryList = this.GroceryList + ID + ",";
    }
    
    public void addToPantry(String ID) {
    	this.Pantry = this.Pantry + ID + ",";
    }
    
    public void addToRecipeList(String RecipeName) {
    	this.RecipeList = this.RecipeList + RecipeName + ";";
    }
    
    
    //UNTESTED METHOD
    public ArrayList<Ingredient> getPantry() {
    	ArrayList<Ingredient> pantryList = new ArrayList<Ingredient>();
    	
    	
    	//String s = "prefix/dir1/dir2/dir3/dir4";
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
    	  //System.out.println(t);
    	}
    	//need to search each ingredient and add to list in this loop:
    	//get ingredient by some string
    	//pantryList.add(Ingredient)
    	/*(output:
prefix
dir1
dir2
dir3
dir4 )*/
    		
    	return pantryList;
    	
    }
}
