package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class Auto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5961197543989122844L;
	private String naam;
	private String merk;
	private String kenteken;
	private String brandstofType;
	
	public Auto(String nm, String me, String ken, String bt){
		naam = nm;
		merk = me;
		kenteken = ken;
		brandstofType = bt;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public String getMerk() {
		return merk;
	}
	
	public String getKenteken() {
		return kenteken;
	}
	
	public String getBrandstofType() {
		return brandstofType;
	}
	
	public boolean controleerKenteken(String kentek) {
		boolean b = false;
		if (kentek != null && kentek.trim().length() > 0) {
			String regex = "[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}";
			if (kentek.matches(regex)) {
				b = true;
			}
		}
		return b;
	}
	
	public boolean controleerAutoNaam(String autonm) {
		boolean b = false;
		if (autonm != null && autonm.trim().length() > 0) {
			String regex = "[a-zA-Z0-9]{3,12}";
			if (autonm.matches(regex)) {
				b = true;
			}
		}
		return b;
	}
	
	public boolean controleerAutoMerk(String automerk) {
		boolean b = false;
		if (automerk != null && automerk.trim().length() > 0) {
			String regex = "[a-zA-Z0-9]{3,12}";
			if (automerk.matches(regex)) {
				b = true;
			}
		}
		return b;
	}
	
	public boolean controleerBrandstoftype(String brandstoftyp) {
		boolean b = false;
		if (brandstoftyp != null && brandstoftyp.trim().length() > 0) {
			String regex = "[a-zA-Z0-9]{3,12}";
			if (brandstoftyp.matches(regex)) {
				b = true;
			}
		}
		return b;
	}

	
	public String toString(){
		String s = "Autonaam: " + naam + ", merk: " + merk + ", kenteken: " + kenteken + ", brandstoftype: " + brandstofType + ".\n";
		return s;
	}
}
