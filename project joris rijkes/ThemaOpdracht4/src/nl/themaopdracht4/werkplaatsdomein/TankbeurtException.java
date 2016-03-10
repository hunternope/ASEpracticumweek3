/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 23 jun. 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class TankbeurtException extends Exception implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4858724716094376149L;
	public TankbeurtException() {
		super();
	}
	public TankbeurtException(String msgs) {
		super(msgs);
	}

}
