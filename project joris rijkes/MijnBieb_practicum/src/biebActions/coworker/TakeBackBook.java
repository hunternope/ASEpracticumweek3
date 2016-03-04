package biebActions.coworker;

import java.util.List;

import biebAware.UserAware;
import biebDomain.Book;
import biebDomain.BookStatus;
import biebDomain.User;
import biebservice.IBiebService;
import biebservice.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TakeBackBook extends ActionSupport implements UserAware {

	private IBiebService ibs = ServiceProvider.getBiebService();
	private User user;
	private List<Book> books = ibs.getBooks();
	private int bookId;
	private Book book;

	public void validate() {
		if (!ibs.bookExists(bookId)){
			addFieldError("bookId", "boek bestaat niet");
			return;
		}
		book = ibs.getBookById(bookId);
		if (book.getStatus()!=BookStatus.BORROWED){
			addFieldError("bookId", "Boek is niet uitgeleend!");
		}
	
	}
	
	public String execute() {
		book = ibs.getBookById(bookId);
		book.setStatus(BookStatus.AVAILABLE);
		book.setUser(null);

		return ActionSupport.SUCCESS;
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