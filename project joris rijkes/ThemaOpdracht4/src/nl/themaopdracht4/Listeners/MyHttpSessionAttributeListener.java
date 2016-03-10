/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.Listeners;

import java.util.logging.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		Logger.getLogger("nl.themaopdracht4.servlets").info("Session:         " + event.getSession().getId().toString() + ", Attribute added:   " + event.getName() + ", waarde: " + event.getValue().toString());
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		Logger.getLogger("nl.themaopdracht4.servlets").info("Session:         " + event.getSession().getId().toString() + ", Attribute removed: " + event.getName() + ", waarde: " + event.getValue().toString());
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	

}
