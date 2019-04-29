package affamato;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FavoriteServlet extends HttpServlet{
	
//POST IS ADDING TO FAVORITES	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	     String ingredient = req.getParameter("ingredient");	     
	     String title = req.getParameter("discoverTitle");
	     int prepMinutes = req.getParameter("prepMinutes");
	     int cookMinutes = req.getParameter("cookMinutes");
	     String instructions = req.getParameter("instructions");	     			
    	
        resp.sendRedirect("/recipesPage.jsp");
    }
    
//GET IS DISCOVER    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	     String ingredient = req.getParameter("ingredient");	     
	     String title = req.getParameter("discoverTitle");
	     int prepMinutes = req.getParameter("prepMinutes");
	     int cookMinutes = req.getParameter("cookMinutes");
	     String instructions = req.getParameter("instructions");	    	
    	
        resp.sendRedirect("/recipesPage.jsp");
    }

}
