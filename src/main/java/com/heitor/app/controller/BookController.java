package com.heitor.app.controller;

import com.heitor.app.entity.Book;
import com.heitor.app.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Long publicationYear,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer totalQuantity,
            @RequestParam(required = false) Integer availableQuantity,
            @RequestParam(required = false) LocalDate registrationDate) {

        List<Book> books = bookService.getAllBooks(
                title,
                author,
                isbn,
                publicationYear,
                language,
                totalQuantity,
                availableQuantity,
                registrationDate
        );

        LOGGER.info("Books successfully fetched: {}", books);

        return books;
    }
}
