/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.Listeners;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class MyHttpSessionActivationListener implements HttpSessionActivationListener {

	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		//Logger logger = Logger.getLogger("nl.LoginAssignment");
		
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		//Logger logger = Logger.getLogger("nl.LoginAssignment");
		
	}

}
