package nl.themaopdracht4.seleniumtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestBase;

public class Testcasesinloggen extends SeleneseTestBase {
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox",
				"http://localhost:8080/ThemaOpdracht4/loginpage.jsp");
		selenium.start();
	}

	@Test
	public void testTestcase1_1inloggen() {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sven");
		selenium.type("name=password", "svne");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium
				.isTextPresent("Username and/or password are incorrect!"));
	}

	@Test
	public void testTestcase1_2inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sv");
		selenium.type("name=password", "sv");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium
				.isTextPresent("Username and/or password are incorrect!"));
	}

	@Test
	public void testTestcase2_1inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sv");
		selenium.type("name=password", "sv");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium
				.isTextPresent("Username and/or password are incorrect!"));
	}

	@Test
	public void testTestcase2_2inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sv");
		selenium.type("name=password", "sv");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium
				.isTextPresent("Username and/or password are incorrect!"));
	}

	@Test
	public void testTestcase3inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sv");
		selenium.type("name=password", "sv");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium
				.isTextPresent("Username and/or password are incorrect!"));
	}

	@Test
	public void testTestcase4inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sv");
		selenium.type("name=password", "sv");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium
				.isTextPresent("Username and/or password are incorrect!"));
	}

	@Test
	public void testTestcase5inloggen() throws Exception {
		selenium.open("/ThemaOpdracht4/loginpage.jsp");
		selenium.type("name=username", "sv");
		selenium.type("name=password", "sv");
		selenium.click("name=button");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium
				.isTextPresent("Username and/or password are incorrect!"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}