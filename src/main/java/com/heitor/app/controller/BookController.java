package com.heitor.app.controller;

import com.heitor.app.dto.common.StockDTO;
import com.heitor.app.dto.input.BookCreateDTO;
import com.heitor.app.dto.input.BookPatchDTO;
import com.heitor.app.dto.input.BookUpdateDTO;
import com.heitor.app.dto.output.BookResponseDTO;
import com.heitor.app.enums.BookStatus;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

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
            @RequestParam(required = false) Integer totalQuantity,
            @RequestParam(required = false) BookStatus bookStatus,
            @RequestParam(required = false) RecordStatus recordStatus) {

        return ResponseEntity.ok(bookService.getAllBooks(
                title,
                author,
                isbn,
                publicationYear,
                language,
                totalQuantity,
                bookStatus,
                recordStatus
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping()
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookCreateDTO dto) {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDTO> partiallyUpdateBook(@RequestBody BookPatchDTO dto,
                                                               @PathVariable Long id) {
        return ResponseEntity.ok(bookService.partiallyUpdateBook(dto, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@Valid @RequestBody BookUpdateDTO dto,
                                                      @PathVariable Long id) {
        return ResponseEntity.ok(bookService.updateBook(dto, id));
    }

    // Rotas para Ativar e Desativar Livros
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateBook(@PathVariable Long id) {
        bookService.deactivateBook(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateBook(@PathVariable Long id) {
        bookService.activateBook(id);

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
