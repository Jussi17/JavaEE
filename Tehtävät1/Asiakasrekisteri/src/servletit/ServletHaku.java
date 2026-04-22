package servletit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import luokat.AsiakasBean;
import luokat.SQL;

@WebServlet(name = "ServletHaku", urlPatterns = { "/ServletHaku" })
public class ServletHaku extends HttpServlet {

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

        String hakusana = request.getParameter("hakusana");
        String hakuvalinta = request.getParameter("hakuvalinta");

        ArrayList<AsiakasBean> list = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM asiakkaat WHERE " + hakuvalinta + " LIKE '%" + hakusana + "%'");
            while (rs.next()) {
                AsiakasBean papu = new AsiakasBean();
                papu.setId(rs.getString("id"));
                papu.setNimi(rs.getString("nimi"));
                papu.setOsoite(rs.getString("osoite"));
                papu.setPuhelin(rs.getString("puhelin"));
                papu.setEmail(rs.getString("email"));
                papu.setSalasana(rs.getString("salasana"));
                list.add(papu);
            }
        } catch (SQLException e) {
            System.out.println("Virhe haussa: " + e);
        }

        request.setAttribute("list", list);
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/haku.jsp");
        rd.forward(request, response);
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