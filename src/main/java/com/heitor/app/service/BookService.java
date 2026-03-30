package com.heitor.app.service;

import com.heitor.app.dto.common.StockDTO;
import com.heitor.app.dto.input.BookCreateDTO;
import com.heitor.app.dto.input.BookPatchDTO;
import com.heitor.app.dto.input.BookUpdateDTO;
import com.heitor.app.dto.output.BookResponseDTO;
import java.util.List;

public interface BookService {

    List<BookResponseDTO> getAllBooks(String title,
                                      String author,
                                      String isbn,
                                      Long publicationYear,
                                      String language,
                                      Integer totalQuantity
    );

    BookResponseDTO getBookById(Long id);

    BookResponseDTO createBook(BookCreateDTO dto);

    BookResponseDTO partiallyUpdateBook(BookPatchDTO dto,
                                        Long id);

    BookResponseDTO updateBook(BookUpdateDTO dto,
                               Long id);

    void deleteBook(Long id);

    BookResponseDTO addStock(StockDTO dto,
                             Long id);

    BookResponseDTO removeStock(StockDTO dto,
                                Long id);
}
