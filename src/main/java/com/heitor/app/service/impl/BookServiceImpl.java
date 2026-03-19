package com.heitor.app.service.impl;

import com.heitor.app.entity.Book;
import com.heitor.app.exception.BookNotFoundException;
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

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book createBook(Book newBook){
        return bookRepository.save(newBook);
    }

    @Override
    public Book partiallyUpdateBook(Book newBook, Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (newBook.getTitle() != null) {
            book.setTitle(newBook.getTitle());
        }

        if (newBook.getAuthor() != null) {
            book.setAuthor(newBook.getAuthor());
        }

        if (newBook.getIsbn() != null) {
            book.setIsbn(newBook.getIsbn());
        }

        if (newBook.getPublicationYear() != null) {
            book.setPublicationYear(newBook.getPublicationYear());
        }

        if (newBook.getLanguage() != null) {
            book.setLanguage(newBook.getLanguage());
        }

        if (newBook.getTotalQuantity() != null) {
            book.setTotalQuantity(newBook.getTotalQuantity());
        }

        if (newBook.getAvailableQuantity() != null) {
            book.setAvailableQuantity(newBook.getAvailableQuantity());
        }

        if (newBook.getRegistrationDate() != null) {
            book.setRegistrationDate(newBook.getRegistrationDate());
        }

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book newBook, Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        book.setIsbn(newBook.getIsbn());
        book.setPublicationYear(newBook.getPublicationYear());
        book.setLanguage(newBook.getLanguage());
        book.setTotalQuantity(newBook.getTotalQuantity());
        book.setAvailableQuantity(newBook.getAvailableQuantity());
        book.setRegistrationDate(newBook.getRegistrationDate());

        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(book);
    }
}
