package biebActions.visitor;

import java.util.ArrayList;
import java.util.List;

import biebDomain.Book;
import biebservice.IBiebService;
import biebservice.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class BookInfo extends ActionSupport implements ModelDriven<Book> {

	private IBiebService ibs = ServiceProvider.getBiebService();
	private List<Book> books = new ArrayList<Book>();
	private int bookId = 0;
	private Book book;

	public void validate() {
		if (!ibs.bookExists(bookId)) {
			addFieldError("bookId", "id is ongeldig");
			books = ibs.getBooks();
		}

	}

	public String execute() {
		setBook(getModel());
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

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public Book getModel() {
		return ibs.getBookById(bookId);
	}
}
