/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 19 jun. 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.junittests;

import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import nl.themaopdracht4.werkplaatsdomein.AfspraakMakenException;
import nl.themaopdracht4.werkplaatsdomein.Auto;
import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.Klant;
import nl.themaopdracht4.werkplaatsdomein.Monteur;
import nl.themaopdracht4.werkplaatsdomein.TijdvakException;

public class TestCasesAfspraakMaken {

	private static Bedrijf hetBedrijf;
	@SuppressWarnings("unused")
	private static Monteur monteur1;
	private static Klant deKlant1;
	private static Auto deAuto1;
	private static Calendar calDatum, calBeginTijd, calEindTijd;
	private static DateFormat afromFormat, toFormat;

	@SuppressWarnings("deprecation")
	@Before
	public void setup() {

		Bedrijf b = new Bedrijf("Gemaakt", "Door", "Joris Rijkes");
		Klant k = new Klant("MrSven1", "Hogeschool1", "Sven", "Neijnoord1",
				"Utrecht", "14-07-1994", "man", "Sven@gmail.com");
		Monteur m = new Monteur("deMonteur1", "Tester2", "aTestera",
				"testscript", "testscript", "10-10-1990", "man",
				"tester1@gmail.com");
		Auto a = new Auto("SvensAuto", "Sven", "A1-A2-A3", "Diesel");
		hetBedrijf = b;
		monteur1 = m;
		deKlant1 = k;
		deAuto1 = a;
		deKlant1.setAuto(deAuto1);
		hetBedrijf.voegKlantToe(k);
		hetBedrijf.voegMedewerkerToe(m);

		calDatum = Calendar.getInstance();
		calDatum.setLenient(false);
		Date datum = null;
		afromFormat = new SimpleDateFormat("MM/dd/yyyy");
		afromFormat.setLenient(false);

		String dateStr = "08/08/2013";

		try {
			datum = afromFormat.parse(dateStr);
		} catch (ParseException e) {

		}
		calDatum.setTime(datum);
		//
		calBeginTijd = Calendar.getInstance();
		calBeginTijd.setLenient(false);
		toFormat = new SimpleDateFormat("kk:mm:ss");
		toFormat.setLenient(false);
		String beginTijd = "09:00:00";
		Date begindate = null;
		try {
			begindate = toFormat.parse(beginTijd);
		} catch (ParseException e) {

		}
		calBeginTijd.setTime(begindate);
		//
		calEindTijd = Calendar.getInstance();
		calEindTijd.setLenient(false);
		Date eindDate = begindate;
		eindDate.setHours(begindate.getHours() + 1);
		calEindTijd.setTime(eindDate);

	}

