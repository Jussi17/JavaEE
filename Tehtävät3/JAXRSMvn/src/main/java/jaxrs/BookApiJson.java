/* BookApiJson.java sisältää REST-Apin
 * joka käyttää JSON-muotoa tiedon välitykseen
 * REST-Apin reitit (GET, POST jne.) luodaan 
 * annotaatioiden avulla.
 * 
 * Tässä esimerkissä reittejä ei ole suojattu,
 * mutta ainakin julkaistavan apin POST, DELETE ja PUT -reitit pitäisi suojata: 
 * https://antoniogoncalves.org/2016/10/03/securing-jax-rs-endpoints-with-jwt/
 * 
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationPath("jsonapi")
@Path("jsonbook") // polku on: localhost:8080/JAXSRMvn/jsonapi/jsonbook
public class BookApiJson extends Application {

	// BookService injektoidaan tässä
	@EJB
	private BookService bs;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks() {
		List<Book> books = bs.getAllBooks();
		// GenericEntity -wrapperi tarvitaan jotta geneerinen tyyppi säilyisi
		GenericEntity<List<Book>> list = new GenericEntity<List<Book>>(books) {
		};
		return Response.ok(list).build();
	}

	@GET
	@Path("{id:}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBookById() {
		// TODO: id:n perustella haun toiminnallisuus
		return "Haku id:n perusteella ei vielä toimi";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveBook(Book book) {
		book = bs.saveBook(book);
		return Response.ok(book).build(); // palautetaan lisätty kirja
	}

	@DELETE
	@Path("{id:}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBook(@PathParam("id") int id) {
		Book book = bs.deleteBook(id);
		return Response.ok(book).build(); // palautetaan poistettu kirja
	}

	@PUT
	@Path("{id:}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBook(@PathParam("id") int id, Book book) {
		book = bs.updateBook(id, book);
		return Response.ok(book).build();
	}

}
