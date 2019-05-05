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

	//static 
	//{
	//	 ObjectifyService.register(Cook.class);
	 //}

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		//ObjectifyService.register(Cook.class);
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String CookHolderName = user.toString();
        String CookFlag = req.getParameter("CookFlag");
        Cook cook = Cook.getCook(user);
        try {
        if(CookFlag.equals("Cook")) 
        {
        	
        	resp.addCookie(new Cookie("iAlreadyExist", "existenceIsPain"));
        	
        	Cookie cookieName = new Cookie("user", user.toString());
        	Cookie[] cookies = req.getCookies();
        	if(cookies != null) {
        		for(int i = 0; i < cookies.length; i++) {
        			if(cookies[i].getName().equals("user")) {
        				cookies[i].setMaxAge(0);
        			}
        			else if(cookies[i].getName().equals("createdNew")) {
        				cookies[i].setMaxAge(0);
        			}
        		}
        	}
        	resp.addCookie(cookieName);
        	
        	//is this the best place to do this
        	//instantiate the object first
        	
        }
        else if(CookFlag.equals("unCook")) 
        {
        	ofy().delete().entity(cook).now();
        }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        

        resp.sendRedirect("/landingPage.jsp");
    }
}
