package affamato;

import java.io.IOException;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if (user != null) {
			//---------------------USER-BASED SEARCH aka filtering---------------------\\

			resp.setContentType("text/plain");
			resp.getWriter().println("Hello," + user.getNickname());
			
		} else {
			//---------------------GENERIC SEARCH---------------------\\
			
			resp.setContentType("text/plain");
			resp.getWriter().println("Hello non-user");
		}
		
	}
	
}
