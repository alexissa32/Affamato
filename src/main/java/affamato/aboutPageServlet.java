package affamato;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.*;
/*import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;*/

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;*/

public class aboutPageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	//UserService userService = UserServiceFactory.getUserService();
        //DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
        
        resp.sendRedirect("/aboutPage.jsp");
    }

}
