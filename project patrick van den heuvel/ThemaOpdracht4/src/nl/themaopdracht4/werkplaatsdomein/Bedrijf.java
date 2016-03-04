/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Bedrijf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2502872959460258885L;
	private String naam;
	private String plaats;
	private String jaar;

	private Beheerder deBeheerder;
	private Pomp dePomp;

	private ArrayList<Klant> alleKlanten;
	private ArrayList<Monteur> alleMonteurs;
	private ArrayList<Onderdeel> alleOnderdelen;
	private ArrayList<Parkeerplaats> alleParkeerplaatsen;

	private int GegenereerdeKlusCode = 0;
	private int GegenereerdeKlantenPasNummer = 1000;
	private int GegenereerdeBetalingNummer = 1;

	public Bedrijf(String nm, String pl, String ja) {
		naam = nm;
		plaats = pl;
		jaar = ja;

		alleKlanten = new ArrayList<Klant>();
		alleMonteurs = new ArrayList<Monteur>();
		alleOnderdelen = new ArrayList<Onderdeel>();
		alleParkeerplaatsen = new ArrayList<Parkeerplaats>();

		dePomp = Pomp.getInstance();

		for (int i = 1; i < 21; i++) {
			Parkeerplaats p = new Parkeerplaats(i);
			alleParkeerplaatsen.add(p);
		}

	}

	public synchronized String geefNieuweKlusCode() {
		String terugTeGevenKlusCodeString = "k";

		GegenereerdeKlusCode += 1;
		terugTeGevenKlusCodeString += GegenereerdeKlusCode;

		return terugTeGevenKlusCodeString;
	}

	public synchronized String geefNieuwePasNummer() {
		String terugTeGevenKlantenPasNummer = "KPN";

		GegenereerdeKlantenPasNummer += 1;
		terugTeGevenKlantenPasNummer += GegenereerdeKlantenPasNummer;

		return terugTeGevenKlantenPasNummer;
	}

	public synchronized int geeftNieuweBetalingNummer() {
		GegenereerdeBetalingNummer += 1;
		return GegenereerdeBetalingNummer;
	}

	public synchronized void voegNieuweOnderhoudsAfspraakToe(Calendar nweDatum,
			Calendar nweBeginTijd, Calendar nweEindTijd, Klant refKlant,
			String soortKlus) throws AfspraakMakenException, TijdvakException {

		if (refKlant != null && refKlant.getAuto() != null) {

			// kijkt of de tijdvak waardes valide zijn zo niet > exception
			Tijdvak nweT = new Tijdvak(nweDatum, nweBeginTijd, nweEindTijd);
			nweT.controleerTijdvakDatums(nweDatum, nweBeginTijd, nweEindTijd);

			Klus k = new Klus(geefNieuweKlusCode(), soortKlus, "inbehandeling",
					"", 0);
			k.controleerSoortKlus(soortKlus);

			// kijkt of er een monteur gevonden kan worden zo niet > exception
			Monteur gevondeMonteur = null;
			gevondeMonteur = getBeschikbareMonteur(nweT);

			// kijkt of het tijdvak toegevoegd kan worden zo niet > exception
			gevondeMonteur.voegTijdvakToe(nweT);

			k.setAuto(refKlant.getAuto());
			nweT.setKlus(k);

		} else {
			throw new AfspraakMakenException(
					"Klant bestaat niet of heeft nog geen auto!");
		}
	}

	public Monteur getBeschikbareMonteur(Tijdvak nweT)
			throws AfspraakMakenException {
		String exceptionMsgs = "";
		Monteur deGevondeMonteur = null;
		if (alleMonteurs != null) {
			for (Monteur m : alleMonteurs) {
				try {
					if (!m.heeftTijdvak(nweT)) {
						deGevondeMonteur = m;
						break;
					}
				} catch (AfspraakMakenException ame) {
					exceptionMsgs = ame.getMessage().toString();
				}
			}
		}
		if (deGevondeMonteur == null) {
			throw new AfspraakMakenException(exceptionMsgs);
		}
		return deGevondeMonteur;
	}

	public ArrayList<Date> getAlleBeschikbareBedrijfTijden(Calendar nweTijd)
			throws AfspraakMakenException {
		ArrayList<Date> alleTijdenDates = new ArrayList<Date>();

		for (Monteur m : alleMonteurs) {
			ArrayList<Date> alleMonteurTijden = m
					.getAlleBeschikbareMonteursTijden(nweTijd);

			for (Date i : alleMonteurTijden) {
				if (!alleTijdenDates.contains(i)) {

					alleTijdenDates.add(i);
				}
			}

		}
		if (alleTijdenDates != null && alleTijdenDates.size() > 0) {
			DateComparator dc = new DateComparator();
			Collections.sort(alleTijdenDates, dc);

			return alleTijdenDates;

		} else {
			throw new AfspraakMakenException(
					"Er zijn geen beschikbare tijden op deze datum!, probeer een andere datum.");
		}
	}

	public synchronized User UserInloggen(User nweU) throws UserException {

		User inloggendeUser = null;

		if (deBeheerder != null) {

			if (ControleerUserInloggen(nweU, deBeheerder)) {
				inloggendeUser = deBeheerder;
			}
		}

		if (inloggendeUser == null && alleMonteurs != null) {

			for (User m : alleMonteurs) {
				if (ControleerUserInloggen(nweU, m)) {
					inloggendeUser = m;
					break;
				}
			}
		}
		if (inloggendeUser == null && alleKlanten != null) {
			for (User k : alleKlanten) {
				if (ControleerUserInloggen(nweU, k)) {
					inloggendeUser = k;
					break;
				}
			}
		}
		if (inloggendeUser == null) {
			throw new UserException("Username and/or password are incorrect!");
		} else {
			return inloggendeUser;
		}
	}

	public boolean ControleerUserInloggen(User inloggendeUser,
			User bestaandeUser) {
		boolean inloggenSuccesvol = false;

		if (inloggendeUser.getUsername().equals(bestaandeUser.getUsername())
				&& inloggendeUser.getPassword().equals(
						bestaandeUser.getPassword())) {
			inloggenSuccesvol = true;
		}

		return inloggenSuccesvol;
	}

	public boolean ControleerUserNaam(User registrerendeUser, User bestaandeUser) {
		boolean heeftUsername = false;

		if (registrerendeUser.getUsername().equals(bestaandeUser.getUsername())) {
			heeftUsername = true;
		}

		return heeftUsername;
	}

	public synchronized void UserRegistreren(User nweU)
			throws RegistrerenException, UserException, AutoException {

		if (deBeheerder != null) {

			if (ControleerUserNaam(nweU, deBeheerder)) {
				throw new RegistrerenException(
						"Er bestaat al een gebruiker met deze naam!");
			}
		}

		if (nweU instanceof Klant) {

			if (alleKlanten != null && alleKlanten.size() > 0) {

				if (!heeftKlant(nweU.getUsername())) {

					if (controleerAlleUserInfo(nweU) && controleerAlleAutoInfo(((Klant) nweU).getAuto()) ) {

						voegKlantToe((Klant) nweU);
					}
				} else {
					throw new RegistrerenException(
							"Er bestaat al een gebruiker met deze naam!");
				}

			} else {
				if (controleerAlleUserInfo(nweU) && controleerAlleAutoInfo(((Klant) nweU).getAuto())) {

					voegKlantToe((Klant) nweU);
				}
			}
		}

		if (nweU instanceof Monteur) {

			if (alleMonteurs != null) {

				if (!heeftMonteur(nweU.getUsername())) {

					if (controleerAlleUserInfo(nweU)) {

						voegMedewerkerToe((Monteur) nweU);

					}

				} else {
					throw new RegistrerenException(
							"Er bestaat al een gebruiker met deze naam!");
				}

			} else {
				if (controleerAlleUserInfo(nweU)) {

					voegMedewerkerToe((Monteur) nweU);
				}
			}
		}

	}

	public boolean controleerAlleUserInfo(User nweU) throws UserException {
		boolean allesGoedGecontroleerd = false;

		if (nweU.controleerAdres(nweU.getAdres())) {

			if (nweU.controleerEmailAddress(nweU.getEmail())) {

				if (nweU.controleerGeboorteDatum(nweU.getGeboortedatum())) {

					if (nweU.controleerUsername(nweU.getUsername())) {

						if (nweU.controleerGeslacht(nweU.getGeslacht())) {

							if (nweU.controleerNaam(nweU.getNaam())) {

								if (nweU.controleerWachtwoord(nweU
										.getPassword())) {

									if (nweU.controleerWoonplaats(nweU
											.getWoonplaats())) {

										allesGoedGecontroleerd = true;

									} else {
										throw new UserException(
												"woonplaats is incorrect!, mag alleen carracters: a-zA-Z ontvangen en lengte 3+!");
									}
								} else {
									throw new UserException(
											"wachtwoord is incorrect!, mag alleen carracters a-zA-Z0-9\\._\\- ontvangen en lengte 5-10!");
								}
							} else {
								throw new UserException(
										"naam is incorrect!, mag alleen carracters [a-zA-Z] ontvangen en 3-18 lengte!");
							}
						} else {
							throw new UserException(
									"geslacht is incorrect!, mag alleen man of vrouw zijn (kleine letters)!");
						}

					} else {
						throw new UserException(
								"username is incorrect!, mag alleen carracters [a-zA-Z0-9\\._\\-] ontvangen en 5-10 lengte!");
					}
				} else {
					throw new UserException(
							"geboortedatum is incorrect!, mag alleen precies DD-MM-JJJJ zijn!");
				}
			} else {
				throw new UserException(
						"email is incorrect!, vul een valide email address in!");
			}
		} else {
			throw new UserException(
					"adres is incorrect!, mag alleen carracters [a-zA-Z0-9] en 3-12 lengte!");
		}

		return allesGoedGecontroleerd;
	}

	public boolean controleerAlleAutoInfo(Auto nweA) throws AutoException {
		boolean allesGoedGecontroleerd = false;

		if (nweA.controleerAutoMerk(nweA.getMerk())) {

			if (nweA.controleerAutoNaam(nweA.getNaam())) {

				if (nweA.controleerBrandstoftype(nweA.getBrandstofType())) {

					if (nweA.controleerKenteken(nweA.getKenteken())) {

						if (!controleerOfKentekenVoorkomt(nweA.getKenteken())) {
						allesGoedGecontroleerd = true;
						} else { 
							throw new AutoException("Deze kenteken/auto komt al voor!");
						}

					} else {
						throw new AutoException(
								"Kenteken is incorrect!, mag alleen carracters: [A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2} ontvangen!");
					}
				} else {
					throw new AutoException(
							"Brandstoftype is incorrect!, mag alleen carracters a-zA-Z0-9 ontvangen en lengte 3-14!");
				}
			} else {
				throw new AutoException(
						"Autonaam is incorrect!, mag alleen carracters a-zA-Z0-9 ontvangen en 3-14 lengte!");
			}
		} else {
			throw new AutoException(
					"Automerk is incorrect!, mag alleen alleen carracters a-zA-Z0-9 en 3-14 lengte!");
		}

		return allesGoedGecontroleerd;
	}
	
	public boolean controleerOfKentekenVoorkomt(String kenteken) throws AutoException {
		boolean komtVoor = false;
		if (alleKlanten != null && !alleKlanten.isEmpty()) {
			for (Klant deKlant : alleKlanten) {
				if (deKlant.getAuto().getKenteken().equals(kenteken)) {
					komtVoor = true;
					break;
				}
			}
		}
		return komtVoor;
	}

	public void voegOnderdeelToe(Onderdeel nweO) {

		if (nweO != null) {
			if (!heeftOnderdeel(nweO.getArtikelNummer())) {
				alleOnderdelen.add(nweO);
			}
		}
	}

	public boolean heeftKlant(String nm) {
		boolean b = false;
		for (Klant k : alleKlanten) {
			if (k.getUsername().equals(nm)) {
				b = true;
				break;
			}
		}
		return b;
	}

	public void voegKlantToe(Klant nweK) {
		if (nweK != null) {
			if (!heeftKlant(nweK.getUsername())) {
				alleKlanten.add(nweK);
			}
		}
	}

	public boolean heeftKlantAuto(String kent) {
		boolean b = false;

		for (Klant k : alleKlanten) {
			if (k.getAuto().getKenteken().equals(kent)) {
				b = true;

			}
		}
		return b;
	}

	public void voegMedewerkerToe(Monteur nweM) {
		if (nweM != null) {
			if (!heeftMonteur(nweM.getUsername())) {
				alleMonteurs.add(nweM);
			}
		}
	}

	public boolean heeftMonteur(String username) {
		boolean b = false;
		for (Monteur m : alleMonteurs) {
			if (m.getUsername().equals(username)) {
				b = true;
			}
		}
		return b;
	}

	public boolean heefUser(User nweU) throws RegistrerenException {
		boolean b = false;

		if (deBeheerder != null) {

			if (ControleerUserNaam(nweU, deBeheerder)) {
				b = true;
			}
		}

		if (!b && alleMonteurs != null) {

			for (User m : alleMonteurs) {
				if (ControleerUserNaam(nweU, m)) {
					b = true;
					break;
				}
			}
		}
		if (!b && alleKlanten != null) {
			for (User k : alleKlanten) {
				if (ControleerUserNaam(nweU, k)) {
					b = true;
					break;
				}
			}
		}
		if (b) {
			throw new RegistrerenException(
					"Er bestaat al een gebruiker met deze naam!");
		}

		return b;
	}

	public boolean heeftParkeerplaats(int nr) {
		boolean b = false;
		for (Parkeerplaats p : alleParkeerplaatsen) {
			if (p.getNummer() == nr) {
				b = true;
			}
		}
		return b;
	}

	public void voegReserveringToeAutomatisch(String dm, String un) {
		ParkeerplaatsReservering nwePr = new ParkeerplaatsReservering(dm, un);
		for (Parkeerplaats p : alleParkeerplaatsen) {
			boolean toegevoegd = p.voegReserveringToe(nwePr);

			if (toegevoegd == true) {
				break;
			}
		}
	}

	public String getAlleReserveringen() {
		String s = "";
		if (alleParkeerplaatsen != null) {
			for (Parkeerplaats p : alleParkeerplaatsen) {
				s += p;
			}
		}
		return s;
	}

	public Pomp getPomp() {
		return dePomp;
	}

	public void voegTankBeurtToe(String pasCode, String pasNummer,
			String benzineType, int aantalLiters) throws TankbeurtException,
			BrandstofException {
		Klant gevondeKlant = null;

		if (dePomp == null) {
			throw new TankbeurtException("Er is geen pomp aanwezig!");
		}
		if (pasCode == null || !(pasCode.trim().length() > 0)) {
			throw new TankbeurtException("Geen pascode ingevuld!");
		}
		if (pasNummer == null || !(pasNummer.trim().length() > 0)) {
			throw new TankbeurtException("Geen pasnummer ingevuld!");
		}
		if (!(aantalLiters > 0)) {
			throw new TankbeurtException(
					"Geen geldige hoeveelheid liters getanked!");
		}

		gevondeKlant = zoekKlantMetPas(pasNummer, pasCode);
		if (gevondeKlant == null) {
			throw new TankbeurtException("Ongeldige pascode of pasnummer!");
		}

		if (!gevondeKlant.getAuto().controleerBrandstoftype(benzineType)) {
			throw new TankbeurtException("Geen geldige brandstoftype gekozen!");
		}
		Brandstof hetBrandstofType = dePomp.zoekBrandStof(benzineType);
		Betaling deBetaling = new Betaling(GegenereerdeBetalingNummer,
				benzineType);
		deBetaling.addBrandstofToBetaling(hetBrandstofType, aantalLiters);
		dePomp.addBetaling(deBetaling);

	}

	public Klant zoekKlantMetPas(String pasNummer, String pasCode) {
		Klant gevondeKlant = null;

		if (alleKlanten != null && !alleKlanten.isEmpty()) {
			for (Klant mogelijkeKlant : alleKlanten) {
				KlantenPas deKlantePas = mogelijkeKlant.getKlantenPas();
				if (pasNummer.equals(deKlantePas.getPasNummer())
						&& pasCode.equals(deKlantePas.getPasCode())) {
					gevondeKlant = mogelijkeKlant;
					break;
				}
			}
		}
		return gevondeKlant;
	}
	
	public boolean checkDatumGoed(String datum) throws ReserveringException {
		  boolean b = false;
		  if(datum == null){
			  throw new ReserveringException(
						"Datum is NULL!");  		   
		  }
		  if(datum.length() == 10) {
		   Scanner sc = new Scanner(datum);
		   sc.useDelimiter("-");
		   int dag = Integer.parseInt(sc.next());
		  
		   int maand = Integer.parseInt(sc.next());
		 
		   int jaar = Integer.parseInt(sc.next());
		   
		   sc.close();
		   if(maand == 1 || maand == 3 || maand == 5 || maand == 7 || maand == 8 || maand == 10 || maand == 12) {
		    if(dag > 0 && dag <= 31) {
		     b = true;
		    }
		   }
		   
		   if(maand == 4 || maand == 6 || maand == 9 || maand == 11) {
		    if(dag > 0 && dag <= 30) {
		     b = true;
		    }
		   }
		   
		   if(maand == 2) {
		    if(jaar % 4 == 0) {
		     if(dag > 0 && dag <= 29) {
		      b = true;
		     }
		    }
		    else {
		     if(dag > 0 && dag <= 28) {
		      b = true;
		     }
		    }
		   }
		
		   if (b == false) {
				throw new ReserveringException(
						"Datum is niet geldig!");  		   
		  }
		  }else{
			  throw new ReserveringException("Datum is niet volledig ingevuld");
			  }
		  return b;
		  
		 }
	
	@SuppressWarnings("deprecation")
	public boolean checkInputDatum(String datum) throws ReserveringException{
		boolean b = false;
	 Calendar huidigeTijd = Calendar.getInstance();
	 huidigeTijd.setLenient(false);
	 int huidigeDag = huidigeTijd.getTime().getDate();
	 int huidigeMaand = huidigeTijd.getTime().getMonth() + 1;
	 int huidigeJaar = huidigeTijd.getTime().getYear() + 1900;
	 
	  Scanner sc = new Scanner(datum);
	   sc.useDelimiter("-");
	   int dag = Integer.parseInt(sc.next());
	   
	   int maand = Integer.parseInt(sc.next());
	  
	   int jaar = Integer.parseInt(sc.next());
	   
	   sc.close();
	   
	   if(jaar >= huidigeJaar){
		   if(maand> huidigeMaand ){
			 b=true;   
		   }else if (huidigeMaand == maand && dag > huidigeDag){
			   b = true;
		  	  
			  
		   }
		   
	   }
	   if (b == false) {
			throw new ReserveringException(
					"Datum is niet geldig!");
	   }
	return b;
	   
	   
	}
	
	public ArrayList<Tijdvak> getAlleKlantTijdvakken(String klantKenteken) {
		ArrayList<Tijdvak> alleTerugTegevenTijden = new ArrayList<Tijdvak>();
		ArrayList<Tijdvak> alleMogelijkeTijdvakken = new ArrayList<Tijdvak>();
		if (klantKenteken != null && !("".equals(klantKenteken))) {
			if (alleMonteurs != null && !alleMonteurs.isEmpty()) {
				for (Monteur gevondeMonteur : alleMonteurs) {
					ArrayList<Tijdvak> monteursTijdvakken = gevondeMonteur.getAlleTijdvakken();
					if (monteursTijdvakken != null && !monteursTijdvakken.isEmpty()) {
						for (Tijdvak m : monteursTijdvakken)
							alleMogelijkeTijdvakken.add(m);
					}
				}
			}
			if (!alleMogelijkeTijdvakken.isEmpty()) {
				for (Tijdvak t : alleMogelijkeTijdvakken) {
					if (t.getKlus().getAuto().getKenteken().equals(klantKenteken)) {
						alleTerugTegevenTijden.add(t);
					}
				}
			}
		}
		return alleTerugTegevenTijden;
	}
	
	public ArrayList<Tijdvak> getalleBeheerdersTijdvakken() {
		ArrayList<Tijdvak> alleTerugTeGevenTijdvakken = new ArrayList<Tijdvak>();
		
		if (alleMonteurs != null && !alleMonteurs.isEmpty()) {
			for (Monteur m : alleMonteurs) {
				ArrayList<Tijdvak> monteurTijdvakken = m.getAlleTijdvakken();
				if (monteurTijdvakken != null && !monteurTijdvakken.isEmpty()) {
					for (Tijdvak t : monteurTijdvakken) {
						alleTerugTeGevenTijdvakken.add(t);
					}
				}
			}
		}
		return alleTerugTeGevenTijdvakken;
	}
	
	public void verwijderKlant(String username) throws UserException {
		boolean isVerwijderd = false;
		if(username != null && username.trim().length() > 0) {
			for (Klant k : alleKlanten) {
				if(k.getGebruikersNaam().equals(username)) {
					isVerwijderd = alleKlanten.remove(k);
					break;
				}
			}
			
			if(!isVerwijderd) {
				throw new UserException("Gebruiker niet aanwezig");
			}
		}
		else {
			throw new UserException("Geen gebruikersnaam ingevuld");
		}
	}
	
	public void verwijderMonteur(String username) throws UserException {
		boolean isVerwijderd = false;
		if(username != null && username.trim().length() > 0) {
			for (Monteur m : alleMonteurs) {
				if(m.getGebruikersNaam().equals(username)) {
					isVerwijderd = alleMonteurs.remove(m);
					break;
				}
			}
			
			if(!isVerwijderd) {
				throw new UserException("Medewerker niet aanwezig");
			}
		}
		else {
			throw new UserException("Geen gebruikersnaam ingevuld");
		}
		
		
	}
	
	public Klant zoekKlant(String kenteken) {
		Klant k = null;
		if (alleKlanten != null) {
			for (Klant kl : alleKlanten) {
				if (kl.getAuto().getKenteken().equals(kenteken)) {
					k = kl;
				}
			
			}
			
		}
		return k;
	}
	
	public boolean checkOnderdeelGoed(String artikelNummer, String artikelNaam, int artikelAantal){
		boolean b = true;
	 
		if(artikelNummer == null ){
			b =false;	
		}
		if(artikelNaam == null){
			b =false;
		}
		if(artikelAantal <= 0){
			b =false;
		}
		
		return b;
	}
	
	public boolean heeftOnderdeel(String i) {
		boolean b = false;
		for (Onderdeel o : alleOnderdelen) {
			if (o.getArtikelNummer().equals(i)) {
				b = true;
			}
		}
		return b;
	}
		
	public ArrayList<Parkeerplaats> getAlleParkeerplaatsen() {
		return alleParkeerplaatsen;
	}

	public ArrayList<Klant> getAlleKlanten() {
		return alleKlanten;
	}

	public ArrayList<Monteur> getAlleMonteurs() {
		return alleMonteurs;
	}

	public ArrayList<Onderdeel> getAlleOnderdelen() {
		return alleOnderdelen;
	}

	public Beheerder getBeheerder() {
		return deBeheerder;
	}

	public void setBeheerder(Beheerder nweB) {
		deBeheerder = nweB;
	}

	public String toString() {

		String s = "Het bedrijf: " + naam + ", plaats: " + plaats + ", jaar: "
				+ jaar + ". \n";

		if (deBeheerder != null) {
			s += "Beheerder: \n" + deBeheerder.toString();
		}

		if (alleKlanten != null) {
			s += "Alle klanten: \n";

			for (Klant k : alleKlanten) {
				s += k;
			}
		} else {
			s += "Er zijn nog geen klanten! \n";
		}

		if (alleMonteurs != null) {
			s += "Alle monteuren: \n";

			for (Monteur m : alleMonteurs) {
				s += m;
			}
		} else {
			s += "Er zijn nog geen monteuren! \n";
		}

		return s;
	}

}
