/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.servlets;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.themaopdracht4.werkplaatsdomein.AfspraakMakenException;
import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.Klant;
import nl.themaopdracht4.werkplaatsdomein.TijdvakException;

public class AfspraakMakenServlet extends HttpServlet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4176956573151814208L;

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String datum = req.getParameter("datum");
		String tijdGekozen = req.getParameter("selectBegin");
		String soortKlus = req.getParameter("selectTypeKlus");
		boolean magDoorChecks = true;
		String regex = "[0-1]{1}[0-9]{1}/[0-3]{1}[0-9]{1}/[0-9]{4}";
		if (datum == null || "".equals(datum.trim())) {
			magDoorChecks = false;
			req.setAttribute("msgs", "Geen datum ingevuld!");
		} else if (!datum.matches(regex)) {
			magDoorChecks = false;
			req.setAttribute("msgs", "Foute datum ingevuld (dd/mm/yyyy)");
		}

		if (magDoorChecks) {

			ServletContext sct = req.getSession().getServletContext();
			Object o = sct.getAttribute("hetBedrijf");
			Bedrijf hetBedrijf = (Bedrijf) o;

			boolean isTijdGekozen = true;
			if ("".equals(tijdGekozen) || tijdGekozen == null) {
				isTijdGekozen = false;
			}
			DateFormat afromFormat = new SimpleDateFormat("MM/dd/yyyy");
			afromFormat.setLenient(false);
			DateFormat atoFormat = new SimpleDateFormat("dd:MM:yyyy");
			atoFormat.setLenient(false);
			String dateStr = datum;
			java.util.Date adate = null;
			try {
				adate = afromFormat.parse(dateStr);
			} catch (ParseException e) {
				req.setAttribute("msgs",
						"Geen geldige datum ingevoerd! (dd/mm/yyyy)");
				isTijdGekozen = false;
			}

			Calendar calinput = Calendar.getInstance();
			calinput.setLenient(false);
			if (adate != null) {
				calinput.setTime(adate);

				// Servlet check of datum wel actueel genoeg is.
				Calendar huidigeDatum = Calendar.getInstance();
				huidigeDatum.setLenient(false);
				if (calinput.getTime().getYear() <= huidigeDatum.getTime()
						.getYear()
						&& calinput.getTime().getMonth() <= huidigeDatum
								.getTime().getMonth()
						&& calinput.getTime().getDate() < huidigeDatum
								.getTime().getDate()) {
					req.setAttribute("msgs",
							"De geselecteerde datum is voor de huidige datum!");
					magDoorChecks = false;
				}

			} else {
				req.setAttribute("msgs", "Geen datum ingevuld!");
				magDoorChecks = false;
			}
			if (!isTijdGekozen && magDoorChecks) {

				ArrayList<Date> alleDates = null;

				try {
					alleDates = (ArrayList<Date>) hetBedrijf
							.getAlleBeschikbareBedrijfTijden(calinput, new ArrayList<Date>());

					req.setAttribute("datearray", alleDates);
				} catch (AfspraakMakenException ame) {
					req.setAttribute("msgs", ame.getMessage().toString());
				}
			}

			if (isTijdGekozen && magDoorChecks) {

				isTijdGekozen = true;
				DateFormat toFormat = new SimpleDateFormat("kk:mm:ss");
				toFormat.setLenient(false);
				java.util.Date begindate = null;
				try {
					begindate = toFormat.parse(tijdGekozen);
				} catch (ParseException e) {
					req.setAttribute("msgs",
							"Geen geldige begin datum (kk:mm:ss");
				}

				int IntEindTijd = 0;
				if (soortKlus.equals("onderhoudsbeurt")) {
					IntEindTijd = 3;
				}
				if (soortKlus.equals("apk keuring")) {
					IntEindTijd = 1;
				}

				Calendar calbegin = Calendar.getInstance();
				calbegin.setLenient(false);
				calbegin.setTime(begindate);

				Calendar caleind = Calendar.getInstance();
				caleind.setLenient(false);
				Date eindDate = begindate;
				eindDate.setHours(begindate.getHours() + IntEindTijd);
				caleind.setTime(eindDate);

				// daadwerkelijke toevoegen van de afspraak

				Object refK = req.getSession().getAttribute("userObject");
				Klant deKlant = (Klant) refK;

				try {

					hetBedrijf.voegNieuweOnderhoudsAfspraakToe(calinput,
							calbegin, caleind, deKlant, soortKlus);
					req.setAttribute("msgs",
							"Afspraak is succesvol toegevoegd!");
				} catch (AfspraakMakenException ame) {
					req.setAttribute("msgs", ame.getMessage().toString());
				} catch (TijdvakException te) {
					req.setAttribute("msgs", te.getMessage().toString());
				}

			}

		}

		rd = req.getRequestDispatcher("/web/secure/klant/onderhoudsafspraakmaken.jsp");
		rd.forward(req, resp);

	}

}