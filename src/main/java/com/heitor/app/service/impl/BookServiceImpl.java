package com.heitor.app.service.impl;

import com.heitor.app.entity.Book;
import com.heitor.app.repository.BookRepository;
import com.heitor.app.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks(String title,
                                  String author,
                                  String isbn,
                                  Long publicationYear,
                                  String language,
                                  Integer totalQuantity,
                                  Integer availableQuantity,
                                  LocalDate registrationDate) {

        return bookRepository.getAllBooks(
                title,
                author,
                isbn,
                publicationYear,
                language,
                totalQuantity,
                availableQuantity,
                registrationDate
        );
    }
}
