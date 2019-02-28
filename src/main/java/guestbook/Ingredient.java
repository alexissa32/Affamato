package guestbook;

import java.util.Date;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Ingredient implements Comparable<Greeting> 
{
	@Parent Key<Guestbook> guestbookName;
	@Id Long id;
    @Index String name;
    //???
    
    private Greeting() {}
    public Greeting(User user, String content, String guestbookName) {
        this.user = user;
        this.content = content;
        this.guestbookName = Key.create(Guestbook.class, guestbookName);
        date = new Date();
    }
    public User getUser() {
        return user;
    }
    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(Greeting other) {
        if (date.after(other.date)) {
            return 1;
        } else if (date.before(other.date)) {
            return -1;
        }
        return 0;
     }
}
