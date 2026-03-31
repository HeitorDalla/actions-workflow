package com.heitor.app.repository;

import com.heitor.app.entity.Book;
import com.heitor.app.enums.BookStatus;
import com.heitor.app.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Busca livros, aplicando filtros quando os parametros forem informados
    @Query("""
        SELECT b
        FROM Book b
        WHERE (:title IS NULL OR LOWER(b.title) = LOWER(:title))
          AND (:author IS NULL OR LOWER(b.author) = LOWER(:author))
          AND (:isbn IS NULL OR LOWER(b.isbn) = LOWER(:isbn))
          AND (:publicationYear IS NULL OR b.publicationYear = :publicationYear)
          AND (:language IS NULL OR LOWER(b.language) = LOWER(:language))
          AND (:totalQuantity IS NULL OR b.totalQuantity = :totalQuantity)
          AND (:bookStatus IS NULL OR b.bookStatus = :bookStatus)
          AND (:recordStatus IS NULL OR b.recordStatus = :recordStatus)
    """)
    List<Book> getAllBooks(
            @Param("title") String title,
            @Param("author") String author,
            @Param("isbn") String isbn,
            @Param("publicationYear") Long publicationYear,
            @Param("language") String language,
            @Param("totalQuantity") Integer totalQuantity,
            @Param("bookStatus") BookStatus bookStatus,
            @Param("recordStatus") RecordStatus recordStatus
    );
}