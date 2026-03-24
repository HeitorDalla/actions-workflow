package com.heitor.app.service;

import com.heitor.app.dto.BookCreateDTO;
import com.heitor.app.dto.BookUpdateDTO;
import com.heitor.app.dto.StockDTO;
import com.heitor.app.dto.response.BookResponseDTO;
import java.util.List;

public interface BookService {

    List<BookResponseDTO> getAllBooks(
            String title,
            String author,
            String isbn,
            Long publicationYear,
            String language,
            Integer totalQuantity
    );

    BookResponseDTO getBookById(Long id);

    BookResponseDTO createBook(BookCreateDTO dto);

    BookResponseDTO partiallyUpdateBook(BookUpdateDTO dto, Long id);

    BookResponseDTO updateBook(BookUpdateDTO dto, Long id);

    void deleteBook(Long id);

    BookResponseDTO addStock(StockDTO dto, Long id);

    BookResponseDTO removeStock(StockDTO dto, Long id);
}
