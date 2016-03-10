/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class BrandstofException extends Exception implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1300188631764236443L;

	public BrandstofException() {
		super();
	}
	
	public BrandstofException(String msg) {
		super(msg);
	}

}
