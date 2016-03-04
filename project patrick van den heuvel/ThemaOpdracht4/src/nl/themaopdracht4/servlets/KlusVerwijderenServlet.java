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
import nl.themaopdracht4.werkplaatsdomein.Monteur;
import nl.themaopdracht4.werkplaatsdomein.Tijdvak;

public class KlusVerwijderenServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855483246833422394L;

	/**
		 * 
		 */

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String selectedValue = req.getParameter("selectedTijdvak");
		 
		Object u = req.getSession().getAttribute("userObject");
		Monteur deMonteur = (Monteur) u;
		ArrayList<Tijdvak> deTijdvakken = deMonteur.getAlleTijdvakken();
			for (Tijdvak k : deTijdvakken) {
				Klus l = k.getKlus();
				if (l.getKlusCode().equals(selectedValue))
					try {
				deMonteur.verwijderTijdvak(k);
					} catch (Exception e) {
					
					}
				break;
				}
			
			rd = req.getRequestDispatcher("/web/secure/medewerker/Klusverwijderen.jsp");
			rd.forward(req, resp);
	}
}
	
