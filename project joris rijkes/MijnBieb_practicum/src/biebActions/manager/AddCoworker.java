package biebActions.manager;

import com.opensymphony.xwork2.ActionSupport;

import biebservice.IBiebService;
import biebservice.ServiceProvider;

@SuppressWarnings("serial")
public class AddCoworker extends ActionSupport {
	
	private IBiebService ibs = ServiceProvider.getBiebService();
	private String username;
	private String password;

	public String execute(){
		ibs.addCoworker(username,password);
		return SUCCESS;
	}

	public void validate(){
		username = username.trim();
		password = password.trim();
		
		if (username.length() == 0 ){			
			addFieldError( "username", "naam is verplicht");
		}
		else if ( ibs.userExists(username ) ){		
			addFieldError("username", "gebruiker bestaat al");
		}
		
		if ( password.length() == 0 ){			
			addFieldError( "password", "wachtwoord is verplicht");
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
//	public String getPassword() {
//		return password;
//	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
