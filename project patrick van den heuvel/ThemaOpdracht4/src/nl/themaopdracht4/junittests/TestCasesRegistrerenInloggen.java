/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 24 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.junittests;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import nl.themaopdracht4.werkplaatsdomein.AutoException;
import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.Klant;
import nl.themaopdracht4.werkplaatsdomein.Monteur;
import nl.themaopdracht4.werkplaatsdomein.RegistrerenException;
import nl.themaopdracht4.werkplaatsdomein.User;
import nl.themaopdracht4.werkplaatsdomein.UserException;

public class TestCasesRegistrerenInloggen {

	private static Bedrijf hetBedrijf;
	private static Klant deKlant;
	private static Monteur deMonteur;

	@BeforeClass
	public static void setup() {

		Bedrijf b = new Bedrijf("Gemaakt", "Door", "Joris Rijkes");
		Klant k = new Klant("MrSven1", "Hogeschool1", "Sven", "Neijnoord1",
				"Utrecht", "14-07-1994", "man", "Sven@gmail.com");
		Monteur m = new Monteur("Tester1", "Tester2", "aTestera", "testscript",
				"testscript", "10-10-1990", "man", "tester1@gmail.com");

		hetBedrijf = b;
		deKlant = k;
		deMonteur = m;
		try {
			hetBedrijf.UserRegistreren(deKlant);
			hetBedrijf.UserRegistreren(deMonteur);
		} catch (UserException ex) {
			System.out.println("klopt niet!");
		} catch (RegistrerenException e) {
			System.out.println("klopt niet!");
		} catch (AutoException e) {
			fail("geen gelidig adress!");
		}
	}

	// EERSTE 5 TESTCASES ZIJN VOOR HET INLOGGEN

	@Test(expected = UserException.class)
	public void testCase1VerkeerdeCombinatieUsernamePassword()
			throws UserException {
		User fouteUserGegevens = new Klant("MrSven1", "1schoolHoge", "", "", "",
				"", "", "");
		hetBedrijf.UserInloggen(fouteUserGegevens);
	}

	@Test
	public void testCase1GoedeCombinatieUsernamePassword() {
		try {
			User fouteUserGegevens = new Klant("MrSven1", "Hogeschool1", "", "",
					"", "", "", "");
			hetBedrijf.UserInloggen(fouteUserGegevens);
		} catch (UserException ux) {
			fail("geen geldige inlog gegevens!");
		}
	}

	@Test(expected = UserException.class)
	public void testCase2UsernameOfWachtwoordNietInvullen()
			throws UserException {
		User fouteUserGegevens = new Klant("MrSven1", "", "", "", "", "", "", "");
		hetBedrijf.UserInloggen(fouteUserGegevens);
	}

	@Test(expected = UserException.class)
	public void testCase2UsernameEnWachtwoordNietInvullen()
			throws UserException {
		User fouteUserGegevens = new Klant("", "", "", "", "", "", "", "");
		hetBedrijf.UserInloggen(fouteUserGegevens);
	}

	@Test(expected = UserException.class)
	public void testCase3FoutUsernameMetSpatie() throws UserException {
		User fouteUserGegevens = new Klant("Mr Sven1", "hogeschool1", "", "",
				"", "", "", "");
		hetBedrijf.UserInloggen(fouteUserGegevens);
	}

	@Test(expected = UserException.class)
	public void testCase4FoutUsernameMetSpecialTekens() throws UserException {
		User fouteUserGegevens = new Klant("Mr!@Sven1", "hogeschool1", "", "",
				"", "", "", "");
		hetBedrijf.UserInloggen(fouteUserGegevens);
	}

	@Test(expected = UserException.class)
	public void testCase5FoutUsernameTeKorteGegevens() throws UserException {
		User fouteUserGegevens = new Klant("MrS", "hog", "", "", "", "", "", "");
		hetBedrijf.UserInloggen(fouteUserGegevens);
	}

	// EERSTE 5 TESTCASES ZIJN VOOR HET REGISTREREN

	@Test(expected = UserException.class)
	public void testCast6FoutAdresVeldLengte2Registreren() throws UserException, RegistrerenException {
		// het 4de veld van de constructor is het address!
		User fouteUserGegevens = new Klant("MrSven2", "Hogeschool1", "Sven",
				"Ne", "Utrecht", "14-07-1994", "man", "Sven@gmail.com");
		try {
		hetBedrijf.UserRegistreren(fouteUserGegevens);
		} catch (AutoException e) {
			fail("geen gelidig adress!");
		}
		}

	@Test(expected = UserException.class)
	public void testCast6FoutAdresVeldLeegRegistreren() throws UserException, RegistrerenException {
		// het 4de veld van de constructor is het address!
		User fouteUserGegevens = new Klant("MrSven3", "Hogeschool1", "Sven", "",
				"Utrecht", "14-07-1994", "man", "Sven@gmail.com");
		try {
		hetBedrijf.UserRegistreren(fouteUserGegevens);
		} catch (AutoException e) {
			fail("geen gelidig adress!");
		}
	}

