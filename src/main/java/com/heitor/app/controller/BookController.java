package com.heitor.app.controller;

import com.heitor.app.dto.common.StockDTO;
import com.heitor.app.dto.input.BookCreateDTO;
import com.heitor.app.dto.input.BookUpdateDTO;
import com.heitor.app.dto.output.BookResponseDTO;
import com.heitor.app.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Long publicationYear,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer totalQuantity) {

        return ResponseEntity.ok(bookService.getAllBooks(
                title,
                author,
                isbn,
                publicationYear,
                language,
                totalQuantity
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping()
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookCreateDTO dto) {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDTO> partiallyUpdateBook(@RequestBody BookUpdateDTO dto,
                                                               @PathVariable Long id) {
        return ResponseEntity.ok(bookService.partiallyUpdateBook(dto, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@RequestBody BookUpdateDTO dto,
                                                      @PathVariable Long id) {
        return ResponseEntity.ok(bookService.updateBook(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

    // Rotas para controlar estoque
    @PatchMapping("/{id}/add-stock")
    public ResponseEntity<BookResponseDTO> addStock(@RequestBody StockDTO dto,
                                                    @PathVariable Long id) {
        return ResponseEntity.ok(bookService.addStock(dto, id));
    }

    @PatchMapping("/{id}/remove-stock")
    public ResponseEntity<BookResponseDTO> removeStock(@RequestBody StockDTO dto,
                                                       @PathVariable Long id) {
        return ResponseEntity.ok(bookService.removeStock(dto, id));
    }
}
