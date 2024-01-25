package com.televital.fptelemedicine.listener;

import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener{
	private static final Logger log4log = Logger.getLogger(SessionListener.class);
	public void init(ServletConfig config){
    }
	
	  /**
     * Adds sessions to the context scoped HashMap when they begin.
     */
    public void sessionCreated(HttpSessionEvent event){
        HttpSession    session = event.getSession();
        ServletContext context = session.getServletContext();
        HashMap activeUsers =  (HashMap)context.getAttribute("activeUsers");

        activeUsers.put(session.getId(), session);
        log4log.info("a user added in the map"+session);
        context.setAttribute("activeUsers", activeUsers);
        log4log.error("map size: "+activeUsers.size());
    }

    /**
     * Removes sessions from the context scoped HashMap when they expire
     * or are invalidated.
     */
    public void sessionDestroyed(HttpSessionEvent event){
        HttpSession    session = event.getSession();
        ServletContext context = session.getServletContext();
        HashMap activeUsers = (HashMap)context.getAttribute("activeUsers");
        activeUsers.remove(session.getId());
        log4log.error("a user deleted in the map");
        log4log.error("map size: "+activeUsers.size());
    }

}
