package guestbook;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class OfySignGuestbookServlet extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static 
	{
       ObjectifyService.register(Greeting.class);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
    {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        // We have one entity group per Guestbook with all Greetings residing
        // in the same entity group as the Guestbook to which they belong.
        // This lets us run a transactional ancestor query to retrieve all
        // Greetings for a given Guestbook.  However, the write rate to each
        // Guestbook should be limited to ~1/second.
        
        String content = req.getParameter("content");
        String guestbookName = req.getParameter("guestbookName");
        
        Greeting g = new Greeting(user, content, guestbookName);
        
        //Thing th = ofy().load().key(g).get();
        ofy().save().entity(g).now();
        ofy().load().entity(g);

        resp.sendRedirect("/ofyguestbook.jsp?guestbookName=" + guestbookName);
    }
}