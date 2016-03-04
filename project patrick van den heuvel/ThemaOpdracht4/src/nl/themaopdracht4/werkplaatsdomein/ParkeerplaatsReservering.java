package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;

/* Copyright Sven van Walderveen, 30 mei 2013 */
public class ParkeerplaatsReservering implements Serializable {
	
	private static final long serialVersionUID = 4015013152429202414L;
	private String datum, username;
	
	public ParkeerplaatsReservering(String dm, String un){
		datum = dm;
		username = un;
	}
	
	public String getDatum() {
		return datum;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String toString() {
		String s = "is gereserveerd op " + datum + " door klant " + username + "\n" + "<br/>";
		return s;
		
	}
}
