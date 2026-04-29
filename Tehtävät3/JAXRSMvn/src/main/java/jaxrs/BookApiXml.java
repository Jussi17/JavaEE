/* BookApiXml.java sisältää REST-Apin
 * joka käyttää XML-muotoa tiedon välitykseen
 * REST-Apin reitit (GET, POST jne.) luodaan 
 * annotaatioiden avulla.
 * 
 * Tässä esimerkissä reittejä ei ole suojattu,
 * mutta ainakin julkaistavan apin POST, DELETE ja PUT -reitit pitäisi suojata: 
 * https://antoniogoncalves.org/2016/10/03/securing-jax-rs-endpoints-with-jwt/
 */

package jaxrs;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// kirjasto jolla voi muuntaa listoja ja olioita XML -muotoon
import com.thoughtworks.xstream.XStream;

@ApplicationPath("xmlapi")
@Path("xmlbook") // polku on: localhost:8080/JAXSRMvn/xmlapi/xmlbook
public class BookApiXml extends Application {

	XStream xstream = new XStream();// xstream olio luodaan
	@EJB
	private BookService bs;// BookService injektoidaan

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getBooks() {
		List<Book> books = bs.getAllBooks();
		xstream.alias("book", Book.class); // vaihdetaan elementin nimi
		String xmloutput = xstream.toXML(books); // muunnos listasta XML:ksi
		return Response.ok(xmloutput).build(); // palautetaan kaikki kirjat
	}

	@GET
	@Path("{id}") // polku on: localhost:8080/JAXSRMvn/xmlapi/xmlbook/1
	@Produces(MediaType.APPLICATION_XML)
	public Response getBookById(@PathParam("id") int id) {
		Book book = bs.getBookById(id);
		xstream.alias("book", Book.class);
		String xmloutput = xstream.toXML(book); // muunnos oliosta XML:ksi
		return Response.ok(xmloutput).build(); // palautetaan id:n perusteella haettu kirja

	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public String saveBook(String bookXML) {
		xstream.processAnnotations(Book.class); // katsotaan Book-luokasta(annotaatio) millainen olio pitää luoda
		Book book = (Book) xstream.fromXML(bookXML); // muunnos XML:stä olioksi
		bs.saveBook(book);
		return "Kirja lisätty";
	}

	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	@Path("{id}")
	public String deleteBook(@PathParam("id") int id) {
		// TODO: poiston toiminnallisuus
		return "Kirjan poisto ei vielä toimi";
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public String updateBook(@PathParam("id") int id, String bookXML) {
		// TODO: muokkauksen toiminnallisuus
		return "Kirjan muokkaus ei vielä toimi";
	}

}
