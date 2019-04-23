package affamato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;


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
	
	
	@Override public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		UserService userService = UserServiceFactory.getUserService();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        User user = userService.getCurrentUser();
        String query = req.getParameter("search");
        JSONArray s = Ingredient.searchIngredient(query);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
        	JSONObject o = s.getJSONObject(i);
        	String name = o.getString("name");
        	if(name != null) sb.append(name);
        	sb.append("\n\n");
        }
        resp.setContentType("text/plain");
        try {
        resp.getWriter().println(sb);
        } catch (Exception e) {
        	return;
        }
	}
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
