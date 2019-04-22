package affamato;

import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ingredientsPageServlet extends HttpServlet{

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
        	
        	resp.sendRedirect(b.toString());
        	if(query == null) resp.getWriter().println("query is null");
        	} catch (Exception e) {
        		
        		e.printStackTrace();
        		resp.sendRedirect("/landingPage.jsp"); //not sure what to redirect to
        	}
	}
}