	@Test
	public void testCast6GoedAddressVeldRegistreren() throws UserException, RegistrerenException {
		// het 4de veld van de constructor is het address!
		try {
			User goedeUserGegevens = new User("MrSven4", "Hogeschool1", "Sven",
					"Neijnoord1", "Utrecht", "14-07-1994", "man",
					"Sven@gmail.com");
			hetBedrijf.UserRegistreren(goedeUserGegevens);
		} catch (UserException ex) {
			fail("geen gelidig adress!");
		} catch (AutoException e) {
			fail("geen gelidig adress!");
		}
	}

	@Test(expected = UserException.class)
	public void testCast7GeenNaamIngevuldRegistreren() throws UserException, RegistrerenException {

		User fouteUserGegevens = new Klant("MrSven5", "Hogeschool1", "",
				"Neijnoord1", "Utrecht", "14-07-1994", "man", "Sven@gmail.com");
		try {
		hetBedrijf.UserRegistreren(fouteUserGegevens);
	} catch (AutoException e) {
		fail("geen gelidig adress!");
	}
	}

	@Test(expected = UserException.class)
	public void testCast8GeenGeldigeGeslachtIngevuldRegistreren()
			throws UserException, RegistrerenException {
		// het 7de veld van de constructor is het geslacht!
		User a1 = new Klant("MrSven6", "Hogeschool1", "Sven",
				"Neijnoord1", "Utrecht", "14-07-1994", "", "Sven@gmail.com");
		try {
		hetBedrijf.UserRegistreren(a1);
		} catch (AutoException e) {
			fail("geen gelidig adress!");
		}
	}

	@Test
	public void testCast8GeldigeGeslachtIngevuldRegistreren()
			throws UserException, RegistrerenException {
		// het 7de veld van de constructor is het geslacht!
		try {
			User goedeUserGegevens = new User("MrSven7", "Hogeschool1", "Sven",
					"Neijnoord1", "Utrecht", "14-07-1994", "man",
					"Sven@gmail.com");
			hetBedrijf.UserRegistreren(goedeUserGegevens);
		} catch (UserException ex) {
			fail("geen geldig geslacht!");
		}
			 catch (AutoException e) {
				fail("geen gelidig adress!");
			}
	}

	public void testCast9ValideEmailAddressIngevuldRegistreren()
			throws UserException, RegistrerenException {
		// het 8de veld van de constructor is het email!
		try {
			User goedeUserGegevens = new Klant("MrSven8", "Hogeschool1", "Sven",
					"Neijnoord1", "Utrecht", "14-07-1994", "man",
					"Sven@gmail.com");
			hetBedrijf.UserRegistreren(goedeUserGegevens);
		} catch (UserException ex) {
			fail("geen valide email address ingevuld!");
		}
			 catch (AutoException e) {
				fail("geen gelidig adress!");
			}
	}

	@Test(expected = UserException.class)
	public void testCast9GeenValideEmailAddressIngevuldRegistreren()
			throws UserException, RegistrerenException {
		// het 8de veld van de constructor is het email!
		User fouteUserGegevens = new Klant("MrSven1", "Hogeschool1", "Sven",
				"Neijnoord1", "Utrecht", "14-07-1994", "vrouw", "fout@123.apg");
		try {
		hetBedrijf.UserRegistreren(fouteUserGegevens);
		} catch (AutoException e) {
			fail("geen gelidig adress!");
		}
		}

	@Test
	public void testCast10ValideGeboorteDatumRegistreren() throws UserException, RegistrerenException {
		// het 6de veld van de constructor is het geboortedatum!
		try {
			User goedeUserGegevens = new User("MrSven9", "Hogeschool1", "Sven",
					"Neijnoord1", "Utrecht", "14-07-1994", "man",
					"Sven@gmail.com");
			hetBedrijf.UserRegistreren(goedeUserGegevens);
		} catch (UserException ex) {
			fail("geen valide email address ingevuld!");
		}
	 catch (AutoException e) {
		fail("geen gelidig adress!");
	}
	}
	@Test (expected = UserException.class)
	public void testCast10InValideGeboorteDatumRegistreren()
			throws UserException, RegistrerenException {
		// het 6e veld van de constructor is het geboortedatum!
		User FouteUserGegevens = new Klant("MrSven9", "Hogeschool1", "Sven",
				"Neijnoord1", "Utrecht", "1994-01-01", "man", "Sven@gmail.com");
		try {
		hetBedrijf.UserRegistreren(FouteUserGegevens);
		} catch (AutoException e) {
			fail("geen gelidig adress!");
		}
	}

}