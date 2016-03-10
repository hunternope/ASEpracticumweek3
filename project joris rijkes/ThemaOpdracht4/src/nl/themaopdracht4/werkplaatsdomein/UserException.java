/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 22 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class UserException extends Exception implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5526466393693759899L;

	public UserException() {
		super();
	}
	
	public UserException(String arg0) {
		super(arg0);
	}

}
