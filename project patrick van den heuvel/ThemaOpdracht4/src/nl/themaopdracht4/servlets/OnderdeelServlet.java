//Ingo van Leeuwen Copyright
package nl.themaopdracht4.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.Onderdeel;

public class OnderdeelServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9096809472996651637L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ServletContext sct = req.getSession().getServletContext();
		Object o = sct.getAttribute("hetBedrijf");
		Bedrijf hetBedrijf = (Bedrijf)o;
		
		String artikelNummer = req.getParameter("artikelNummer");
		String artikelNaam	 = req.getParameter("Naam");
		String artikelAantal = req.getParameter("Aantal");
		
		if (artikelAantal != "" && artikelNaam != "" && artikelNummer != ""  && artikelNummer.length() > 4) {
			System.out.print("Aantal is Valide");
					int i;	
		i = Integer.parseInt(artikelAantal);
		
		
			if (hetBedrijf.checkOnderdeelGoed(artikelNummer,artikelNaam,i) == true)  {
				boolean b = false;
				 ArrayList<Onderdeel> alleOnderdelen = hetBedrijf.getAlleOnderdelen();
				 // code hieronder is ook nodig voor klus bijwerken , alleen moet het i= i-x; worden en er 
				 // moet een check in zodat hij niet lager als 0 kan worden.
				if(hetBedrijf.heeftOnderdeel(artikelNummer) == true){
					for (Onderdeel on : alleOnderdelen) {
						if (on.getArtikelNummer().equals(artikelNummer)) {
							int x = on.getAantal();
							if(x < 1){
								req.setAttribute("geenvoorraad", "Dit product is niet op voorraad!");
								b = true;
								break;
							}
							
							i= i+x;
							alleOnderdelen.remove(on);
							
							break;
					
			
						}//alleOnderdelen.remove(on);
					}
				}				
				if(b == false){
				Onderdeel nieuwOnderdeel = new Onderdeel(artikelNummer,artikelNaam,i);
				hetBedrijf.voegOnderdeelToe(nieuwOnderdeel);
				req.setAttribute("melding1","Onderdeel is toegevoegd!");
				}
				
				ArrayList<Onderdeel> deOnderdelen = hetBedrijf.getAlleOnderdelen();
				String onderdeel = "";
				if (deOnderdelen != null && !deOnderdelen.isEmpty()) {
					for (Onderdeel g : deOnderdelen) {
						onderdeel += g;
					}
				}
				
				req.getSession().setAttribute("Onderdeel", onderdeel);
				RequestDispatcher rd = null;
				
				rd = req.getRequestDispatcher("/web/secure/beheerder/OnderdelenBeheren.jsp");
				rd.forward(req, resp);

				
			}else{
				req.setAttribute("melding","Onderdeel gegevens zijn niet juist ingevuld!");
				RequestDispatcher rd = null;
				rd = req.getRequestDispatcher("/web/secure/beheerder/OnderdelenBeheren.jsp");
				rd.forward(req, resp);
			}
			}else {
				req.setAttribute("melding","Geen geldige gegevens ingevuld!");
				RequestDispatcher rd = null;
				rd = req.getRequestDispatcher("/web/secure/beheerder/OnderdelenBeheren.jsp");
				rd.forward(req, resp);
			
		}
		
	}

	}

