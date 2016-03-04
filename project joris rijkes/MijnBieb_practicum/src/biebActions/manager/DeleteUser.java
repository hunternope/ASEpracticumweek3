package biebActions.manager;

import java.util.ArrayList;
import java.util.List;

import biebAware.UserAware;
import biebDomain.Book;
import biebDomain.BookStatus;
import biebDomain.User;
import biebservice.IBiebService;
import biebservice.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DeleteUser extends ActionSupport implements UserAware {

	private static final String USERNAME2 = "username";
	private IBiebService ibs = ServiceProvider.getBiebService();
	private String username;
	private List<User> allUsers = new ArrayList<User>();
	private User user;

	public String execute() {
		ibs.deleteUser(username);
		return SUCCESS;
	}

	public void validate() {
		allUsers = ibs.getUsers();

		if (!ibs.userExists(username)) {
			addFieldError(USERNAME2, "gebruiker bestaat niet");

		} else if (username.equals(user.getUsername())) {
			addFieldError(USERNAME2, "je mag jezelf niet verwijderen!");
		}

		List<Book> userbooks = ibs.getBooks(username);
		for (Book b : userbooks) {
			if (b.getStatus() == BookStatus.BORROWED) {
				addFieldError(USERNAME2,
						"Deze gebruiker heeft een boek geleend");

			}
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public User getUser() {

		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

}
