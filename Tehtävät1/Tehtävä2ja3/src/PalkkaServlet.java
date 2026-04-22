import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PalkkaServlet")
public class PalkkaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        double tunnit = Double.parseDouble(request.getParameter("tunnit"));
        double tuntipalkka = Double.parseDouble(request.getParameter("tuntipalkka"));
        double veroprosentti = Double.parseDouble(request.getParameter("veroprosentti"));
        
        double bruttopalkka = tunnit * tuntipalkka;
        double nettopalkka = bruttopalkka * (1 - veroprosentti / 100);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><body>");
        out.println("<h1>Palkan laskenta</h1>");
        out.println("<p>Bruttopalkka: " + bruttopalkka + " €</p>");
        out.println("<p>Nettopalkka: " + nettopalkka + " €</p>");
        out.println("</body></html>");
    }
}