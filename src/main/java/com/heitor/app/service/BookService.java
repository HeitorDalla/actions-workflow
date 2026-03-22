package com.heitor.app.service;

import com.heitor.app.dto.request.BookRequestDTO;
import com.heitor.app.dto.response.BookResponseDTO;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    List<BookResponseDTO> getAllBooks(
            String title,
            String author,
            String isbn,
            Long publicationYear,
            String language,
            Integer totalQuantity,
            Integer availableQuantity,
            LocalDate registrationDate
    );

    BookResponseDTO getBookById(Long id);

    BookResponseDTO createBook(BookRequestDTO newBook);

    BookResponseDTO partiallyUpdateBook(BookRequestDTO newBook, Long id);

    BookResponseDTO updateBook(BookRequestDTO book, Long id);

    void deleteBook(Long id);
}
