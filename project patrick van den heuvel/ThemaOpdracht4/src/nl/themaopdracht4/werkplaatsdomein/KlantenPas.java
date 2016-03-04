/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 23 jun. 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

public class KlantenPas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9005945430000543416L;
	private String passNummer;
	private String passCode;
	
	public KlantenPas(String passNummer, String passCode) {
		
		this.passNummer = passNummer;
		this.passCode = passCode;
		
	}
	
	public String getPasCode() {
		return passCode;
	}
	
	public void setPasCode(String pascode){
		this.passCode = pascode;
	}
	
	public String getPasNummer() {
		return passNummer;
	}
}

