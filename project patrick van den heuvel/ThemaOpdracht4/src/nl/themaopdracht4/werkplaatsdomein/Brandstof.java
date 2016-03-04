/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */
package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class Brandstof implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5247147893447886140L;
	private int getal; // identificatie 
	private String brandstofType; // wat is het voor een brandstof 
	private double literPrijs; // de literprijs
	
	public Brandstof() {
		
	}

	public Brandstof(int getal, String tekst, double pr) {
		this.getal = getal;
		this.brandstofType = tekst;
		this.literPrijs = pr;
	}

	public int getGetal() {
		return getal;
	}

	public void setGetal(int getal) {
		this.getal = getal;
	}

	public String getBrandstofType() {
		return brandstofType;
	}

	public void setTekst(String tekst) {
		this.brandstofType = tekst;
	}

	public double getPR() {
		return literPrijs;
	}

	public void setPR(double pr) {
		this.literPrijs = pr;
	}
}
