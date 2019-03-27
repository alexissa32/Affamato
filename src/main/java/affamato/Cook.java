package affamato;

import java.util.ArrayList;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;


@Entity
public class Cook {
    @Parent Key<Cook> CookHolder;
    @Id Long id;
    @Index User user;
    //May have to use new fields that are organized
    //if ingredient and recipe end up just containing jsons
    @Index ArrayList<Ingredient> Inventory;
    @Index ArrayList<Ingredient> GroceryList;
    @Index ArrayList<Recipe> RecipeList;
     

    private Cook() {}
    public Cook(User user, String CookHolder) {
        this.user = user;
        this.CookHolder = Key.create(Cook.class, CookHolder);
        this.RecipeList = new ArrayList<Recipe>();
        this.Inventory = new ArrayList<Ingredient>();
        this.GroceryList = new ArrayList<Ingredient>();
      
    }
    public User getCook() {
        return user;
    }   
}