package nl.themaopdracht4.servlets;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5501419912031299929L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher rd = null;
		

		req.getSession().removeAttribute("userObject");
		req.getSession().invalidate();
		req.setAttribute("msgs",
				"Succesvol uitgelogd!");
		rd = req.getRequestDispatcher("/web/loginpage.jsp");
		rd.forward(req, resp);
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doGet(req, resp);
	}

}
