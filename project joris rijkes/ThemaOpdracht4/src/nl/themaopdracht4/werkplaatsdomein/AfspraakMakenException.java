/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 28 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;
	
public class AfspraakMakenException extends Exception implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7316164209317965379L;

	public AfspraakMakenException() {
		
		super();
	}
	
	public AfspraakMakenException(String msg) {
		
		super(msg);
	}

}
