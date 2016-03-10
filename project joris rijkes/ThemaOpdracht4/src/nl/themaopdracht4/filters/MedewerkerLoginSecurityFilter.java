/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 19 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import nl.themaopdracht4.werkplaatsdomein.Monteur;

public class MedewerkerLoginSecurityFilter implements Filter {
	
	public void init(FilterConfig arg0) {
		
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsr = (HttpServletRequest)req;
		Object o = hsr.getSession().getAttribute("userObject");
		if (!(o instanceof Monteur)) {
			hsr.getRequestDispatcher("/web/loginpage.jsp").forward(req, res);
		} else {
			chain.doFilter(req, res);
		}
		
	}
	
	public void destroy() {
		
	}

}
