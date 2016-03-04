/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 4 jun. 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class AutoException extends Exception implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5958580939123691934L;

	public AutoException() {
		
		super();
		
	}
	
	public AutoException(String msg) {
		
		super(msg);
		
	}

}
