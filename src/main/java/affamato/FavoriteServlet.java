package affamato;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FavoriteServlet extends HttpServlet{
//POST IS ADDING TO FAVORITES	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
    	
        resp.sendRedirect("/recipesPage.jsp");
    }
//GET IS DISCOVER    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
    	
        resp.sendRedirect("/recipesPage.jsp");
    }

}
