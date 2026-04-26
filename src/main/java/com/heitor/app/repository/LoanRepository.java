package com.heitor.app.repository;

import com.heitor.app.entity.Book;
import com.heitor.app.entity.Loan;
import com.heitor.app.entity.User;
import com.heitor.app.enums.LoanStatus;
import com.heitor.app.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    // Busca emprestimos, aplicando filtros quando os parametros forem informados
    @Query("""
        SELECT l
        FROM Loan l
        LEFT JOIN l.fine f
        WHERE (:userId IS NULL OR l.user.id = :userId)
          AND (
              :fine IS NULL
              OR (:fine = true AND f IS NOT NULL)
              OR (:fine = false AND f IS NULL)
          )
          AND (:loanStatus IS NULL OR l.loanStatus = :loanStatus)
          AND (:recordStatus IS NULL OR l.recordStatus = :recordStatus)
    """)
    List<Loan> getAllLoans(
            @Param("userId") Long userId,
            @Param("fine") Boolean fine,
            @Param("loanStatus") LoanStatus loanStatus,
            @Param("recordStatus") RecordStatus recordStatus
    );

    // Verifica se existe emprestimos pendentes para usuario buscado
    @Query("""
        SELECT COUNT(l) > 0
        FROM Loan l
        WHERE (l.user = :user)
          AND (l.loanStatus IN :loanStatus)
    """)
    boolean existsByUserAndLoanStatus(
            @Param("user") User user,
            @Param("loanStatus") List<LoanStatus> loanStatus
    );

    // Verificar se existe emprestimos pendentes para o livro buscado
    @Query("""
        SELECT COUNT(l) > 0
        FROM Loan l
        WHERE (:book MEMBER OF l.books)
          AND (l.loanStatus IN :loanStatus)
    """)
    boolean existsByBookAndLoanStatus(
            @Param("book") Book book,
            @Param("loanStatus") List<LoanStatus> loanStatus
    );

    // Encontra emprestimos feitos pelo usuario buscado
    @Query("""
        SELECT l
        FROM Loan l
        WHERE l.user.id = :userId
    """)
    List<Loan> findByUserId(@Param("userId") Long userId);

    // Verificar se usuario ja tem emprestimo ativo para determinados livros
    @Query("""
        SELECT COUNT(l) > 0
        FROM Loan l
        JOIN l.books b
        WHERE (l.user.id = :userId)
          AND (b.id = :bookId)
          AND (l.loanStatus = :loanStatus)
    """)
    boolean existsByUserIdAndBooks_IdAndLoanStatus(
            @Param("userId") Long userId,
            @Param("bookId") Long bookId,
            @Param("loanStatus") LoanStatus loanStatus
    );

    // Método que busca todos os emprestimos pendentes (scheduler)
    @Query("""
        SELECT l
        FROM Loan l
        WHERE (l.loanStatus = 'OPEN')
          AND (l.dueDate < :today)
""")
    List<Loan> findOverdueLoans(@Param("today") LocalDate today);
}