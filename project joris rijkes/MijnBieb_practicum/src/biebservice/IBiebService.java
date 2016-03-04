package biebservice;

import java.util.List;

import biebDomain.Book;
import biebDomain.User;

public interface IBiebService {
	
	void addBook(int rookId, String titel,String auteur,String isbn,int pages);
	List<Book> getBooks();
	Book getBookById(int bookId);
	boolean bookExists (int id);
	void deleteBook(int rookId);	
	
	boolean userExists (String username);
	List<User> getUsers();
	List<User> getMembers();
	User getUserByUsername(String username);
	boolean hasBorrowedBooks(String username);
	void deleteUser(String username);
	void addMember(String username, String password);
	void addCoworker(String username, String password);
	
	void changePassword(String username,String password);
	void reserveBook(String username, int rookId);
	void cancelReservationBook(String username, int rookId);
	void takeBackBook(int bookId);
	void lendBook(String username, int bookId);
	List<Book> getBooks(String username);
}
