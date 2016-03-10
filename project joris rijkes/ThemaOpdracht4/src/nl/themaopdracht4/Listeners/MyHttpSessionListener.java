/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.Listeners;

import java.util.logging.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		Logger.getLogger("nl.themaopdracht4.servlets").info("Session created: " + se.getSession().getId().toString());
		
		
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		Logger.getLogger("nl.themaopdracht4.servlets").info("Session destroyed: " + se.getSession().getId().toString());
		
	}
	

}
