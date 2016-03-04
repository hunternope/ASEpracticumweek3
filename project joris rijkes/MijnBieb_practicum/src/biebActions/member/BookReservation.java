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
public class BookReservation extends ActionSupport implements UserAware {

	private IBiebService ibs = ServiceProvider.getBiebService();
	private List<Book> books = new ArrayList<Book>();
	private int bookId = 0;
	private Book book;
	private User user;

	public void validate() {
		if (!ibs.bookExists(bookId)) {
			addFieldError("bookId", "id is ongeldig");
		} else if (ibs.getBookById(bookId).getStatus() != BookStatus.AVAILABLE) {
			addFieldError("bookId", "Dit boek is niet beschikbaar");
		}

	if(!getFieldErrors().isEmpty())
		for (Book b : ibs.getBooks()) {
			if (b.getStatus() == BookStatus.AVAILABLE) {
				books.add(b);
			}
		}
	}

	public String execute() {
		ArrayList<Book> userBooks = user.getBooks();
		book = ibs.getBookById(bookId);
		userBooks.add(book);
		book.setStatus(BookStatus.RESERVED);
		book.setUser(user);
		user.setBooks(userBooks);

		
		for (Book b : ibs.getBooks()) {
			if (b.getStatus() == BookStatus.AVAILABLE) {
				books.add(b);
			}
		}
		return ActionSupport.SUCCESS;

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

	public Book getBook() {
		return book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
