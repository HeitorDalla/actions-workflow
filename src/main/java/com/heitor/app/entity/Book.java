package com.heitor.app.entity;

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

    @Column(name = "book_total_quantity", nullable = false)
    private Integer totalQuantity;

    @Column(name = "book_available_quantity", nullable = false)
    private Integer availableQuantity;

    @Column(name = "book_registration_date", nullable = false)
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<LoanItem> loanItems = new ArrayList<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

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
                List<LoanItem> loanItems,
                List<Reservation> reservations) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.language = language;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;
        this.registrationDate = registrationDate;
        this.loanItems = loanItems;
        this.reservations = reservations;
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

    public List<LoanItem> getLoanItems() {
        return loanItems;
    }

    public void setLoanItems(List<LoanItem> loanItems) {
        this.loanItems = loanItems;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}