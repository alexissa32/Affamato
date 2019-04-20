package affamato;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;

public class ExecuteRegistrationOnceServlet implements ServletContextListener
{
	//static 
	//{
    //   ObjectifyService.register(Recipe.class);
    //   ObjectifyService.register(Ingredient.class);
    //   ObjectifyService.register(Cook.class);
    //}

	@Override
	public void contextInitialized(ServletContextEvent sce) 
	{
		//ObjectifyService.init();
		//ObjectifyService.factory();
	    ObjectifyService.register(Recipe.class);
	    ObjectifyService.register(Ingredient.class);
	    ObjectifyService.register(Cook.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub	
	}
}
