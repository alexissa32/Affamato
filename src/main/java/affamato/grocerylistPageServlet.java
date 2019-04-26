package affamato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class grocerylistPageServlet extends HttpServlet{
	
	//get is for updating an existing grocery list
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		 UserService userService = UserServiceFactory.getUserService();
	     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	     User user = userService.getCurrentUser();
	     String data = req.getParameter("data");
	     Integer groceryListNum = Integer.parseInt(req.getParameter("listID")) - 1;
	     //int pos = Integer.parseInt(posString);
	     String addOrRemove = req.getParameter("ar");
	     Cook cook = Cook.getCook(user);
	     if(addOrRemove != null) {
		     if(addOrRemove.equals("add")) {
		    	 //String data = req.getParameter("data");
			     cook.addToGroceryList(data, groceryListNum);
		     }
		     else if(addOrRemove.equals("remove")) {
		    	 //int idx = Integer.parseInt(req.getParameter("index"));
		    	 cook.removeFromGroceryList(data, groceryListNum);
		     }
	     }
	     cook.updateCook();
	     
	     
	     resp.sendRedirect("/grocerylistPage.jsp");
	}
	
	//post is for creating a new grocery list
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        User user = userService.getCurrentUser();
    	String listName = req.getParameter("name"); //this is working.
    	Cook cook = Cook.getCook(user);
    	cook.newGroceryList(listName);
    	cook.updateCook();
    	//URL url = new URL("https://www.affamato.xyz/search?q="+query);
        // Get the input stream through URL Connection
    	
        //URLConnection con = url.openConnection();
        //InputStream is =con.getInputStream();
        //BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //result = br.readLine();
    	
    	//perhaps this needs to be in the JSP


        resp.sendRedirect("/grocerylistPage.jsp");
    }

}