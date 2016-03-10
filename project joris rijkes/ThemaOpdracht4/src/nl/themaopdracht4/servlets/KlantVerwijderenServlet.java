package nl.themaopdracht4.servlets;
	
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.UserException;
	
	public class KlantVerwijderenServlet extends HttpServlet implements Serializable {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 7892845164604594455L;
	
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			RequestDispatcher rd = null;
		
			String selectedValue = req.getParameter("selectedKlant");
			ServletContext sc = getServletContext();
			Bedrijf hetBedrijf = (Bedrijf) sc.getAttribute("hetBedrijf");
			boolean komtVoor = hetBedrijf.heeftKlant(selectedValue);
			
			if(komtVoor) {
				try {
					hetBedrijf.verwijderKlant(selectedValue);
				} catch (UserException e) {
					req.setAttribute("msgs", e.getMessage());
				}
				rd = req.getRequestDispatcher("/web/secure/beheerder/klantverwijderen.jsp");
				req.setAttribute("msgs", "Klant succesvol verwijderd");	
				rd.forward(req, resp);
			}
			else {
				rd = req.getRequestDispatcher("/web/secure/beheerder/klantverwijderen.jsp");
				req.setAttribute("msgs", "Klant niet verwijderd");	
				rd.forward(req, resp);
			}
			
	
		} 
	}
