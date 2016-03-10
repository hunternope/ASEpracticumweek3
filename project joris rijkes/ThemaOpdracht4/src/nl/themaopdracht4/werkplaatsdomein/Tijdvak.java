/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;
import java.util.Calendar;

public class Tijdvak implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7059804103667176212L;
	private Calendar beginTijd;
	private Calendar eindTijd;
	private Calendar datum;
	private Klus deKlus;

	public Tijdvak(Calendar datum, Calendar beginTijd, Calendar eindTijd) {

		
		this.datum = datum;
		this.beginTijd = beginTijd;
		this.eindTijd = eindTijd;
		
	}

	@SuppressWarnings("deprecation")
	public void controleerTijdvakDatums(Calendar nweDat, Calendar nweBegin,
			Calendar nweEind) throws TijdvakException {
		
		Calendar huidigeTijd = Calendar.getInstance();
		huidigeTijd.setLenient(false);

		if (nweDat == null) {
			throw new TijdvakException("Geen datum ingevuld!");
		}
		if (nweBegin == null) {
			throw new TijdvakException("Geen begin datum ingevuld!");
		}

		if (nweEind == null) {
			throw new TijdvakException("Geen eind datum ingevuld!");
		}

		if (nweDat.getTime().getYear() <= huidigeTijd.getTime().getYear() && nweDat.getTime().getMonth() <= huidigeTijd.getTime().getMonth() && nweDat.getTime().getDate() < huidigeTijd.getTime().getDate()) {
			throw new TijdvakException(
					"De geselecteerde datum is voor de huidige datum!");
		}
		if (nweBegin.getTime().getHours() <= huidigeTijd.getTime().getHours() && nweDat.getTime().getYear() <= huidigeTijd.getTime().getYear() && nweDat.getTime().getMonth() <= huidigeTijd.getTime().getMonth() && nweDat.getTime().getDate() <= huidigeTijd.getTime().getDate()) {
			throw new TijdvakException("De geselecteerde tijd op deze datum is kleiner dan de huidige tijd op deze datum!");
		}
		
		if (nweBegin.getTime().getHours() >= nweEind.getTime().getHours()) {
			throw new TijdvakException("Begin tijdstip is na of gelijk aan het eind tijdstip!");
		}
	}
	
	public void setKlus(Klus nweK) {

		if (nweK != null) {
			deKlus = nweK;

		}
	}

	public Klus getKlus() {

		return deKlus;

	}

	public void verwijderKlus(Klus gezochteK) {
		deKlus = null;

	}

	public Calendar getBeginTijd() {

		return beginTijd;
	}

	public Calendar getEindTijd() {

		return eindTijd;
	}

	public Calendar getDatum() {

		return datum;
	}

	@SuppressWarnings({ "deprecation" })
	public String getDatumString() {
		String s = (datum.getTime().getDate()) + "-" + (datum.get(2) + 1) + "-"
				+ datum.get(1);

		return s;
	}
	
	@SuppressWarnings("deprecation")
	public String getBeginTijdString() {
		String s = beginTijd.getTime().getHours() + ":"
				+ beginTijd.getTime().getMinutes() + ":"
				+ beginTijd.getTime().getSeconds();
		return s;
	}
	@SuppressWarnings("deprecation")
	public String getEindTijdString() {
		String s = eindTijd.getTime().getHours() + ":"
				+ eindTijd.getTime().getMinutes() + ":"
				+ eindTijd.getTime().getSeconds();
		return s;
	}
		
	public String toString() {
		String s = "Begintijd: " + beginTijd + ", Eindtijd: " + eindTijd + "\n";

		return s;
	}
}
