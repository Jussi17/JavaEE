import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PalkkaServlet2")
public class PalkkaServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        double tunnit = Double.parseDouble(request.getParameter("tunnit"));
        double tuntipalkka = Double.parseDouble(request.getParameter("tuntipalkka"));
        double veroprosentti = Double.parseDouble(request.getParameter("veroprosentti"));
        
        double bruttopalkka = tunnit * tuntipalkka;
        double nettopalkka = bruttopalkka * (1 - veroprosentti / 100);
        
        request.setAttribute("bruttopalkka", bruttopalkka);
        request.setAttribute("nettopalkka", nettopalkka);
        
        request.getRequestDispatcher("palkkalomake2.jsp").forward(request, response);
    }
}