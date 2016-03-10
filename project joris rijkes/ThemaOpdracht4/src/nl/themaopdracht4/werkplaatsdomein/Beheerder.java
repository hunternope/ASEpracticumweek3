/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 5 jun. 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class Beheerder extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -10086795004341469L;

	public Beheerder(String username, String password, String achternaam,
			String address, String woonplaats, String geboortedatum,
			String geslacht, String email) {

		super(username, password, achternaam, address, woonplaats,
				geboortedatum, geslacht, email);
	}

	public String getNaam() {

		return super.getNaam();
	}

	public String getAdres() {

		return super.getAdres();
	}

	public String getGeb_datum() {

		return super.getGeboortedatum();
	}

	public String getGebruikersNaam() {

		return super.getUsername();
	}

	public String getWachtwoord() {

		return super.getPassword();
	}

	public String getEmail() {

		return super.getEmail();
	}
	
	public String toString() {
		String s = "gebruikersnaam :" + getGebruikersNaam() + ", " + getNaam()
				+ " Beheerder object uitgeprint!\n";
		return s;
	}
}

