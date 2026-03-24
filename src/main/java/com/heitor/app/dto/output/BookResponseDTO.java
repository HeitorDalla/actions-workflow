package com.heitor.app.dto.output;

import java.time.LocalDate;

public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Long publicationYear;
    private String language;
    private Integer totalQuantity;
    private Integer availableQuantity;
    private LocalDate registrationDate;

    public BookResponseDTO() {}

    public BookResponseDTO(Long id,
                           String title,
                           String author,
                           String isbn,
                           Long publicationYear,
                           String language,
                           Integer totalQuantity,
                           Integer availableQuantity,
                           LocalDate registrationDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.language = language;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;
        this.registrationDate = registrationDate;
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
}
