package com.heitor.app.service.impl;

import com.heitor.app.dto.request.BookRequestDTO;
import com.heitor.app.dto.response.BookResponseDTO;
import com.heitor.app.entity.Book;
import com.heitor.app.exception.BookNotFoundException;
import com.heitor.app.mapper.BookMapper;
import com.heitor.app.repository.BookRepository;
import com.heitor.app.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private BookMapper mapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BookResponseDTO> getAllBooks(String title,
                                             String author,
                                             String isbn,
                                             Long publicationYear,
                                             String language,
                                             Integer totalQuantity,
                                             Integer availableQuantity,
                                             LocalDate registrationDate) {

        List<Book> books = bookRepository.getAllBooks(
                title,
                author,
                isbn,
                publicationYear,
                language,
                totalQuantity,
                availableQuantity,
                registrationDate
        );

        return mapper.toDtoList(books);
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return mapper.toDto(book);
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO dto){
        Book book = mapper.toEntity(dto);

        Book savedBook = bookRepository.save(book);

        return mapper.toDto(savedBook);
    }

    @Override
    public BookResponseDTO partiallyUpdateBook(BookRequestDTO dto, Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        mapper.updateEntityFromDto(dto, book);

        book = bookRepository.save(book);

        return mapper.toDto(book);
    }

    @Override
    public BookResponseDTO updateBook(BookRequestDTO dto, Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        Book newBook = mapper.toEntity(dto);
        newBook.setId(book.getId());

        bookRepository.save(newBook);

        return mapper.toDto(newBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(book);
    }
}
