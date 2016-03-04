/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 19 jun. 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class TijdvakException extends Exception implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7656236017241834333L;

	public TijdvakException() {
		super();
	}
	
	public TijdvakException(String msgs) {
		super(msgs);
	}

}
