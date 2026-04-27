/* BookController toimii tiedon välittäjänä viewin (index.xhtml) 
 * ja Servicen (BookService.java) välillä.
 * 
 * Kontrollerin tehtävänä on siis ottaa vastaan käyttäjän syöte viewistä
 * ja päivittää viewiä välittämällä sinne kannasta tuleva data. 
 * Arkkitehtuurin kannalta on parempi että kontrolleri ei sisällä 
 * sovelluslogiikkaa vaan se hoidetaan Servicessä. 
 * 
 */
package jpajsf;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

//import jpajsf.Book; //ei tarvitse importata kun on samassa paketissa
@Named(value = "bookController")
@RequestScoped
public class BookController implements Serializable {

	private static final long serialVersionUID = 1L;
	// Service injektoidaan kontrolleriin tässä
	@EJB
	BookService bs;
	private Book book;

	public BookController() {
		this.book = new Book();
	}

	public void setBook(Book book) {
		this.book = book;
	}

	/*
	 * getBook()-metodin avulla otetaan vastaan käyttäjän syöte viewistä. Tätä
	 * metodia ei pitäisi käyttää muuhun tarkoitukseen.
	 */
	public Book getBook() {
		return book;
	}

	/*
	 * Metodi joka kutsuu BookServicen saveBook() -metodia. Tallennus tapahtuu
	 * BookServicessä
	 */
	public void saveBook() {
		this.bs.save(book);

	}

	/*
	 * Metodi joka kutsuu BookServicen getAllBooks() -metodia. Tietokantahaku
	 * tapahtuu BookServicessä.
	 */
	public List<Book> listAllBooks() {

		List<Book> allBooks = this.bs.getAllBooks();

		return allBooks;

	}
}
