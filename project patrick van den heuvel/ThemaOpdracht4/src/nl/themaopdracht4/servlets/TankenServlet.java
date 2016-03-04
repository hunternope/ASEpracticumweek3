/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 23 jun. 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.BrandstofException;
import nl.themaopdracht4.werkplaatsdomein.TankbeurtException;

public class TankenServlet extends HttpServlet {


	private static final long serialVersionUID = -2950987939275107063L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher rd = null;

		// Geen klant object mee geven, de klantenpas die gescanned word moet de
		// klant's pasnummer automatisch geven/invullen. De gebruiker vult zelf
		// zijn pascode in.
		String pasNummer = req.getParameter("pasnummer");
		String pasCode = req.getParameter("pascode");
		String benzineType = req.getParameter("benzinetype");

		// Simulatie tankbeurt aantalLiters
		int aantalLiters = 1 + (int) (100 * Math.random());

		ServletContext sct = req.getSession().getServletContext();
		Bedrijf hetBedrijf = (Bedrijf) sct.getAttribute("hetBedrijf");

		try {
			hetBedrijf.voegTankBeurtToe(pasCode, pasNummer, benzineType,
					aantalLiters);
			req.setAttribute("msgs", "Succesvol " + aantalLiters + " liters " + benzineType + " getanked!");
			req.setAttribute("liter", "" + aantalLiters);
		} catch (TankbeurtException tbe) {
			req.setAttribute("msgs", tbe.getMessage());
		} catch (BrandstofException bse) {
			req.setAttribute("msgs", bse.getMessage());
		}

		rd = req.getRequestDispatcher("/web/secure/tankbeurtinterface.jsp");
		rd.forward(req, resp);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doPost(req, resp);
	}
}
