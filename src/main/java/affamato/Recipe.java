//@Author Alex Issa
package affamato;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Recipe implements Comparable<Recipe> 
{
    @Id Long id; //The @Id field can be of type Long, long, or String. 
    //If you use Long and save an entity with a null id, a numeric value will be 
    //generated for you using the standard GAE allocator for this kind. If you use 
    //String or the primitive long type, values will never be autogenerated.
    @Index String jsonString;
    
    //private Recipe() {}
    public Recipe(String json) 
    {
    	jsonString = json;
    }

    @Override
    public int compareTo(Recipe other) 
    {
    	return 0;
    }
} 