/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
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

import nl.themaopdracht4.werkplaatsdomein.Auto;
import nl.themaopdracht4.werkplaatsdomein.AutoException;
import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.Klant;
import nl.themaopdracht4.werkplaatsdomein.KlantenPas;
import nl.themaopdracht4.werkplaatsdomein.Monteur;
import nl.themaopdracht4.werkplaatsdomein.RegistrerenException;
import nl.themaopdracht4.werkplaatsdomein.User;
import nl.themaopdracht4.werkplaatsdomein.UserException;

public class RegistrerenServlet extends HttpServlet {

	private static final long serialVersionUID = 561404012257214671L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		boolean registerSuccess = true;
		String gebruikersnaam = req.getParameter("username");
		String pass1 = req.getParameter("password1");
		String pass2 = req.getParameter("password2");
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String address = req.getParameter("address");
		String geslacht = req.getParameter("geslacht");
		String geboortedatum = req.getParameter("geb_datum");
		String woonplaats = req.getParameter("woonplaats");
		String achternaam = req.getParameter("achternaam");
		// auto
		String autonaam = "";
		String merk = "";
		String kenteken = "";
		String brandstoftype = "";

		String typeUserObject = req.getParameter("usertype");
		boolean isKlant = false;
		boolean isMonteur = false;

		// -- begin typeUser controleren
		if ("klant".equals(typeUserObject)) {
			autonaam = req.getParameter("autonaam");
			merk = req.getParameter("merk");
			kenteken = req.getParameter("kenteken");
			brandstoftype = req.getParameter("brandstoftype");
			registerSuccess = true;
			isKlant = true;
		} else if ("monteur".equals(typeUserObject)) {
			registerSuccess = true;
			isMonteur = true;
		} else {
			req.setAttribute("msgs",
					"Fout met het type user opgegven!, geef dit door aan de makers!");
			registerSuccess = false;
		}
		// --

		// auto ui-checks
		if (isKlant) {
			if (!(autonaam.trim().length() > 0)) {
				req.setAttribute("aerror", "Autonaam is niet ingevuld!");
				registerSuccess = false;
			}

			if (!(merk.trim().length() > 0)) {
				req.setAttribute("aerror1", "Automerk is niet ingevuld!");
				registerSuccess = false;
			}

			if (!(kenteken.trim().length() > 0)) {
				req.setAttribute("aerror2", "Kenteken is niet ingevuld!");
				registerSuccess = false;
			}

			if (!(brandstoftype.trim().length() > 0)) {
				req.setAttribute("aerror3", "Brandstoftype is niet ingevuld!");
				registerSuccess = false;
			}
		}
		// user ui-checks

		if (!(gebruikersnaam.trim().length() > 0)) {
			req.setAttribute("rerror", "Gebruikersnaam is niet ingevuld!");
			registerSuccess = false;
		}

		if (!(pass1.trim().length() > 0) || !(pass2.trim().length() > 0)) {
			req.setAttribute("rerror1", "Wachtwoord is niet ingevuld!");
			registerSuccess = false;
		}

		if (!(pass1.equals(pass2))) {
			req.setAttribute("rerror2", "Wachtwoorden komen niet overeen!");
			registerSuccess = false;
		}
		if (!(email1.trim().length() > 0) || !(email2.trim().length() > 0)) {
			req.setAttribute("rerror3", "Email is niet ingevuld!");
			registerSuccess = false;
		}
		if (!(email1.equals(email2))) {
			req.setAttribute("rerror4", "Emails komen niet overeen!");
			registerSuccess = false;
		}
		if (!(address.trim().length() > 0)) {
			req.setAttribute("rerror5", "Address is niet ingevuld!");
			registerSuccess = false;
		}

		boolean geslachtCheck = false;
		if (!(geslacht.trim().length() > 0)) {
			req.setAttribute("rerror6", "Geboortedatum is niet ingevuld!");
			registerSuccess = false;
			geslachtCheck = true;
		}

