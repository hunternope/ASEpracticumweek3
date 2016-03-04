package biebActions.coworker;

import java.util.List;

import biebAware.UserAware;
import biebDomain.Book;
import biebDomain.BookStatus;
import biebDomain.User;
import biebDomain.UserRole;
import biebservice.IBiebService;
import biebservice.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LendBook extends ActionSupport implements UserAware {

	private IBiebService ibs = ServiceProvider.getBiebService();
	private List<User> users = ibs.getMembers();
	private User user;
	private List<Book> books = ibs.getBooks();
	private String username;
	private int bookId;
	private Book book;
	private User bookUser;

	public void validate() {
		if (!ibs.userExists(username)) { 
			addFieldError("username", "gebruiker bestaat niet");
			return;
		} else if (!ibs.bookExists(bookId)){
			addFieldError("bookId", "boek bestaat niet");
			return;
		}
		bookUser = ibs.getUserByUsername(username);
		if(bookUser.getUr()!=UserRole.MEMBER){
			addFieldError("bookId", "deze user is geen standaard lid");
		}
		book = ibs.getBookById(bookId);
		if (book.getUser()!=bookUser && book.getStatus() == BookStatus.RESERVED){
			addFieldError("username", "dit boek is door een andere gebruiker gereserveerd!");
		}
		//TODO: fix bij select
	}
	
	public String execute() {
		book = ibs.getBookById(bookId);
		book.setStatus(BookStatus.BORROWED);
		book.setUser(ibs.getUserByUsername(username));

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public Book getBook(){
		return book;
	}
}