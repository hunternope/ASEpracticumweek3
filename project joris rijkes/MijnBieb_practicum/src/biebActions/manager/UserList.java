package biebActions.manager;

import java.util.ArrayList;
import java.util.List;

import biebAware.UserAware;
import biebDomain.User;
import biebservice.IBiebService;
import biebservice.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserList extends ActionSupport implements UserAware {

	private IBiebService ibs = ServiceProvider.getBiebService();
	private List<User> allUsers = new ArrayList<User>();
	private User user;

	public String execute() {
		allUsers = ibs.getUsers();

		return ActionSupport.SUCCESS;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}