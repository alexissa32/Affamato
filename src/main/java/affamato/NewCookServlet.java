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
	        	Cook = newCook;
        	}
        	Cookie cookie = new Cookie("user", user.toString());
        	Cookie[] cookies = req.getCookies();
        	if(cookies != null) {
        		for(int i = 0; i < cookies.length; i++) {
        			if(cookies[i].getName().equals("user")) {
        				cookies[i].setMaxAge(0);
        			}
        		}
        	}
        	resp.addCookie(cookie);
        	//is this the best place to do this
        	//instantiate the object first
        	
        }
        else if(CookFlag.equals("unCook")) 
        {
        	ofy().delete().entity(Cook).now();
        }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        //ListHolder listholder = new listHolder(Cook);
        req.setAttribute("ListObject", Cook);
    	req.getSession().setAttribute("ListObject", Cook);
    	this.getServletConfig().getServletContext().setAttribute("ListObject", Cook);
        resp.sendRedirect("/landingPage.jsp");
    }
}
