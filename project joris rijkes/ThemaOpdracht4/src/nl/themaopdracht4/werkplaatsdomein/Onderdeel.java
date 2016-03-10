package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class Onderdeel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -146782474537800076L;
	private String artikelNummer;
	private String naam;
	private int Aantal;
	
	public Onderdeel(String aN, String nm, int at) {
		
		artikelNummer = aN;
		naam = nm;
		Aantal = at;
	}
	
	public Onderdeel(){
		
	}
	public void setArtikelNummer(String nwaN){
		artikelNummer = nwaN;
	}
	public void setNaam(String nwnm){
		naam = nwnm;
	}
	public void setAantal(int nwat){
		Aantal = nwat;
	}
	
	public String getArtikelNummer(){
		return artikelNummer;
	}
	
	public String getNaam() {
		return naam;
	}
	public int getAantal(){
		return Aantal;
	}
	
public String toString() {
		String s = " "+ getArtikelNummer() + "\t" +  getNaam() + "\t"+  getAantal()+ "\n" + "<br/>";
		return s;
	}
}
