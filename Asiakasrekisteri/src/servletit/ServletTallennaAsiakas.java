package servletit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import luokat.SQL;

@WebServlet(name = "ServletTallennaAsiakas", urlPatterns = { "/ServletTallennaAsiakas" })
public class ServletTallennaAsiakas extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Connection conn = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            conn = SQL.openConnection();
        } catch (Exception e) {
            System.out.println("Kantaan ei saada yhteyttä " + e);
        }
    }

    @Override
    public void destroy() {
        try {
            conn.close();
        } catch (SQLException se) {
            System.out.println("Poikkeus " + se);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false);
            String log = (String) session.getAttribute("login");
            if (!log.equals("ok")) {
                response.sendRedirect("login.html");
            }
        } catch (Exception e) {
            response.sendRedirect("login.html");
            return;
        }

        String id = request.getParameter("id");
        String nimi = request.getParameter("nimi");
        String osoite = request.getParameter("osoite");
        String puhelin = request.getParameter("puhelin");
        String email = request.getParameter("email");
        String salasana = request.getParameter("salasana");

        try {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE asiakkaat SET nimi=?, osoite=?, puhelin=?, email=?, salasana=? WHERE id=?");
            ps.setString(1, nimi);
            ps.setString(2, osoite);
            ps.setString(3, puhelin);
            ps.setString(4, email);
            ps.setString(5, salasana);
            ps.setString(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Virhe päivityksessä: " + e);
        }

        response.sendRedirect("AsiakkaatServlet");
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