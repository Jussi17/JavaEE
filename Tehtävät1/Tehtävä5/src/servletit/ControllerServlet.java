/*
 * ControllerServlet ohjailee viestejä käyttöliittymältä
 * palveluja suorittaville tiedostoille (palveluservleteille)
 *
 */

package servletit;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerServlet", urlPatterns = { "/ControllerServlet" })
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// luodaan uusi sessio jos tullaan sivulle ekan kerran
		HttpSession session = request.getSession(true);
		session.setAttribute("avain", "arvo");// Turha homma, mutta tehdään jotta ei tule huomautusta

		/*
		 * Ohjataan pyyntö palveluservletille Tässä voitaisiin (pyynnön perusteella
		 * (if)) valita jatkokäsittelyn tekevä servletti, jos niitä olisi useita
		 */

		// RequestDispatcher-olio tarvitaan tietojen välittämiseksi tiedostolta toiselle
		String submit = request.getParameter("Submit");
		RequestDispatcher rd = request.getRequestDispatcher("/DispatcherServlet");
		rd.include(request, response);

		// Kontrolli palaa tähän kun DispatcherServlet on tehnyt työnsä.

		/*
		 * Valitaan kutsuttava JSP-sivu. Jos vaihtoehtoja olisi useita, voitaisiin
		 * valita iffityksellä.
		 */

		// siirrytään vastaus.jsp-sivulle
		if (submit != null && submit.equals("Muokkaa")) {
	        request.getRequestDispatcher("muokkaa.jsp").forward(request, response);
	    } else {
	        request.getRequestDispatcher("vastaus.jsp").forward(request, response);
	    }

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
