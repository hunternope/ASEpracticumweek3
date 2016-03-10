package nl.themaopdracht4.werkplaatsdomein;

public class Klant extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8738169207613591224L;
	private Auto deAuto;
	private KlantenPas deKlantenPas;

	public Klant(String us, String pw, String nm, String adr, String wop,
			String geb, String gesl, String em) {
		super(us, pw, nm, adr, wop, geb, gesl, em);

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

	public void setAuto(Auto nweA) {
		
		deAuto = nweA;
		
	}

	public Auto getAuto() {
		return deAuto;
	}
	
	public KlantenPas getKlantenPas() {
		return deKlantenPas;
	}
	
	public void setKlantenPas(KlantenPas nweKp) {
		deKlantenPas = nweKp;
	}

	public String toString() {
		String s = "gebruikersnaam :" + getGebruikersNaam() + ", " + getNaam()
				+ ", passnummer: " + deKlantenPas.getPasNummer() + " Klant object uitgeprint!\n";
		if (deAuto != null) {
			s += deAuto.toString();
		}
		return s;
	}
}
