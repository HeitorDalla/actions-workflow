package com.heitor.app.service;

import com.heitor.app.entity.Book;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    List<Book> getAllBooks(
            String title,
            String author,
            String isbn,
            Long publicationYear,
            String language,
            Integer totalQuantity,
            Integer availableQuantity,
            LocalDate registrationDate
    );

    Book getBookById(Long id);

    Book createBook(Book newBook);

    Book updatedBook(Book newBook, Long id);

    void deleteBook(Long id);
}
