/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4516855928396373028L;
	private String username;
	private String password;
	private String naam;
	private String adres;
	private String woonplaats;
	private String geboortedatum;
	private String geslacht;
	private String email;

	public User(String username, String password, String achternaam,
			String adres, String woonplaats, String geb_datum, String geslacht,
			String email) {
		this.username = username;
		this.password = password;
		this.naam = achternaam;
		this.adres = adres;
		this.woonplaats = woonplaats;
		this.geboortedatum = geb_datum;
		this.geslacht = geslacht;
		this.email = email;

	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}

	public String getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(String geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public String getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(String geslacht) {
		this.geslacht = geslacht;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	// pqc methodes week 4 + themaopdracht

	public boolean controleerUsername(String gbn) {
		boolean b = false;
		String regex = "[a-zA-Z0-9\\._\\-]{5,14}";
		if (gbn != null && gbn.trim().length() > 0) {
			if (gbn.matches(regex)) {
				b = true;
			}
		}
		return b;
	}

	public boolean controleerWachtwoord(String wacht) {
		boolean b = false;
		String regex = "[a-zA-Z0-9\\._\\-]{5,14}";
		if (wacht != null && wacht.trim().length() > 0) {
			if (wacht.matches(regex)) {
				b = true;
			}
		}
		return b;
	}

	public boolean controleerEmailAddress(String email) {
		boolean isValidEmail = true;

		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();

		} catch (AddressException ex) {
			isValidEmail = false;
		}
		return isValidEmail;
	}

	public boolean controleerGeboorteDatum(String geb) {
		boolean isValidFormat = false;
		if (geb != null && geb.trim().length() > 0) {

			String regex = "[0-9]{2}-[0-9]{2}-[0-9]{4}";
			if (geb.matches(regex)) {
				String formatString = "dd-MM-yyyy";

				Date dateObj;
				try {
					SimpleDateFormat sdf = (SimpleDateFormat) DateFormat
							.getDateInstance();
					sdf.applyPattern(formatString);
					sdf.setLenient(false);
					dateObj = sdf.parse(geb);
					if (geb.equals(sdf.format(dateObj))) {
						isValidFormat = true;

					}
				} catch (ParseException ex) {

				}
			}
		}
		return isValidFormat;
	}

	public boolean controleerAdres(String adr) {
		boolean b = false;
		if (adr != null && adr.trim().length() > 0) {
			String regex = "[a-zA-Z0-9]{3,12}";
			if (adr.matches(regex)) {
				b = true;
			}
		}
		return b;
	}

	public boolean controleerNaam(String nm) {
		boolean b = false;
		if (nm != null && nm.trim().length() > 0) {
			String regex = "[a-zA-Z]{3,18}";
			if (nm.matches(regex)) {
				b = true;
			}
		}
		return b;
	}

	public boolean controleerGeslacht(String ges) {
		boolean b = false;
		if (ges != null && ges.trim().length() > 0) {
			if ("man".equals(ges)) {
				b = true;
			} else if ("vrouw".equals(ges)) {
				b = true;
			}
		} else {
			b = false;
		}
		return b;

	}

	public boolean controleerWoonplaats(String woon) {
		boolean b = false;
		String regex = "[a-zA-Z]{3,}";
		if (woon != null && woon.trim().length() > 3) {
			if (woon.matches(regex)) {
				b = true;
			}
		}
		return b;
	}

	public String toString() {
		String s = "username: " + username + " password: " + password;
		return s;
	}
}
