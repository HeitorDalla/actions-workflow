package com.heitor.app.service.impl;

import com.heitor.app.dto.common.StockDTO;
import com.heitor.app.dto.input.BookCreateDTO;
import com.heitor.app.dto.input.BookPatchDTO;
import com.heitor.app.dto.input.BookUpdateDTO;
import com.heitor.app.dto.output.BookResponseDTO;
import com.heitor.app.entity.Book;
import com.heitor.app.enums.BookStatus;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.exception.BookNotFoundException;
import com.heitor.app.exception.BusinessException;
import com.heitor.app.mapper.BookMapper;
import com.heitor.app.repository.BookRepository;
import com.heitor.app.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BookResponseDTO> getAllBooks(String title,
                                             String author,
                                             String isbn,
                                             Long publicationYear,
                                             String language,
                                             Integer totalQuantity) {

        List<Book> books = bookRepository.getAllBooks(
                title,
                author,
                isbn,
                publicationYear,
                language,
                totalQuantity
        );

        return mapper.toDtoList(books);
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return mapper.toDto(book);
    }

    @Transactional
    @Override
    public BookResponseDTO createBook(BookCreateDTO dto){
        Book book = mapper.fromCreateDTO(dto);

        // Regras de Negócio
        book.setRegistrationDate(LocalDate.now());
        book.setAvailableQuantity(dto.getTotalQuantity());
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setRecordStatus(RecordStatus.ACTIVE);

        Book savedBook = bookRepository.save(book);
        return mapper.toDto(savedBook);
    }

    @Transactional
    @Override
    public BookResponseDTO partiallyUpdateBook(BookPatchDTO dto,
                                               Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        mapper.patchEntity(dto, book);

        book = bookRepository.save(book);
        return mapper.toDto(book);
    }

    @Transactional
    @Override
    public BookResponseDTO updateBook(BookUpdateDTO dto,
                                      Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        mapper.updateEntity(dto, book);

        bookRepository.save(book);
        return mapper.toDto(book);
    }

    @Transactional
    @Override
    public void deactivateBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        // Verificar se o livro esta em empréstimos pedentes
        if (!book.getLoans().isEmpty()) {
            throw new BusinessException("The book cannot be deactivated because they have active loans.");
        }

        // Verificar se o livro esta em um empréstimos com multas pendentes


        // Verificar se o livro esta reservado
        if (!book.getReservations().isEmpty()) {
            throw new BusinessException("The book cannot be deactivated because they have active reservations.");
        }

        book.setRecordStatus(RecordStatus.INACTIVE);

        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void activateBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setBookStatus(BookStatus.AVAILABLE);
        book.setRecordStatus(RecordStatus.ACTIVE);

        bookRepository.save(book);
    }

    @Transactional
    @Override
    public BookResponseDTO addStock(StockDTO dto,
                                    Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        // Regras de negócio
        book.setTotalQuantity(book.getTotalQuantity() + dto.getQuantity());
        book.setAvailableQuantity(book.getAvailableQuantity() + dto.getQuantity());

        bookRepository.save(book);
        return mapper.toDto(book);
    }

    @Transactional
    @Override
    public BookResponseDTO removeStock(StockDTO dto,
                                       Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        // Regras de negócio
        if (book.getAvailableQuantity() < dto.getQuantity()) {
            throw new BusinessException("Out of stock.");
        }
        book.setTotalQuantity(book.getTotalQuantity() - dto.getQuantity());
        book.setAvailableQuantity(book.getAvailableQuantity() - dto.getQuantity());

        bookRepository.save(book);
        return mapper.toDto(book);
    }
}
