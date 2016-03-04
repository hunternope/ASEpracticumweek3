package biebActions.member;

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
public class MyBookList extends ActionSupport implements UserAware {

	private IBiebService ibs = ServiceProvider.getBiebService();
	private List<Book> allbooks = new ArrayList<Book>();
	private List<Book> books = new ArrayList<Book>();
	private User user;

	public String execute() {
		allbooks = ibs.getBooks();
		for (Book b : allbooks) {
			if (!(b.getStatus() == BookStatus.AVAILABLE)) {
	//			if (b.getUser() == user) {
					books.add(b);
	//			}

			}
		}
		return ActionSupport.SUCCESS;
	}

	public List<Book> getBooks() {
		return books;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}