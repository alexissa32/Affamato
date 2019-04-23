package affamato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.utils.URIBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class recipesPageServlet extends HttpServlet{
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
        UserService userService = UserServiceFactory.getUserService();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        User user = userService.getCurrentUser();
        Cook cook = Cook.getCook(user);
    	String addOrRemove = req.getParameter("ar");
    	if(addOrRemove != null) {
    		if(addOrRemove.equals("add")) {
    			String data = req.getParameter("data");
    			cook.addToRecipeList(data);
    		}
    		else if(addOrRemove.equals("remove")) {
    			int pos = Integer.parseInt(req.getParameter("pos"));
    			cook.removeFromRecipeList(pos);
    		}
    	}
    	
    	
    	
    	resp.sendRedirect("/recipesPage.jsp");
	
        
    }

}