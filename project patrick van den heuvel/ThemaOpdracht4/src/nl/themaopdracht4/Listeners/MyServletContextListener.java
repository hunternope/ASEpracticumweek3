package nl.themaopdracht4.Listeners;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import nl.themaopdracht4.formatters.MyFormatter;
import nl.themaopdracht4.werkplaatsdomein.Auto;
import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.Beheerder;
import nl.themaopdracht4.werkplaatsdomein.Brandstof;
import nl.themaopdracht4.werkplaatsdomein.Klant;
import nl.themaopdracht4.werkplaatsdomein.KlantenPas;
import nl.themaopdracht4.werkplaatsdomein.Monteur;

/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 15/05/2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {

		Logger logger = Logger.getLogger("nl.themaopdracht4.servlets");
		try {
			FileHandler fh = new FileHandler(
					"Themaopdracht4-HttpSessionEvent.txt");
			logger.addHandler(fh);
			fh.setFormatter(new MyFormatter());

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		logger.setLevel(Level.ALL);
		logger.info("Logger initialized");

		try {
			FileInputStream fis = new FileInputStream(
					"themaopdracht4Bedrijf.obj");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Bedrijf hetBedrijf = (Bedrijf) ois.readObject();
			System.out.println(hetBedrijf);

			ServletContext sct = sce.getServletContext();
			sct.setAttribute("hetBedrijf", hetBedrijf);
			
			ois.close();
		} catch (Exception ioe) {
			Bedrijf hetBedrijf = new Bedrijf("AutoBedrijf ATD", "Utrecht",
					"2013");
			Beheerder deBeheerder = new Beheerder("Admin", "Admin", "Paladijn",
					"Admin", "Admin", "01-01-1990", "man", "AdminATD@gmail.com");
			Klant deKlant = new Klant("sven", "sven", "sven", "sven", "sven",
					"sven", "sven", "atdjorisrijkes@gmail.com");
			Auto deAuto = new Auto("SvensAuto", "Sven", "A1-A2-A3", "Diesel");
			Monteur deMonteur1 = new Monteur("Bart", "bart",
					"vanE", "deMonteur1", "deMonteur1", "deMonteur1",
					"deMonteur1", "atdjorisrijkes@gmail.com");
			KlantenPas deKlantenPas = new KlantenPas(
					hetBedrijf.geefNieuwePasNummer(), deKlant.getUsername());

			ServletContext sct = sce.getServletContext();
			deKlant.setAuto(deAuto);
			deKlant.setKlantenPas(deKlantenPas);
			hetBedrijf.setBeheerder(deBeheerder);
			hetBedrijf.voegKlantToe(deKlant);
			hetBedrijf.voegMedewerkerToe(deMonteur1);
			
			ArrayList<Brandstof> brandstofList = new ArrayList<Brandstof>();
			Brandstof p1 = new Brandstof(1,"Euro95", 1.778);
			Brandstof p2 = new Brandstof(2,"Super98", 1.844);
			Brandstof p3 = new Brandstof(3,"Diesel", 1.441);
			Brandstof p4 = new Brandstof(4,"LPG", 0.793);
			brandstofList.add(p1);
			brandstofList.add(p2);
			brandstofList.add(p3);
			brandstofList.add(p4);
			hetBedrijf.getPomp().setbrandstofen(brandstofList);
			
			sct.setAttribute("hetBedrijf", hetBedrijf);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {

		Object o = (Bedrijf) sce.getServletContext().getAttribute("hetBedrijf");
		Bedrijf b = (Bedrijf) o;

		try {
			FileOutputStream fos = new FileOutputStream(
					"themaopdracht4Bedrijf.obj");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(b);
			System.out.println(b);
			oos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
