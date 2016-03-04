/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */
package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;
import java.util.ArrayList;

public class Pomp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 967341191004494924L;

	private static Pomp pomp;

	private ArrayList<Brandstof> alleBrandstoffen = new ArrayList<Brandstof>();
	private ArrayList<Betaling> alleBetalingen = new ArrayList<Betaling>();

	private Pomp() {
	};

	public static Pomp getInstance() {
		if (pomp == null) {
			pomp = new Pomp();
		}
		return pomp;
	}
	
	public Brandstof zoekBrandStof(String benzineType) {
		Brandstof gevondeBrandstof = null;
		if (alleBrandstoffen != null) {
			for (Brandstof mogelijkeBrandstof : alleBrandstoffen) {
				if (benzineType.equals(mogelijkeBrandstof.getBrandstofType())) {
				gevondeBrandstof = mogelijkeBrandstof;
				break;
				}
			}
		}
		return gevondeBrandstof;
	}

	public ArrayList<Brandstof> getbrandstoffen() {
		return alleBrandstoffen;
	}

	public void setbrandstofen(ArrayList<Brandstof> allebrandstoffen) {
		this.alleBrandstoffen = allebrandstoffen;
	}

	public ArrayList<Betaling> getbetalingen() {
		return alleBetalingen;
	}

	public void addBetaling(Betaling t) {
		alleBetalingen.add(t);
	}
}