		if (!("man".equals(geslacht)) && !geslachtCheck) {
			if ((!"vrouw".equals(geslacht))) {
				req.setAttribute("rerror6", "Geslacht is niet correct!");
				registerSuccess = false;
			}
		}

		if (!(geboortedatum.trim().length() > 0)) {
			req.setAttribute("rerror7", "Geslacht is niet correct!");
			registerSuccess = false;
		}

		if (!(achternaam.trim().length() > 0)) {
			req.setAttribute("rerror8", "Achternaam is niet ingevuld!");
			registerSuccess = false;
		}
		if (!(achternaam.trim().length() > 0)) {
			req.setAttribute("rerror9", "Woonplaats is niet ingevuld!");
			registerSuccess = false;
		}

		// na alle servlet checks

		if (registerSuccess) {
			ServletContext sc = getServletContext();
			Bedrijf hetBedrijf = (Bedrijf) sc.getAttribute("hetBedrijf");
			User binnekomendeUser = new User(gebruikersnaam, pass1, achternaam,
					address, woonplaats, geboortedatum, geslacht, email1);

			boolean heeftUser = false;
			try {
				heeftUser = hetBedrijf.heefUser(binnekomendeUser);

			} catch (RegistrerenException rex) {
				heeftUser = true;
				registerSuccess = false;
				req.setAttribute("msgs", rex.getMessage().toString());
			}

			if (!heeftUser) {
				try {
					hetBedrijf.controleerAlleUserInfo(binnekomendeUser);
				} catch (UserException ue) {
					heeftUser = true;
					registerSuccess = false;
					req.setAttribute("msgs", ue.getMessage().toString());
				}
			}

			if (!heeftUser && registerSuccess) {

				try {
					if ("klant".equals(typeUserObject)) {
						Klant nieuweKlant = new Klant(gebruikersnaam, pass1,
								achternaam, address, woonplaats, geboortedatum,
								geslacht, email1);
						Auto nieuweAuto = new Auto(autonaam, merk, kenteken,
								brandstoftype);
									
							// klantpassword = klant password > natuurlijk bij echte implmentatie een gegenereerde password
							KlantenPas deKlantenPas = new KlantenPas(hetBedrijf.geefNieuwePasNummer(), nieuweKlant.getPassword());
							
							nieuweKlant.setKlantenPas(deKlantenPas);
							nieuweKlant.setAuto(nieuweAuto);
							
							// daadwerkelijke registratie van de klant
							hetBedrijf.UserRegistreren(nieuweKlant);
														
							rd = req.getRequestDispatcher("/web/loginpage.jsp");
							req.setAttribute("msgs", "Succesvol geregistreerd!");
							rd.forward(req, resp);
						}
					

					if ("monteur".equals(typeUserObject)) {
						Monteur nieuweMonteur = new Monteur(gebruikersnaam,
								pass1, achternaam, address, woonplaats,
								geboortedatum, geslacht, email1);
						hetBedrijf.UserRegistreren(nieuweMonteur);

						System.out.println("Monteur ("
								+ nieuweMonteur.getGebruikersNaam()
								+ " is succesvol toegevoegd!");
						rd = req.getRequestDispatcher("/web/registermonteur.jsp");
						req.setAttribute("msgs", "Succesvol geregistreerd!");
						rd.forward(req, resp);
					}

				} catch (UserException ue) {
					registerSuccess = false;
					req.setAttribute("msgs", ue.getMessage().toString());
				}

				catch (RegistrerenException re) {
					registerSuccess = false;
					req.setAttribute("msgs", re.getMessage().toString());
				}

				catch (AutoException ae) {
					registerSuccess = false;
					req.setAttribute("msgs1", ae.getMessage().toString());
				}

			}
		}
		if (!registerSuccess && isKlant) {
			rd = req.getRequestDispatcher("/web/registerklant.jsp");
			rd.forward(req, resp);
		} else if (!registerSuccess && isMonteur) {
			rd = req.getRequestDispatcher("/web/registermonteur.jsp");
			rd.forward(req, resp);
		}
	}
}