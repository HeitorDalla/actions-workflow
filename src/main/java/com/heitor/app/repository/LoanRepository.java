package com.heitor.app.repository;

import com.heitor.app.entity.Loan;
import com.heitor.app.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
    """)
    List<Loan> getAllLoans(
            @Param("userId") Long userId,
            @Param("fine") Boolean fine,
            @Param("loanStatus") LoanStatus loanStatus
    );

    // Encontra emprestimos feitos pelo usuario buscado
    @Query("""
        SELECT l
        FROM Loan l
        WHERE l.user.id = :userId
    """)
    List<Loan> findByUserId(@Param("userId") Long userId);
}