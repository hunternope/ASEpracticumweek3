/*
 * Developer : Haydar Berkan Yilmaz (haydar.yilmaz@gmail.com)
 * Date : 15/07/1990 :D
 * All code (c)2013 Haydar Berkan Yilmaz inc. all rights reserved
 */
package nl.themaopdracht4.servlets;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.themaopdracht4.werkplaatsdomein.Klus;
import nl.themaopdracht4.werkplaatsdomein.KlusAfvinkException;
import nl.themaopdracht4.werkplaatsdomein.Monteur;
import nl.themaopdracht4.werkplaatsdomein.Tijdvak;

public class KlusAfvinkenServlet extends HttpServlet implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 7892845164604594455L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String werkzaamheden = req.getParameter("werkzaamheden");
		
		String manuur = req.getParameter("manuren");
		
		String status = req.getParameter("status");
		
		String selectedValue = req.getParameter("selectedTijdvak");
		
		  int getalmanuur= 0;
		  try {
		    getalmanuur = Integer.parseInt(manuur);
		  } catch (Exception pe) {
		   getalmanuur = 0;
		   
		  }

		Object u = req.getSession().getAttribute("userObject");
		boolean magDoor = true;
		Monteur deMonteur = (Monteur) u;
		ArrayList<Tijdvak> deTijdvakken = deMonteur.getAlleTijdvakken();
		for (Tijdvak k : deTijdvakken) {
			Klus l = k.getKlus();
			if (l.getKlusCode().equals(selectedValue)) {
				// all deze try catch achter elkaar gaat niet werken > als helft
				// niet goed is zoals manuur > maar status is wel goed > wijzigd
				// hij toch de klus met nieuwe status, terwijl niet alle input
				// waardes kloppen!
				try {
					l.setStatus(status);
				} catch (KlusAfvinkException e1) {
					magDoor = false;
					req.setAttribute("msgs", e1.getMessage());
				}
				
				try {
					l.setManuren(getalmanuur);
				} catch (KlusAfvinkException e) {
					magDoor = false;
					req.setAttribute("msgs", e.getMessage());
				}
				try {
					l.setWerkzaamheden(werkzaamheden);
				} catch (KlusAfvinkException e) {
					req.setAttribute("msgs", e.getMessage());
					magDoor = false;
				}
			}

		}
		if (magDoor) {

			rd = req.getRequestDispatcher("/web/secure/klusoverzicht.jsp");
			rd.forward(req, resp);

		} else {
			rd = req.getRequestDispatcher("/web/secure/medewerker/klusaanpassen.jsp");
			rd.forward(req, resp);
		}
	}
}
