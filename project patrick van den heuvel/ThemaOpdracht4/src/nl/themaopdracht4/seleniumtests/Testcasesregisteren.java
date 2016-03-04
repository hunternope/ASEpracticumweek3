package nl.themaopdracht4.seleniumtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestBase;

public class Testcasesregisteren extends SeleneseTestBase {
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox",
				"http://localhost:8080/ThemaOpdracht4/loginpage.jsp");
		selenium.start();
	}

	@Test
	public void testTestcase6_1inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/Register.jsp");
		selenium.type("name=username", "Joris123");
		selenium.type("name=password1", "Joris123");
		selenium.type("name=password2", "Joris123");
		selenium.type("name=achternaam", "Amsterdam");
		selenium.type("name=address", "Amsterdam");
		selenium.type("name=woonplaats", "Amsterdam");
		selenium.type("name=geb_datum", "1994-01-01");
		selenium.type("name=geslacht", "man");
		selenium.type("name=email1", "svenvanwalderveen@gmail.com");
		selenium.type("name=email2", "svenvanwalderveen@gmail.com");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("DD-MM-YYYY"));
	}
	
	@Test
	public void testTestcase6_2inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/Register.jsp");
		selenium.type("name=username", "Joris123");
		selenium.type("name=password1", "Joris123");
		selenium.type("name=password2", "Joris123");
		selenium.type("name=achternaam", "Amsterdam");
		selenium.type("name=address", "Amsterdam");
		selenium.type("name=woonplaats", "Amsterdam");
		selenium.type("name=geb_datum", "1994-01-01");
		selenium.type("name=geslacht", "man");
		selenium.type("name=email1", "svenvanwalderveen@gmail.com");
		selenium.type("name=email2", "svenvanwalderveen@gmail.com");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("DD-MM-YYYY"));
	}
	
	@Test
	public void testTestcase6_3inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/Register.jsp");
		selenium.type("name=username", "Sven123");
		selenium.type("name=password1", "Sven123");
		selenium.type("name=password2", "Sven123");
		selenium.type("name=achternaam", "Walderveen");
		selenium.type("name=address", "deMeern");
		selenium.type("name=woonplaats", "Amsterdam");
		selenium.type("name=geb_datum", "14-07-1994");
		selenium.type("name=geslacht", "man");
		selenium.type("name=email1", "svenvanwalderveen@gmail.com");
		selenium.type("name=email2", "svenvanwalderveen@gmail.com");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Succesvol geregistreerd!"));
	}
	
	@Test
	public void testTestcase7inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/Register.jsp");
		selenium.type("name=username", "Sven123");
		selenium.type("name=password1", "Sven123");
		selenium.type("name=password2", "Sven123");
		selenium.type("name=address", "Amsterdam");
		selenium.type("name=woonplaats", "Amsterdam");
		selenium.type("name=geb_datum", "14-07-1994");
		selenium.type("name=geslacht", "man");
		selenium.type("name=email1", "svenvanwalderveen@gmail.com");
		selenium.type("name=email2", "svenvanwalderveen@gmail.com");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Achternaam is niet ingevuld!"));
	}
	
	@Test
	public void testTestcase8_1inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/Register.jsp");
		selenium.type("name=username", "Sven123");
		selenium.type("name=password1", "Sven123");
		selenium.type("name=password2", "Sven123");
		selenium.type("name=achternaam", "Walderveen");
		selenium.type("name=address", "Amsterdam");
		selenium.type("name=woonplaats", "Amsterdam");
		selenium.type("name=geb_datum", "14-07-1994");
		selenium.type("name=geslacht", "alien");
		selenium.type("name=email1", "svenvanwalderveen@gmail.com");
		selenium.type("name=email2", "svenvanwalderveen@gmail.com");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Geslacht is niet ingevuld!"));
	}
	
	@Test
	public void testTestcase8_2inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/Register.jsp");
		selenium.type("name=username", "Sven123");
		selenium.type("name=password1", "Sven123");
		selenium.type("name=password2", "Sven123");
		selenium.type("name=achternaam", "Walderveen");
		selenium.type("name=address", "Amsterdam");
		selenium.type("name=woonplaats", "Amsterdam");
		selenium.type("name=geb_datum", "14-07-1994");
		selenium.type("name=geslacht", "man");
		selenium.type("name=email1", "svenvanwalderveen@gmail.com");
		selenium.type("name=email2", "svenvanwalderveen@gmail.com");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Succesvol geregistreerd!"));
	}
	
	@Test
	public void testTestcase9_1inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/Register.jsp");
		selenium.type("name=username", "Sven123");
		selenium.type("name=password1", "Sven123");
		selenium.type("name=password2", "Sven123");
		selenium.type("name=achternaam", "Walderveen");
		selenium.type("name=address", "Amsterdam");
		selenium.type("name=woonplaats", "Amsterdam");
		selenium.type("name=geb_datum", "14-07-1994");
		selenium.type("name=geslacht", "man");
		selenium.type("name=email1", "svenvanwalderveen@gmail.com");
		selenium.type("name=email2", "svenvanwalderveen@gmail.com");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Succesvol geregistreerd!"));
	}
	
	@Test
	public void testTestcase9_2inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/Register.jsp");
		selenium.type("name=username", "Sven123");
		selenium.type("name=password1", "Sven123");
		selenium.type("name=password2", "Sven123");
		selenium.type("name=achternaam", "Walderveen");
		selenium.type("name=address", "Amsterdam");
		selenium.type("name=woonplaats", "Amsterdam");
		selenium.type("name=geb_datum", "14-07-1994");
		selenium.type("name=geslacht", "man");
		selenium.type("name=email1", "svenvanwalderveen@gmail.com");
		selenium.type("name=email2", "svenvanwalderveen@gmail.com");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Succesvol geregistreerd!"));
	}
	
	@Test
	public void testTestcase10_1inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/Register.jsp");
		selenium.type("name=username", "Sven123");
		selenium.type("name=password1", "Sven123");
		selenium.type("name=password2", "Sven123");
		selenium.type("name=achternaam", "Walderveen");
		selenium.type("name=address", "Amsterdam");
		selenium.type("name=woonplaats", "Amsterdam");
		selenium.type("name=geb_datum", "14-07-1994");
		selenium.type("name=geslacht", "man");
		selenium.type("name=email1", "svenvanwalderveen@gmail.com");
		selenium.type("name=email2", "svenvanwalderveen@gmail.com");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Succesvol geregistreerd!"));
	}
	
	@Test
	public void testTestcase10_2inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/Register.jsp");
		selenium.type("name=username", "Joris123");
		selenium.type("name=password1", "Joris123");
		selenium.type("name=password2", "Joris123");
		selenium.type("name=achternaam", "Amsterdam");
		selenium.type("name=address", "Amsterdam");
		selenium.type("name=woonplaats", "Amsterdam");
		selenium.type("name=geb_datum", "1994-01-01");
		selenium.type("name=geslacht", "man");
		selenium.type("name=email1", "svenvanwalderveen@gmail.com");
		selenium.type("name=email2", "svenvanwalderveen@gmail.com");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("DD-MM-YYYY"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}