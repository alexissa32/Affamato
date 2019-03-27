package affamato;

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

    private Cook() {}
    public Cook(User user, String CookHolder) {
        this.user = user;
        this.CookHolder = Key.create(Cook.class, CookHolder);
      
    }
    public User getCook() {
        return user;
    }   
}