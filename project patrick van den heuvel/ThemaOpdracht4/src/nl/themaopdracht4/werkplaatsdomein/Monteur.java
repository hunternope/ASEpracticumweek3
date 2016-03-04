/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Monteur extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -957709679201072433L;
	private final String[] alleStringTijden = { "08:00:00", "09:00:00",
			"10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00",
			"15:00:00", "16:00:00", "17:00:00" };

	private ArrayList<Tijdvak> deTijdvakken;

	public Monteur(String un, String pw, String nm, String adr, String wop,
			String geb, String gesl, String em) {

		super(un, pw, nm, adr, wop, geb, gesl, em);
		deTijdvakken = new ArrayList<Tijdvak>();

	}

	public void voegTijdvakToe(Tijdvak nweT) throws AfspraakMakenException, TijdvakException {

		if (!heeftTijdvak(nweT)) {
			deTijdvakken.add(nweT);
		}
	}
	
	public void verwijderTijdvak(Tijdvak nweT) throws TijdvakException {
		
		if (deTijdvakken != null && !deTijdvakken.isEmpty()) {
			
			if (deTijdvakken.contains(nweT)) {
				deTijdvakken.remove(nweT);
			} else { throw new TijdvakException("Tijdvak bestaat niet!"); }
		} else { throw new TijdvakException("Geen tijdvakken aanwezig!"); }
	}

	@SuppressWarnings("deprecation")
	public boolean heeftTijdvak(Tijdvak nweT) throws AfspraakMakenException {
		boolean isBezetTijdvak = false;
		ArrayList<Tijdvak> alleMogelijkeBezetteTijdvakTijden = new ArrayList<Tijdvak>();
		ArrayList<Tijdvak> alleInpuntTijden = new ArrayList<Tijdvak>();

		for (int i = nweT.getBeginTijd().getTime().getHours(); i < nweT
				.getEindTijd().getTime().getHours(); i++) {

			// check 17:00 is laaste uur > keuze tijd
			if (i > 17) {
				throw new AfspraakMakenException(
						"Deze afspraaktype kan niet op dit tijdstip worden ingepland, probeer een ander tijdstip.");
			}

			Calendar tijdBeginTijd = Calendar.getInstance();
			tijdBeginTijd.setLenient(false);
			Date d = new Date();
			d.setHours(i);
			tijdBeginTijd.setTime(d);
			Tijdvak bezetteTijdvak = new Tijdvak(nweT.getDatum(),
					tijdBeginTijd, nweT.getEindTijd());
			if (!alleInpuntTijden.contains(bezetteTijdvak)) {
				alleInpuntTijden.add(bezetteTijdvak);
			}
		}

		for (Tijdvak t : deTijdvakken) {

			if (t.getDatum().equals(nweT.getDatum())) {

				for (int i = t.getBeginTijd().getTime().getHours(); (i < t
						.getEindTijd().getTime().getHours()); i++) {
					Calendar tijdBeginTijd = t.getBeginTijd();
					tijdBeginTijd.getTime().setHours(i + 1);
					Tijdvak bezetteTijdvak = new Tijdvak(t.getDatum(),
							tijdBeginTijd, t.getEindTijd());
					if (!alleMogelijkeBezetteTijdvakTijden
							.contains(bezetteTijdvak)) {
						alleMogelijkeBezetteTijdvakTijden.add(bezetteTijdvak);
					}
				}
			}
		}
		// check of tijdvak lengte (Begin-eind) wel kan bij de monteur
		for (Tijdvak d : alleMogelijkeBezetteTijdvakTijden) {

			for (Tijdvak e : alleInpuntTijden) {

				if (e.getBeginTijd().getTime().getHours() == d.getBeginTijd()
						.getTime().getHours()) {

					throw new AfspraakMakenException(
							"Deze afspraaktype kan niet op dit tijdstip worden ingepland, probeer een ander tijdstip.");
				}
			}
		}
		return isBezetTijdvak;
	}

	@SuppressWarnings("deprecation")
	public ArrayList<Date> getAlleBeschikbareMonteursTijden(Calendar nweTijd) {
		ArrayList<Date> binneKomendeAlleDates = getAlleDates();
		ArrayList<Date> uitGaandeAlleDates = new ArrayList<Date>();
		ArrayList<Date> alleTijdvakkenDates = new ArrayList<Date>();
		if (deTijdvakken != null) {
			for (Tijdvak t : deTijdvakken) {
				if (t.getDatum().equals(nweTijd)) {

					int b = t.getBeginTijd().getTime().getHours();
					int d = t.getBeginTijd().getTime().getMinutes();
					int f = t.getBeginTijd().getTime().getSeconds();

					int g = t.getEindTijd().getTime().getHours();
					// int h = t.getEindTijd().getTime().getMinutes();
					// int j = t.getEindTijd().getTime().getSeconds();

					for (int i = b; (i < g); i++) {
						Date goedeDate = new Date(70, 00, 01);
						goedeDate.setHours(i);
						goedeDate.setMinutes(d);
						goedeDate.setSeconds(f);
						if (!alleTijdvakkenDates.contains(goedeDate)) {
							alleTijdvakkenDates.add(goedeDate);
						}
					}
				}
			}
		}
		if (deTijdvakken != null && deTijdvakken.size() > 0) {

			for (Date i : binneKomendeAlleDates) {

				if (!alleTijdvakkenDates.contains(i)) {

					uitGaandeAlleDates.add(i);
				}
			}
			return uitGaandeAlleDates;
		} else {
			return binneKomendeAlleDates;
		}

	}

	public ArrayList<Date> getAlleDates() {
		ArrayList<Date> alleDates = new ArrayList<Date>();

		DateFormat sdf = new SimpleDateFormat("kk:mm:ss");
		sdf.setLenient(false);

		try {

			for (String s : alleStringTijden) {

				Date d = sdf.parse(s);
				alleDates.add(d);

			}
		} catch (Exception e) {

		}

		return alleDates;
	}

	public ArrayList<Tijdvak> getAlleTijdvakken() {
		return deTijdvakken;
	}

	public String getNaam() {

		return super.getNaam();
	}

	public String getEmailAdres() {

		return super.getEmail();
	}

	public String getGebruikersNaam() {

		return super.getUsername();
	}

	public String getWachtwoord() {

		return super.getPassword();
	}

	public String toString() {

		String s = "Monteur: " + getUsername() + ", " + getNaam()
				+ " monteurobject!. \n";
		/*
		 * if (deTijdvakken != null) { for (Tijdvak t : deTijdvakken) { s +=
		 * t.toString(); } }
		 */

		return s;
	}
}
