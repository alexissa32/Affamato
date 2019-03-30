package affamato;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class landingPageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
        resp.sendRedirect("/landingPage.jsp");
    }

}
