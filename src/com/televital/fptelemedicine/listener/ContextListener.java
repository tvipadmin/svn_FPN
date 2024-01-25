package com.televital.fptelemedicine.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
	  public void contextInitialized(ServletContextEvent event){
	        ServletContext context = event.getServletContext();
	        //
	        // instanciate a map to store references to all the active
	        // sessions and bind it to context scope.
	        //
	        HashMap activeUsers = new HashMap();
	        context.setAttribute("activeUsers", activeUsers);
	    }

	    /**
	     * Needed for the ServletContextListener interface.
	     */
	    public void contextDestroyed(ServletContextEvent event){
	        // To overcome the problem with losing the session references
	        // during server restarts, put code here to serialize the
	        // activeUsers HashMap.  Then put code in the contextInitialized
	        // method that reads and reloads it if it exists...
	    }
}
