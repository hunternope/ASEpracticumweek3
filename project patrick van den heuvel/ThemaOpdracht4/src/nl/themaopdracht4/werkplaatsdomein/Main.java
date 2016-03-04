package nl.themaopdracht4.werkplaatsdomein;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Bedrijf b1 = new Bedrijf("HU", "Neijnoord", "2013");
		Monteur m1 = new Monteur("iestss", "iestss", "iestss", "iestss",
				"iestss", "iestss", "iestss", "iestss");
		Monteur m2 = new Monteur("aiestss", "aiestss", "aiestss", "aiestss",
				"aiestss", "aiestss", "aiestss", "aiestss");

		Calendar beg1 = Calendar.getInstance();
		Calendar ein1 = Calendar.getInstance();
		beg1.setLenient(false);
		ein1.setLenient(false);

		String datum = "02/01/2013";

		DateFormat fromFormat = new SimpleDateFormat("MM/dd/yyyy");
		fromFormat.setLenient(false);
		DateFormat toFormat = new SimpleDateFormat("dd/MM/yyyy");
		toFormat.setLenient(false);
		String dateStr = datum;
		java.util.Date date = null;
		try {
			date = fromFormat.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(toFormat.format(date));

		Calendar calinput = Calendar.getInstance();
		calinput.setLenient(false);
		calinput.setTime(date);
		/*
		 * System.out.println(formatDate(calinput));
		 * System.out.println(calinput.get(calinput.YEAR));
		 * System.out.println(calinput.get(calinput.MONTH));
		 * System.out.println(calinput.get(calinput.DAY_OF_MONTH));
		 * System.out.println(calinput.getTime());
		 */

		// //Date date = new Date(s)
		// Calendar zoektijd = Calendar.getInstance();
		// zoektijd.setLenient(false);
		// zoektijd.set(2013, 01, 01);
		// zoektijd.setTime(date);
		// zoektijd.set(Calendar.HOUR_OF_DAY, 0);
		// zoektijd.set(Calendar.MINUTE, 0);
		// zoektijd.set(Calendar.SECOND, 0);
		// zoektijd.set(Calendar.MILLISECOND, 0);
		// date = zoektijd.getTime();
		// System.out.println(date);
		beg1.set(2013, 01, 02, 12, 00, 00);
		ein1.set(2013, 01, 02, 13, 00, 00);
		//
		Calendar beg2 = Calendar.getInstance();
		Calendar ein2 = Calendar.getInstance();
		beg2.setLenient(false);
		ein2.setLenient(false);

		beg2.set(2013, 01, 02, 15, 00, 00);
		ein2.set(2013, 01, 02, 17, 00, 00);

		try {
			Tijdvak t1 = new Tijdvak(calinput, beg1, ein1);
			// Tijdvak t2 = new Tijdvak(calinput, beg2, ein2);
			Tijdvak t3 = new Tijdvak(calinput, beg2, ein2);
			Tijdvak t4 = new Tijdvak(calinput, beg1, ein1);

			m1.voegTijdvakToe(t1);
			// m1.voegTijdvakToe(t2);
			m1.voegTijdvakToe(t4);
			m1.voegTijdvakToe(t4);
			m2.voegTijdvakToe(t3);
		} catch (AfspraakMakenException ame) {
			ame.printStackTrace();
		} catch (TijdvakException te) {
			te.printStackTrace();
		}
		//
		b1.voegMedewerkerToe(m1);
		// b1.voegMedewerkerToe(m2);
		//
		//
		// // Klant ka1 = new Klant("Haydar", "1@1");
		// // Klant ka2 = new Klant("Sven", "2@2");
		// /*
		// * Date[] iest = m1.getAlleDates(); for (int i = 0; i < iest.length;
		// * i++) { if (iest[i] != null) {
		// System.out.println(iest[i].toString());
		// * } }
		// */
		//
		ArrayList<Date> printalledatetijden = null;
		try {
			printalledatetijden = b1.getAlleBeschikbareBedrijfTijden(calinput);
			int i = 0;
			for (Date d : printalledatetijden) {
				if (d != null) {
					System.out.println("positie: " + i + " Staat: "
							+ d.toString());
					i++;
				}
			}
		} catch (AfspraakMakenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * private static String formatDate(Calendar nweC) {
	 * 
	 * String a = nweC.get(Calendar.DAY_OF_MONTH) + "-" +
	 * (nweC.get(Calendar.MONTH) + 1) + "-" + nweC.get(Calendar.YEAR); return a;
	 * }
	 */
}
