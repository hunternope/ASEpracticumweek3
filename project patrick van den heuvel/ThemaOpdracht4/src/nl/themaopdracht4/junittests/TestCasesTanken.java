/*
 * Copyright Sven van Walderveen, 26 mei 2013 
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 27 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.junittests;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import nl.themaopdracht4.werkplaatsdomein.Betaling;
import nl.themaopdracht4.werkplaatsdomein.BetalingException;
import nl.themaopdracht4.werkplaatsdomein.Brandstof;
import nl.themaopdracht4.werkplaatsdomein.BrandstofException;
import nl.themaopdracht4.werkplaatsdomein.Pomp;


public class TestCasesTanken {
	static private Betaling b1;
	static private Brandstof br1;
	@BeforeClass
	public static void setup() {
		ArrayList<Brandstof> brandstofList = new ArrayList<Brandstof>();
		Brandstof p1 = new Brandstof(1,"Euro 95", 1.778);
		brandstofList.add(p1);
		br1 = p1;
		Pomp p = Pomp.getInstance();
		p.setbrandstofen(brandstofList);
		Betaling beta = new Betaling(1, "Verkoop aan Scooter");
		b1 = beta;
	}
	
	
	@Test(expected = BrandstofException.class)
	//Exceptie verwacht omdat er zowieso een aantal liters > 0 ingevuld moet zijn.
	public void testAddBrandstofToBetalingNulLiter() throws BrandstofException {
		int aantalLitersBrandstof = 0;
		b1.addBrandstofToBetaling(br1, aantalLitersBrandstof);
	}
	
	@Test(expected = BrandstofException.class)
	//je moet geen foutmelding krijgen aangezien er geen brandstof geselecteerd is en hij een foutmelding verwacht
	public void testAddBrandstofToBetalingGeenBrandstof() throws BrandstofException {
		int aantalLitersBrandstof = 5;
		b1.addBrandstofToBetaling(null, aantalLitersBrandstof);
	}
	
	@Test(expected = BetalingException.class)
	//Exceptie verwacht omdat er geen betalingsregels zijn
	public void testGetBetalingTotaalZonderRegels() throws BetalingException {
		b1.getBetalingTotaal();
	}
	
	@Test(expected = BetalingException.class)
	//Exceptie verwacht omdat er geen betalingsregels zijn
	public void testGetBetalingBTWZonderRegels() throws BetalingException{
		b1.getBtwTotaal();
	}
	
}
