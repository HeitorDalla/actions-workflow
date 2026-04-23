package com.heitor.app.entity;

import com.heitor.app.enums.BookStatus;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.exception.BusinessException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_title", nullable = false)
    private String title;

    @Column(name = "book_author", nullable = false, length = 150)
    private String author;

    @Column(name = "book_isbn", nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(name = "book_publication_year", nullable = false)
    private Long publicationYear;

    @Column(name = "book_language", nullable = false)
    private String language;

    @Column(name = "book_total_quantity")
    private Integer totalQuantity;

    @Column(name = "book_available_quantity", nullable = false)
    private Integer availableQuantity;

    @Column(name = "book_registration_date", nullable = false)
    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_status", nullable = false)
    private BookStatus bookStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_record_status", nullable = false)
    private RecordStatus recordStatus;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Loan> loans = new ArrayList<>();

    public Book() {}

    public Book(Long id,
                String title,
                String author,
                String isbn,
                Long publicationYear,
                String language,
                Integer totalQuantity,
                Integer availableQuantity,
                LocalDate registrationDate,
                BookStatus bookStatus,
                RecordStatus recordStatus,
                List<Reservation> reservations,
                List<Loan> loans) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.language = language;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;
        this.registrationDate = registrationDate;
        this.bookStatus = bookStatus;
        this.recordStatus = recordStatus;
        this.reservations = reservations;
        this.loans = loans;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Long publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void activate() {
        bookStatus = BookStatus.AVAILABLE;
        recordStatus = RecordStatus.ACTIVE;
    }

    public void deactivate() {
        bookStatus = BookStatus.UNAVAILABLE;
        recordStatus = RecordStatus.INACTIVE;
    }

    public void initialize(Integer totalQuantity) {
        if (id != null) {
            throw new BusinessException("Book already initialized.");
        }

        if (totalQuantity == null || totalQuantity <= 0) {
            throw new BusinessException("Total quantity must be greater than zero.");
        }

        registrationDate = LocalDate.now();
        this.totalQuantity = totalQuantity;
        availableQuantity = totalQuantity;
        activate();
    }

    public void addStock(Integer quantity) {
        if (recordStatus != RecordStatus.ACTIVE) {
            throw new BusinessException("It is not possible to add inventory to an inactive book.");
        }

        if (quantity <= 0) {
            throw new BusinessException("Quantity must be greater than zero.");
        }

        totalQuantity += quantity;
        availableQuantity += quantity;
    }

    public void removeStock(Integer quantity) {
        if (quantity <= 0) {
            throw new BusinessException("Quantity must be greater than zero.");
        }

        if (availableQuantity < quantity) {
            throw new BusinessException("Out of stock.");
        }

        totalQuantity -= quantity;
        availableQuantity -= quantity;
    }

    public void borrow() {
        if (availableQuantity <= 0) {
            throw new BusinessException("Book out of stock.");
        }

        availableQuantity--;

        if (availableQuantity == 0) {
            bookStatus = BookStatus.UNAVAILABLE;
        }
    }

    public void returnBook() {
        if (recordStatus != RecordStatus.ACTIVE) {
            throw new BusinessException("Cannot return book that is inactive.");
        }

        availableQuantity++;

        if (availableQuantity > 0) {
            bookStatus = BookStatus.AVAILABLE;
        }
    }
}