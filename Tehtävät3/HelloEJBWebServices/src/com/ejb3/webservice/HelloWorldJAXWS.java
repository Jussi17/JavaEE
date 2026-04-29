package com.ejb3.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@Stateless
@LocalBean
public class HelloWorldJAXWS {

    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void initiate() {
        books.add(new Book(1, "Tuntematon Sotilas", "Väinö Linna"));
        books.add(new Book(2, "Harry Potter", "J.K. Rowling"));
        books.add(new Book(3, "Taru Sormusten Herrasta", "J.R.R. Tolkien"));
    }

    @WebMethod
    public String sayHello(@WebParam(name = "name") String name) {
        return "Hello " + name + "!";
    }

    @WebMethod
    public List<Book> getAllBooks() {
        return books;
    }

    @WebMethod
    public Book addBook(@WebParam(name = "id") Integer id,
                        @WebParam(name = "title") String title,
                        @WebParam(name = "author") String author) {
        Book book = new Book(id, title, author);
        books.add(book);
        return book;
    }
}