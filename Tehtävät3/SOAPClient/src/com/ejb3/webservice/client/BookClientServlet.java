package com.ejb3.webservice.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ejb3.webservice.Book;
import com.ejb3.webservice.HelloWorldJAXWS;
import com.ejb3.webservice.HelloWorldJAXWSService;

@WebServlet("/BookClientServlet")
public class BookClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HelloWorldJAXWSService service = new HelloWorldJAXWSService();
        HelloWorldJAXWS port = service.getHelloWorldJAXWSPort();

        List<Book> books = port.getAllBooks();

        out.println("<html><body>");
        out.println("<h1>Kirjat SOAP-API:sta</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>Id</th><th>Title</th><th>Author</th></tr>");
        for (Book b : books) {
            out.println("<tr>");
            out.println("<td>" + b.getId() + "</td>");
            out.println("<td>" + b.getTitle() + "</td>");
            out.println("<td>" + b.getAuthor() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
    }
}