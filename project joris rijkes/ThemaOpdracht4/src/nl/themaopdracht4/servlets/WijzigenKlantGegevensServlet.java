/*
 * Developer : Haydar Berkan Yilmaz (haydar.yilmaz@gmail.com)
 * Date : 15/07/1990 :D
 * All code (c)2013 Haydar Berkan Yilmaz inc. all rights reserved
 */
package nl.themaopdracht4.servlets;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.themaopdracht4.werkplaatsdomein.User;

public class WijzigenKlantGegevensServlet extends HttpServlet implements
		Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -3816558269411044510L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String voornaam = req.getParameter("voornaam");
		String woonplaats = req.getParameter("woonplaats");
		String adres = req.getParameter("adres");
		String email = req.getParameter("email");
		String password = req.getParameter("password1");
		String password2 = req.getParameter("password2");

		// Userobject ophalen
		Object u = req.getSession().getAttribute("userObject");
		// Casten
		User deKlant = (User) u;
		// Gegevens setten die binnengekomen zijn via de JSP pagina
		if (deKlant.controleerNaam(voornaam)) {
			deKlant.setNaam(voornaam);
		} else {
		}
		if (deKlant.controleerWoonplaats(woonplaats)) { // dubbele checks
			deKlant.setWoonplaats(woonplaats);
		} else {
		}
		if (deKlant.controleerAdres(adres)) {
			deKlant.setAdres(adres);
		} else {
		}
		if (deKlant.controleerEmailAddress(email)) {
			deKlant.setEmail(email);
		} else {
		}
		if (deKlant.controleerWachtwoord(password)) {
			if (deKlant.controleerWachtwoord(password2)) {
				deKlant.setPassword(password);
			} else {
			}
		}

		rd = req.getRequestDispatcher("/web/secure/index-dynamic.jsp");
		rd.forward(req, resp);

	}
}
