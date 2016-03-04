package biebActions.coworker;

import java.util.ArrayList;
import java.util.List;

import biebAware.UserAware;
import biebDomain.Book;
import biebDomain.User;
import biebservice.IBiebService;
import biebservice.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BookAndUserList extends ActionSupport implements UserAware {

	private IBiebService ibs = ServiceProvider.getBiebService();
	private List<User> users = new ArrayList<User>();
	private User user;
	private List<Book> books = new ArrayList<Book>();

	public String execute() {
		users = ibs.getMembers();
		books = ibs.getBooks();

		return ActionSupport.SUCCESS;
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Book> getBooks() {
		return books;
	}
}