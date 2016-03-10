/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */
package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class Tankbeurt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3908923678943683787L;
	private Brandstof brandstof;
	private int aantal;
	
	public Tankbeurt(Brandstof brandstof, int aantal) {
		this.brandstof = brandstof;
		this.aantal = aantal;
	}

	

	public Brandstof getbrandstof() {
		return brandstof;
	}

	public int getAantal() {
		return aantal;
	}

	
	
}
