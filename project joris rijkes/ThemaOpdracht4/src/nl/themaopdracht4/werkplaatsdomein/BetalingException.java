/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class BetalingException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1612614868734084785L;

	public BetalingException() {
		super();
	}
	
	public BetalingException(String msg) {
		super(msg);
	}
}
