package affamato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray; 
import org.json.JSONObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class inventoryPageServlet extends HttpServlet{
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        User user = userService.getCurrentUser();
    	String search = req.getParameter("search"); //this is working.
    	//URL url = new URL("https://www.affamato.xyz/search?q="+query);
        // Get the input stream through URL Connection
    	
        //URLConnection con = url.openConnection();
        //InputStream is =con.getInputStream();
        //BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //result = br.readLine();
	
        Cook thisCook = Cook.getCook(user);
        JSONArray thisPantry = thisCook.getPantry();
        
        resp.sendRedirect("/inventoryPage.jsp");
    }

}