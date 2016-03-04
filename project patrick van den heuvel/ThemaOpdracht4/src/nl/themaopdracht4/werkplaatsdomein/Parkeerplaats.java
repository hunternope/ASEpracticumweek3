package nl.themaopdracht4.werkplaatsdomein;

import java.io.Serializable;
import java.util.ArrayList;

/* Copyright Sven van Walderveen, 30 mei 2013 */
public class Parkeerplaats implements Serializable {

	private static final long serialVersionUID = 3292228938722139428L;
	private int nummer;
	private ArrayList<ParkeerplaatsReservering> alleReserveringen;

	public Parkeerplaats(int id) {
		nummer = id;
		alleReserveringen = new ArrayList<ParkeerplaatsReservering>();
	}
		
	public int getNummer() {
		return nummer;
	}
	
	public ArrayList<ParkeerplaatsReservering> getAlleParkeerplaatsReserveringen() {
		return alleReserveringen;
	}

	public boolean voegReserveringToe(ParkeerplaatsReservering nwePR) {
		boolean b = false;
		if (nwePR != null) {
			if (!heeftParkeerplaatsReservering(nwePR.getDatum())) {
				alleReserveringen.add(nwePR);
				b = true;
			} 
		}
		return b;

	}

	public boolean heeftParkeerplaatsReservering(String dm) {
		boolean b = false;
		for (ParkeerplaatsReservering pr : alleReserveringen) {
			if (pr.getDatum().equals(dm)) {
				b = true;
			}
		}
		return b;
	}


	public String toString() {
		String s = "";
		if (alleReserveringen != null) {
			for (ParkeerplaatsReservering pr : alleReserveringen) {
				s += "Parkeerplek " + getNummer() + " " + pr.toString();
			}
		} else {
			s += "Geen reserveringen voor deze parkeerplaats.";
		}
		return s;
	}
}
