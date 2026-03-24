package com.heitor.app.repository;

import com.heitor.app.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

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
    """)
    List<Loan> getAllLoans(
            @Param("userId") Long userId,
            @Param("fine") Boolean fine
    );
}
