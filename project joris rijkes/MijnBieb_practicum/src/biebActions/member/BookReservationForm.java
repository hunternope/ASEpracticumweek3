package biebActions.member;

import java.util.ArrayList;
import java.util.List;

import biebDomain.Book;
import biebDomain.BookStatus;
import biebservice.IBiebService;
import biebservice.ServiceProvider;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BookReservationForm extends ActionSupport {

	private IBiebService ibs = ServiceProvider.getBiebService();
	private List<Book> books = new ArrayList<Book>();

	public String execute() {
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
}