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
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
        UserService userService = UserServiceFactory.getUserService();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        User user = userService.getCurrentUser();
    	String query = req.getParameter("search"); //this is working.
    	//URL url = new URL("https://www.affamato.xyz/search?q="+query);
    	String vegetarian = req.getParameter("veggie"); //these will either be on or null
    	String vegan = req.getParameter("vegan");
    	String glutenFree = req.getParameter("glutenf");
    	String ketogenic = req.getParameter("keto");
    	String dairyFree = req.getParameter("dairyf");
    	String quick = req.getParameter("quickr");
    	String useInventory = req.getParameter("useinv");
    	String useExpiring = req.getParameter("useexp");
    	//String user = req.getParameter("user");
    	
        // Get the input stream through URL Connection
    	
        //URLConnection con = url.openConnection();
        //InputStream is =con.getInputStream();
        //BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //result = br.readLine();
    	try {
    	URIBuilder b = new URIBuilder("http://www.affamato.xyz/search"); 
    	b.addParameter("user", user.toString());
    	b.addParameter("q", query);
    	b.addParameter("vegetarian", vegetarian == null ? "false" : "true");
    	b.addParameter("vegan", vegan == null ? "false" : "true");
    	b.addParameter("glutenFree", glutenFree == null ? "false" : "true");
    	b.addParameter("ketogenic", ketogenic == null ? "false" : "true");
    	b.addParameter("dairyFree", dairyFree == null ? "false" : "true");
    	b.addParameter("quick", quick == null ? "false" : "true");
    	b.addParameter("useInventory", useInventory == null ? "false" : "true");
    	b.addParameter("useExpiring", useExpiring == null ? "false" : "true");
    	
    	resp.sendRedirect(b.toString());
    	
    	} catch (Exception e) {
    		
    		e.printStackTrace();
    		resp.sendRedirect("/recipesPage.jsp");
    	}
	
        
    }

}