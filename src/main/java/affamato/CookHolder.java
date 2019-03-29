package affamato;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class CookHolder 
{
    @Id long id;
    String name;

    public CookHolder(String name) 
    {
        this.name = name;
    }
}
