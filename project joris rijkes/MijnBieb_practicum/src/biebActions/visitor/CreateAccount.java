package biebActions.visitor;

import com.opensymphony.xwork2.ActionSupport;

import biebservice.IBiebService;
import biebservice.ServiceProvider;

@SuppressWarnings("serial")
public class CreateAccount extends ActionSupport {
	
	private static final String USERNAME2 = "username";
	private static final String PASSWORD2 = "password";
	private IBiebService ibs = ServiceProvider.getBiebService();
	private String username;
	private String password;

	public String execute(){
		ibs.addMember(username,password);
		
		return SUCCESS;
	}

	public void validate(){
		username = username.trim();
		password = password.trim();
		
		if (username.length() == 0 ){			
			addFieldError( USERNAME2, "naam is verplicht");
		}
		else if ( ibs.userExists(username ) ){		
			addFieldError(USERNAME2, "gebruiker bestaat al");
		}
		
		// lengte check
		// -----------------------------------------------------------------
		if (password.length() == 0) {
			addFieldError(PASSWORD2, "Wachtwoord is verplicht");
		} else if(password.matches(".{0,7}|")){
			addFieldError(PASSWORD2,
					"Wachtwoord moet minimaal 8 tekens lang zijn");
		}
		// hoofdletter/kleine letter check
		// ----------------------------------------------
		if (!password.matches(".*[A-Z]+.*")) {
			addFieldError(PASSWORD2,
					"Wachtwoord moet minstens een hoofdletter bevatten");
		} else if (!password.matches(".*[a-z]+.*")) {
			addFieldError(PASSWORD2,
					"Wachtwoord moet minstens een kleine letter bevatten");
		}
		// cijfer / spacies check
		// -------------------------------------------------------
		
		if (!password.matches(".*\\d+.*")) {
			addFieldError(PASSWORD2,
					"Wachtwoord moet minstens een cijfer bevatten");
		}
		if (password.matches(".*\\s+.*")) {
			addFieldError(PASSWORD2, "Wachtwoord mag geen spatie bevatten");
		}
	}

	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
}
