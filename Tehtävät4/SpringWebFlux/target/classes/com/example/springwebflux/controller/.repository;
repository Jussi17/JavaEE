package com.example.springwebflux.controller;

import com.example.springwebflux.model.Book;
import com.example.springwebflux.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> getBookById(@PathVariable String id) {
        return bookRepository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> updateBook(@PathVariable String id, @RequestBody Book bookDetails) {
        return bookRepository.findById(id)
                .flatMap(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setYear(bookDetails.getYear());
                    return bookRepository.save(book);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        return bookRepository.deleteById(id);
    }
}