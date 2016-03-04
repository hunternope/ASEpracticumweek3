package biebActions.manager;

import com.opensymphony.xwork2.ActionSupport;

import biebservice.IBiebService;
import biebservice.ServiceProvider;

@SuppressWarnings("serial")
public class AddBook extends ActionSupport {

	private IBiebService ibs = ServiceProvider.getBiebService();
	private int id;
	private String titel;
	private String auteur;
	private String isbn;
	private int pages;

	public String execute() {
		ibs.addBook(id, titel, auteur, isbn, pages);
		return SUCCESS;
	}

	public void validate() {

		if (ibs.bookExists(id)) {
			addFieldError("id", "boek met dit id bestaat al");
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
}