	@Test
	public void TestCase1_1GoedeDatumIngevuld() {
		try {
			hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
					calEindTijd, deKlant1, "apk keuring");
		} catch (AfspraakMakenException ame) {
			ame.printStackTrace();
			fail("geen goede datum ingevuld!");
		} catch (TijdvakException tve) {
			fail("geen goede datum ingevuld!");
		}
	}

	@Test(expected = TijdvakException.class)
	public void TestCase1_2FouteDatumIngevuld() throws AfspraakMakenException,
			TijdvakException {
		// datum is al geweest!
		Calendar calDatum1 = Calendar.getInstance();
		calDatum1.setLenient(false);
		Date datum = null;
		String dateStr = "01/01/2013";
		try {
			datum = afromFormat.parse(dateStr);
		} catch (ParseException e) {
			fail();
		}
		calDatum1.setTime(datum);

		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum1, calBeginTijd,
				calEindTijd, deKlant1, "apk keuring");
	}

	@Test(expected = TijdvakException.class)
	public void TestCase1_3GeenDatumIngevuld() throws AfspraakMakenException,
			TijdvakException {

		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(null, calBeginTijd,
				calEindTijd, deKlant1, "apk keuring");
	}

	@Test
	public void TestCase2_1GoedeBeginTijdIngevuld() {
		try {
			hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
					calEindTijd, deKlant1, "apk keuring");
		} catch (AfspraakMakenException ame) {
			ame.printStackTrace();
			fail("geen goede datum ingevuld!");
		} catch (TijdvakException tve) {
			fail("geen goede datum ingevuld!");
		}
	}

	@Test(expected = TijdvakException.class)
	public void TestCase2_2FouteBeginTijdIngevuld()
			throws AfspraakMakenException, TijdvakException {
		
		Calendar calBeginTijd1 = Calendar.getInstance();
		calBeginTijd1.setLenient(false);
		Date datum = null;
		String dateStr = "18:00:00";
		try {
			datum = toFormat.parse(dateStr);
		} catch (ParseException e) {
			fail();
		}
		calBeginTijd1.setTime(datum);

		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd1,
				calEindTijd, deKlant1, "apk keuring");
	}

	@Test(expected = TijdvakException.class)
	public void TestCase2_3GeenBeginTijdIngevuld()
			throws AfspraakMakenException, TijdvakException {
		
		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, null,
				calEindTijd, deKlant1, "apk keuring");
	}
	
	@Test
	public void TestCase3_1GoedeEindTijdIngevuld() {
		try {
			hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
					calEindTijd, deKlant1, "apk keuring");
		} catch (AfspraakMakenException ame) {
			ame.printStackTrace();
			fail("geen goede datum ingevuld!");
		} catch (TijdvakException tve) {
			fail("geen goede datum ingevuld!");
		}
	}
	
	@Test(expected = TijdvakException.class)
	public void TestCase3_2FouteEindTijdIngevuld()
			throws AfspraakMakenException, TijdvakException {
		
		Calendar calEindTijd1 = Calendar.getInstance();
		calEindTijd1.setLenient(false);
		Date datum = null;
		String dateStr = "18:00:00";
		try {
			datum = toFormat.parse(dateStr);
		} catch (ParseException e) {
			fail();
		}
		calEindTijd1.setTime(datum);

		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calEindTijd1,
				calEindTijd, deKlant1, "apk keuring");
	}

	@Test(expected = TijdvakException.class)
	public void TestCase3_3GeenEindTijdIngevuld()
			throws AfspraakMakenException, TijdvakException {
		
		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
				null, deKlant1, "apk keuring");
	}

	@Test
	public void TestCase4_1GoedKlantObject() {
		try {
			hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
					calEindTijd, deKlant1, "apk keuring");
		} catch (AfspraakMakenException ame) {
			fail(ame.getMessage());
		} catch (TijdvakException tve) {
			fail(tve.getMessage());
		}
	}
	
	@Test(expected = AfspraakMakenException.class)
	public void TestCase4_2GeenKlantObject()
			throws AfspraakMakenException, TijdvakException {
		
		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
				calEindTijd, null, "apk keuring");
	}
	
	@Test
	public void TestCase5_1GoedSoortKlusIngevuld() {
		try {
			hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
					calEindTijd, deKlant1, "apk keuring");
		} catch (AfspraakMakenException ame) {
			fail(ame.getMessage());
		} catch (TijdvakException tve) {
			fail(tve.getMessage());
		}
	}
	
	@Test(expected = AfspraakMakenException.class)
	public void TestCase5_2FoutSoortKlusIngevoerd()
			throws AfspraakMakenException, TijdvakException {
		
		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
				calEindTijd, deKlant1, "fout soort klus");
	}
	
	@Test(expected = AfspraakMakenException.class)
	public void TestCase5_3GeenSoortKlusIngevoerd()
			throws AfspraakMakenException, TijdvakException {
		
		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
				calEindTijd, deKlant1, null);
	}
	
	@Test
	public void TestCase6_1GoedAfspraakGemaakt()
			throws AfspraakMakenException, TijdvakException {
		try {
		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
				calEindTijd, deKlant1, "apk keuring");
		} catch (AfspraakMakenException ame) {
			fail(ame.getMessage());
		} catch (TijdvakException tve) {
			fail(tve.getMessage());
		}
	}
		
	@Test(expected = AfspraakMakenException.class)
	public void TestCase6_2FoutAfsrpaakGemaakt()
			throws AfspraakMakenException, TijdvakException {
		
		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(null, null,
				null, null, null);
	}
	
	@Test(expected = AfspraakMakenException.class)
	public void TestCase6_3KanGeenAfspraakMakenOpDitTijdstip()
			throws AfspraakMakenException, TijdvakException {
		Calendar fouteTijd = Calendar.getInstance();
		String beginTijd = "08:00:00";
		Date begindate = null;
		try {
			begindate = toFormat.parse(beginTijd);
		} catch (ParseException e) {

		}
		fouteTijd.setTime(begindate);
		
		// er word van 9:0:0 tot 10:0:0 een tijdvak gemaakt
		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, calBeginTijd,
				calEindTijd, deKlant1, "apk keuring");
		
		// er word van 8:0:0 tot 11:0:0 een tijdvak gemaakt > kan niet omdat 9 als bezet is!
		hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calDatum, fouteTijd,
				calEindTijd, deKlant1, "onderhoudsbeurt");
	}
}
