package com.example.springwebflux.router;

import com.example.springwebflux.handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> route(BookHandler handler) {
        return RouterFunctions
                .route(GET("/api/functional/books"), handler::getAllBooks)
                .andRoute(GET("/api/functional/books/{id}"), handler::getBookById)
                .andRoute(POST("/api/functional/books"), handler::createBook)
                .andRoute(DELETE("/api/functional/books/{id}"), handler::deleteBook);
    }
}