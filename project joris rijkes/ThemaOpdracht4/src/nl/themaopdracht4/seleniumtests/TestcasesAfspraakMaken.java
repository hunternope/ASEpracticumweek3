/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 jun. 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.seleniumtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestBase;

public class TestcasesAfspraakMaken extends SeleneseTestBase {
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox",
				"http://localhost:8080/ThemaOpdracht4/loginpage.jsp");
		selenium.start();
	}
	
	@Test
	public void testcase1AfspraakHuidigeDatumTijdIsVoorDeHuidigeDatumTijd() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sven");
		selenium.type("name=password", "sven");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Onderhoudsafspraak maken");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=datepicker");
		selenium.click("link=20");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.select("name=selectBegin", "label=15:0:0");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("De geselecteerde tijd op deze datum is kleiner dan de huidige tijd op deze datum!"));
	}
	
	@Test
	public void testcase2AfspraakTijdsLengteDuurtTeLangVoorDitTijdstip() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "");
		selenium.type("name=username", "sven");
		selenium.type("name=password", "sven");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Onderhoudsafspraak maken");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=datepicker");
		selenium.click("link=20");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.select("name=selectBegin", "label=16:0:0");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Deze afspraaktype kan niet op dit tijdstip worden ingepland, probeer een ander tijdstip."));
	}
	
	@Test
	public void testcase3DatimIsVoorHuidigeDatum() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sven");
		selenium.type("name=password", "sven");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Onderhoudsafspraak maken");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=datepicker");
		selenium.click("link=19");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("De geselecteerde datum is voor de huidige datum!"));
	}
	
	@Test
	public void testcase4GeenBeschikbareTijdenOpDezeDatum() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "");
		selenium.type("name=username", "sven");
		selenium.type("name=password", "sven");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Onderhoudsafspraak maken");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=datepicker");
		selenium.click("link=21");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Er zijn geen beschikbare tijden op deze datum!, probeer een andere datum."));
	}
	
	@Test
	public void testcase5GeenGeldigeDatumIngevoerd() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sven");
		selenium.type("name=password", "sven");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Onderhoudsafspraak maken");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=datepicker");
		selenium.type("id=datepicker", "1111111");
		selenium.click("css=html");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Foute datum ingevuld (dd/mm/yyyy)"));
	}
	
	@Test
	public void testcase6NiksIngevuldBijDatum() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sven");
		selenium.type("name=password", "sven");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Onderhoudsafspraak maken");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Geen datum ingevuld!"));
	}
	
	@Test
	public void testcase7SuccesvolAfspraakMaken() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "");
		selenium.type("name=username", "sven");
		selenium.type("name=password", "sven");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Onderhoudsafspraak maken");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=datepicker");
		selenium.click("link=23");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=#menu > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Afspraak is succesvol toegevoegd!"));
	}

	
	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
