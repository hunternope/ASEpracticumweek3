/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */
package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;
import java.util.Calendar;

public class Betaling implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3258922307641001045L;
	private int betalingNr;
	private String betalingType;
	private Calendar datum;
	private Tankbeurt[] tankbeurt;

	public Betaling(int betalingNr, String betalingType) {
		this.betalingNr = betalingNr;
		this.betalingType = betalingType;
		this.datum = Calendar.getInstance();
		this.tankbeurt = new Tankbeurt[100];
	}

	public void addBrandstofToBetaling(Brandstof brandstofType, int aantal)
			throws BrandstofException {
		// eerst controleren hoeveel betalingregels er al zijn
		if (brandstofType == null) {
			throw new BrandstofException("Geen brandstof gekozen > null!");
		}
		if (aantal <= 0) {
			throw new BrandstofException("Aantal is niet ingevuld!");
		}
		int aantalBestaandeRegels = 0;
		for (int i = 1; i < tankbeurt.length; i++) {
			if (tankbeurt[i] != null) {
				aantalBestaandeRegels++;
			}
		}
		
			Tankbeurt tr = new Tankbeurt(brandstofType, aantal);
			tankbeurt[aantalBestaandeRegels + 1] = tr;
			aantalBestaandeRegels += 1;
		if (aantalBestaandeRegels == 0)  {
			throw new BrandstofException("Geen brandstof getanked!");
		}
	}

	public double getBetalingTotaal() throws BetalingException {
		double result = 0.0;
		for (int i = 0; i < tankbeurt.length; i++) {
			if (tankbeurt[i] != null) {
				Brandstof p = tankbeurt[i].getbrandstof();
				double prijs = p.getPR();
				result += prijs * tankbeurt[i].getAantal();
			}
		}
		if (result == 0.0) {
			throw new BetalingException(
					"Er zijn geen tankbeurt, betalingsregels!");
		} else {
			double btwBedrag = getBtwTotaal();
			result += btwBedrag;

			return result;
		}
	}

	public double getBtwTotaal() throws BetalingException {
		double result = 0.0;
		for (int i = 0; i < tankbeurt.length; i++) {
			if (tankbeurt[i] != null) {
				Brandstof p = tankbeurt[i].getbrandstof();
				double prijs = p.getPR();
				result += prijs * tankbeurt[i].getAantal() * 0.21;
			}
		}
		if (result == 0.0) {
			throw new BetalingException(
					"Er zijn geen tankbeurt, betalingsregels!");
		} else {
			return result;
		}
	}

	public void setDatum(Calendar datum) {
		this.datum = datum;
	}
	
	@SuppressWarnings("deprecation")
	public String toString() {
		String s = "Datum" + datum.getTime().getDate() + "-" + datum.getTime().getMonth() + "-" + datum.getTime().getYear() + "-" + "BetalingNr: " + betalingNr + ", BetalingType: " + betalingType;
		return s;
	}
}
