package biebActions.member;

import biebAware.UserAware;
import biebDomain.User;
import biebservice.IBiebService;
import biebservice.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ChangePassword extends ActionSupport implements UserAware {

	private static final String NEW_PASSWORD = "newPassword";
	private static final String OLD_PASSWORD = "oldPassword";
	private IBiebService ibs = ServiceProvider.getBiebService();
	private String oldPassword;
	private String newPassword;
	private User user;

	public String execute() {
		ibs.changePassword(user.getUsername(), newPassword);
		return SUCCESS;
	}

	public void validate() {


		if (!oldPassword.equals(user.getPassword())) {
			addFieldError(OLD_PASSWORD,
					"Huidig wachtwoord niet correct ingevuld");
		}
		
		// lengte check
		// -----------------------------------------------------------------
		if (newPassword.length() == 0) {
			addFieldError(NEW_PASSWORD, "Wachtwoord is verplicht");
		} else if(newPassword.matches(".{0,7}|")){
			addFieldError(NEW_PASSWORD,
					"Wachtwoord moet minimaal 8 tekens lang zijn");
		}
		// hoofdletter/kleine letter check
		// ----------------------------------------------
		if (!newPassword.matches(".*[A-Z]+.*")) {
			addFieldError(NEW_PASSWORD,
					"Wachtwoord moet minstens een hoofdletter bevatten");
		} else if (!newPassword.matches(".*[a-z]+.*")) {
			addFieldError(NEW_PASSWORD,
					"Wachtwoord moet minstens een kleine letter bevatten");
		}
		// cijfer / spacies check
		// -------------------------------------------------------
		
		if (!newPassword.matches(".*\\d+.*")) {
			addFieldError(NEW_PASSWORD,
					"Wachtwoord moet minstens een cijfer bevatten");
		}
		if (newPassword.matches(".*\\s+.*")) {
			addFieldError(NEW_PASSWORD, "Wachtwoord mag geen spatie bevatten");
		}
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
