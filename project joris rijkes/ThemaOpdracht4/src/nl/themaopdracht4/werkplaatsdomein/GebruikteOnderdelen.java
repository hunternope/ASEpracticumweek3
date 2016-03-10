package nl.themaopdracht4.werkplaatsdomein;

public class GebruikteOnderdelen {

	private int aantal;
	private Onderdeel deOnderdeel;
	
	public GebruikteOnderdelen(int an) {
		
		aantal = an;
		Onderdeel ond = new Onderdeel();
		deOnderdeel = ond;
	}
	
	public void setDeOnderdeel(Onderdeel nweO) {
		if (nweO != null) {
			deOnderdeel = nweO;
		}
	}
	
	public Onderdeel getOnderdeel() {
		return deOnderdeel;
	}
	
	public int getAantal() {
		return aantal;
	}
	
	public void setAantal(int i) {
		aantal = i;
	}
	
	public String toString() {
		String s = "Hoeveelheid: " + aantal + ", " + deOnderdeel.toString() + "\n";
		return s;
	}
	
	
}
