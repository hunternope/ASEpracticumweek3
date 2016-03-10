/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com) && Haydar Yilmaz
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;
import java.util.ArrayList;

public class Klus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1971049890739198411L;
	private String klusCode;
	private String soortKlus;
	private String status;
	private String werkzaamheden;
	private double manuur;
	@SuppressWarnings("unused")
	private ArrayList<GebruikteOnderdelen> deGebruikteOnderdelen;
	private Monteur deUitvoerende;
	private Auto betreftAuto;

	public Klus(String kc, String sk, String st, String wh, double man) {
		manuur = man;
		klusCode = kc;
		soortKlus = sk;
		status = st;
		werkzaamheden = wh;
		deGebruikteOnderdelen = new ArrayList<GebruikteOnderdelen>();
	}

	public void controleerSoortKlus(String soortKlus)
			throws AfspraakMakenException {
		if (soortKlus == null) {
			throw new AfspraakMakenException("Klus is niet ingevuld! (null)!");
		}

		if (!"apk keuring".equals(soortKlus)) {
			if (!"onderhoudsbeurt".equals(soortKlus)) {
				throw new AfspraakMakenException(
						"Klus komt niet overeen met 'apk keuring' of 'onderhoudsbeurt'!");
			}
		}
	}

	/*
	 * public void voegGebruikteOnderdelenToe(GebruikteOnderdelen on, Onderdeel
	 * o) { if (on != null && o != null) { if
	 * (!heeftGebruikteOnderdeel(o.getArtikelNummer())) {
	 * deGebruikteOnderdelen.add(on); on.setDeOnderdeel(o);
	 * 
	 * }
	 * 
	 * else if (heeftGebruikteOnderdeel(o.getArtikelNummer())) { for
	 * (GebruikteOnderdelen g : deGebruikteOnderdelen) { if
	 * (g.getOnderdeel().getArtikelNummer() == o .getArtikelNummer()) {
	 * g.setAantal(g.getAantal() + on.getAantal()); } } } } }
	 * 
	 * public boolean heeftGebruikteOnderdeel(int i) { boolean b = false;
	 * 
	 * for (GebruikteOnderdelen g : deGebruikteOnderdelen) { if
	 * (g.getOnderdeel().getArtikelNummer() == i) { b = true; } } return b; }
	 */

	public void setMonteurToe(Monteur nweM) {

		if (nweM != null && !heeftMonteur(nweM.getNaam())) {

			deUitvoerende = nweM;
		}
	}

	public Monteur getMonteur() {
		Monteur m = null;

		if (deUitvoerende != null) {
			m = deUitvoerende;
		}
		return m;
	}

	public boolean heeftMonteur(String nm) {
		boolean b = false;
		if (deUitvoerende != null) {
			if (deUitvoerende.getNaam().equals(nm)) {
			}
			b = true;
		}
		return b;
	}

	public void verwijderMonteur(Monteur gezochteM) {
		deUitvoerende = null;
	}

	public void setAuto(Auto nweA) {

		betreftAuto = nweA;
	}

	public Auto getAuto() {
		return betreftAuto;
	}

	public String getKlusCode() {

		return klusCode;
	}

	public String getSoortKlus() {

		return soortKlus;

	}

	public String getWerkzaamheden() {
		return werkzaamheden;
	}

	public void setStatus(String sta) throws KlusAfvinkException {
		if (controleerStatus(status)) {
			status = sta;
		}
	}

	public String getStatus() {

		return status;
	}

	public void setManuren(int man) throws KlusAfvinkException {
		if (controleerManuren(man)) {
			manuur = man;
		}

	}

	public boolean controleerManuren(int manuur) {
		boolean valideManuren = true;
		if (manuur == 0 || manuur > 9) {
			valideManuren = false;
		}		
		return valideManuren;
	}

	public boolean controleerStatus(String status) throws KlusAfvinkException {
		boolean b = true;
		if (!status.equals("inbehandeling")) {
			if (!status.equals("klusvoltooid")) {
				throw new KlusAfvinkException(
						"Status wijkt af mag alléén 'inbehandeling' of 'klus voltooid' ");
			}
		} else {
			b = true;
		}
		return b;
	}

	public double getManuur() {
		return manuur;
	}

	public boolean controleerWerkzaamheden(String werkzaamheden)
			throws KlusAfvinkException {
		boolean b = true;
		if (werkzaamheden == null) {
			throw new KlusAfvinkException(
					"Te weinig informatie ingevuld over de werkzaamheden");
		}
		if (werkzaamheden.length() < 0) {
			throw new KlusAfvinkException(
					"Niks ingevuld vul de werkzaamheden in");
		}
		if (werkzaamheden.length() < 10) {
			throw new KlusAfvinkException(
					"Te weinig informatie ingevuld over de werkzaamheden");
		}
		if (werkzaamheden.length() > 100) {
			throw new KlusAfvinkException("Teveel characters > 100");
		} else {
			b = true;
		}
		return b;
	}

	public void setWerkzaamheden(String wz) throws KlusAfvinkException {
		if (controleerWerkzaamheden(wz)) {
			werkzaamheden = wz;
		}
	}

	public String toString() {
		String s = "De klus: " + getKlusCode() + " Heeft de status: "
				+ getStatus() + " Aantal gewerkte uren aan klus : "
				+ getManuur() + "De volgende werkzaamheden zijn uitgevoerd : "
				+ getWerkzaamheden();
		if (betreftAuto != null) {
			s += betreftAuto.toString();
		}
		return s;
	}

}