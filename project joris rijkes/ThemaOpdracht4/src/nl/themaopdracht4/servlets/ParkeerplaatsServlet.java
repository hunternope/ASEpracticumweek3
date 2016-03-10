
package nl.themaopdracht4.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.Klant;
import nl.themaopdracht4.werkplaatsdomein.ReserveringException;

public class ParkeerplaatsServlet extends HttpServlet {

	private static final long serialVersionUID = 561404012257214671L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		ServletContext sct = req.getSession().getServletContext();
		Object o = sct.getAttribute("hetBedrijf");
		Bedrijf hetBedrijf = (Bedrijf)o;

		
		// Toegevoegd
		Object loggedUser = req.getSession().getAttribute("userObject");
		Klant deKlant = (Klant)loggedUser;
				
		String datum = req.getParameter("datum");
		
		
		try {
			if (hetBedrijf.checkDatumGoed(datum) == true && hetBedrijf.checkInputDatum(datum)== true)  {
				
				
				hetBedrijf.voegReserveringToeAutomatisch(datum, deKlant.getUsername());
				req.setAttribute("melding1","Reservering is geplaatst!");
				String reserveringen = hetBedrijf.getAlleReserveringen();

				
				req.getSession().setAttribute("reserveringen", reserveringen);
				RequestDispatcher rd = null;
				
				rd = req.getRequestDispatcher("/web/secure/ParkeerplaatsOverzicht.jsp");
				rd.forward(req, resp);
						
			
		}} catch (ReserveringException e) {
	
			req.setAttribute("erorr1", e.getMessage());
			RequestDispatcher rd = null;
			rd = req.getRequestDispatcher("/web/secure/klant/ParkeerplaatsReserveren.jsp");
			rd.forward(req, resp);
		}
		

	}
}
