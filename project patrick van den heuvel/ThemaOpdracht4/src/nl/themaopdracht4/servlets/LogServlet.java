/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 20 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.servlets;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.themaopdracht4.werkplaatsdomein.Bedrijf;
import nl.themaopdracht4.werkplaatsdomein.User;
import nl.themaopdracht4.werkplaatsdomein.UserException;

public class LogServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7943985273228383529L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String name = req.getParameter("username");
		String pass = req.getParameter("password");
		RequestDispatcher rd = null;
		if (name.trim().length() > 0 || pass.trim().length() > 0) {

			User inloggendeUser = new User(name, pass);

			ServletContext sct = req.getSession().getServletContext();
			Bedrijf hetBedrijf = (Bedrijf) sct.getAttribute("hetBedrijf");

			try {
				//gooit exceptie zodra null word teruggegeven
				User GevondeUser = hetBedrijf.UserInloggen(inloggendeUser);
				
				req.getSession().setAttribute("userObject", GevondeUser);
				Cookie c = new Cookie("cUsername", name);
				resp.addCookie(c);
				rd = req.getRequestDispatcher("/web/secure/index-dynamic.jsp");
				rd.forward(req, resp);

			} catch (UserException ex) {
				req.setAttribute("msgs", ex.getMessage().toString());
				rd = req.getRequestDispatcher("/web/loginpage.jsp");
				rd.forward(req, resp);
			}

		} else {
			rd = req.getRequestDispatcher("/web/loginpage.jsp");
			req.setAttribute("msgs", "Username and/or password are incorrect!");
			rd.forward(req, resp);

		}
	}
}
