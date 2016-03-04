/*
 * Developer : Haydar Berkan Yilmaz (haydar.yilmaz@gmail.com)
 * Date : 15/07/1990 :D
 * All code (c)2013 Haydar Berkan Yilmaz inc. all rights reserved
 */
package nl.themaopdracht4.servlets;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.themaopdracht4.werkplaatsdomein.Auto;
import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.Klant;
import nl.themaopdracht4.werkplaatsdomein.Klus;
import nl.themaopdracht4.werkplaatsdomein.Tijdvak;

public class FactuurServlet extends HttpServlet implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 7892845164604594455L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String selectedValue = req.getParameter("selectedKlus");
		
		ServletContext sct = req.getSession().getServletContext();
		Bedrijf hetBedrijf = (Bedrijf) sct.getAttribute("hetBedrijf");
		
		ArrayList<Tijdvak> deTijdvakken = hetBedrijf.getalleBeheerdersTijdvakken();
		for (Tijdvak k : deTijdvakken) {
			Klus l = k.getKlus();
			if (l != null) {
				if (l.getKlusCode().equals(selectedValue)) {
					Auto auto = l.getAuto();
					Klant kl = hetBedrijf.zoekKlant(auto.getKenteken());

					Properties props = new Properties();
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.port", 465);
					props.put("mail.smtp.ssl.enable", true);
					Session mailSession = Session.getInstance(props);

					try {
						MimeMessage msg = new MimeMessage(mailSession);
						msg.setFrom(new InternetAddress("atdhaydar@gmail.com",
								"ATD Facturatie"));
						msg.setRecipients(Message.RecipientType.TO,
								kl.getEmail());
						msg.setSubject("Factuur");
						msg.setSentDate(Calendar.getInstance().getTime());
						msg.setText("Beste klant :" + " " + kl.getUsername()
								+ "" + "uw gegevens : " + kl.getAdres()
								+ "De auto : " + kl.getAuto() + " "
								+ "\nWoonplaats :" + kl.getWoonplaats()
								+ "Totale rekening :" + l.getManuur() * 40 *1.21
								+ " inclusief 21% btw :");
						Transport.send(msg, "atdhaydar@gmail.com", "Haydar100");
						req.setAttribute("msgs", "Succesvol factuur gemaakt en verstuurd!");
					} catch (Exception e) {
						req.setAttribute("msgs", "Er is een fout opgetreden tijdens het email sturen!");
					}
					break;
				}

			}

		}

		rd = req.getRequestDispatcher("/web/secure/beheerder/Factuur.jsp");
		rd.forward(req, resp);

	}
}
