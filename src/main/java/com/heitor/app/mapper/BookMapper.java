package com.heitor.app.mapper;

import com.heitor.app.dto.BookCreateDTO;
import com.heitor.app.dto.BookUpdateDTO;
import com.heitor.app.dto.response.BookResponseDTO;
import com.heitor.app.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {
    public List<BookResponseDTO> toDtoList(List<Book> entities) {
        if (entities.isEmpty()) {
            return null;
        }

        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public BookResponseDTO toDto(Book entity) {
        if (entity == null) {
            return null;
        }

        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setIsbn(entity.getIsbn());
        dto.setPublicationYear(entity.getPublicationYear());
        dto.setLanguage(entity.getLanguage());
        dto.setTotalQuantity(entity.getTotalQuantity());
        dto.setRegistrationDate(entity.getRegistrationDate());
        dto.setAvailableQuantity(entity.getAvailableQuantity());

        return dto;
    }

    public Book fromCreateDTO(BookCreateDTO dto) { // create
        if (dto == null) {
            return null;
        }

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setLanguage(dto.getLanguage());
        book.setTotalQuantity(dto.getTotalQuantity());

        return book;
    }

    public void updateEntityFromDto(BookUpdateDTO dto, Book entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getTitle() != null) {
            entity.setTitle(dto.getTitle());
        }

        if (dto.getAuthor() != null) {
            entity.setAuthor(dto.getAuthor());
        }

        if (dto.getIsbn() != null) {
            entity.setIsbn(dto.getIsbn());
        }

        if (dto.getPublicationYear() != null) {
            entity.setPublicationYear(dto.getPublicationYear());
        }

        if (dto.getLanguage() != null) {
            entity.setLanguage(dto.getLanguage());
        }
    }
}
