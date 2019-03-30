package affamato;

import java.util.ArrayList;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
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
    
    
    //ignore this method for now - just theoretical
    public ArrayList<Ingredient> getPantry() {
    	ArrayList<Ingredient> pantryList = new ArrayList<Ingredient>();
    	
    	
    	String s = "prefix/dir1/dir2/dir3/dir4";
    	String[] tokens = s.split("/");
    	
    	for (String t : tokens)
    	  System.out.println(t);
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
