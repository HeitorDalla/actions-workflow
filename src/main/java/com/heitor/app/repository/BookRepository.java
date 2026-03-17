package com.heitor.app.repository;

import com.heitor.app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("""
        SELECT b
        FROM Book b
        WHERE (:title IS NULL OR LOWER(:title) LIKE LOWER(CONCAT('%', :title, '%')))
          AND (:author IS NULL OR LOWER(b.author) = :author)
          AND (:isbn IS NULL OR LOWER(b.isbn) = :isbn)
          AND (:publicationYear IS NULL OR b.publicationYear = :publicationYear)
          AND (:language IS NULL OR LOWER(b.language) = :language)
          AND (:totalQuantity IS NULL OR b.totalQuantity = :totalQuantity)
          AND (:availableQuantity IS NULL OR b.availableQuantity = :availableQuantity)
          AND (:registrationDate IS NULL OR b.registrationDate = :registrationDate)
    """)
    List<Book> getAllBooks(
            @Param("title") String title,
            @Param("author") String author,
            @Param("isbn") String isbn,
            @Param("publicationYear") Long publicationYear,
            @Param("language") String language,
            @Param("totalQuantity") Integer totalQuantity,
            @Param("availableQuantity") Integer availableQuantity,
            @Param("registrationDate") LocalDate registrationDate
    );
}