package affamato;

import java.io.BufferedReader;
import java.io.IOException;
<<<<<<< HEAD
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.utils.URIBuilder;
=======
import java.util.logging.Logger;
>>>>>>> 9bd02192b10f34318241c720782f716cd962817e

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ingredientsPageServlet extends HttpServlet{

	private static final Logger log = Logger.getLogger(Cook.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserService userService = UserServiceFactory.getUserService();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        User user = userService.getCurrentUser();
        String query = req.getParameter("search");

        try {
        	URIBuilder b = new URIBuilder("http://www.affamato.xyz/search"); 
        	b.addParameter("type", "ingredient");
        	b.addParameter("user", user.toString());
        	b.addParameter("q", query);
        	String redirectPage = req.getParameter("redirect");
        	if(redirectPage == null) {
        		log.info("redirect is null");
        		redirectPage = "/grocerylistPage.jsp";
        	}
        	b.addParameter("redirect", redirectPage);
        	if(query == null) log.info("query is null");
        	resp.sendRedirect(b.toString());
        	resp.sendRedirect("/landingPage.jsp");
        
        	
        	} catch (Exception e) {
        		
        		e.printStackTrace();
        		resp.sendRedirect("/landingPage.jsp"); //not sure what to redirect to
        	}
	}
}
