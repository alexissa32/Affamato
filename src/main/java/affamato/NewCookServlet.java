package affamato;


import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.appengine.api.users.User;

import com.google.appengine.api.users.UserService;

import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

 
public class NewCookServlet extends HttpServlet {

	static 
	{
		 ObjectifyService.register(Cook.class);
	 }

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		ObjectifyService.register(Cook.class);
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String CookHolderName = user.toString();
        String CookFlag = req.getParameter("CookFlag");
        List<Cook> Cooks = ObjectifyService.ofy().load().type(Cook.class).list();
        Cook Cook = null;
        for(int i = 0; i < Cooks.size(); i++)
        {
      		if(Cooks.get(i).getCook().equals(user))
      		{
      			Cook = Cooks.get(i);
      		}
      	}
        //pass the string "Cook" to create a new cook
        //pass the string "unCook" to delete the cook that exists
        try {
        if(CookFlag.equals("Cook")) 
        {
        	if(Cook == null)
        	{
	        	Cook newCook = new Cook(user, CookHolderName);
	        	ofy().save().entity(newCook).now();
        	}
        	Cookie cookie = new Cookie("user", "value");
        	resp.addCookie(cookie);
        }
        else if(CookFlag.equals("unCook")) 
        {
        	ofy().delete().entity(Cook).now();
        }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        resp.sendRedirect("/landingPage.jsp");
    }
}
