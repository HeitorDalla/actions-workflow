package com.heitor.app.repository;

import com.heitor.app.entity.Fine;
import com.heitor.app.entity.Loan;
import com.heitor.app.entity.User;
import com.heitor.app.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("""
        SELECT l
        FROM Loan l
        WHERE (:loanDate IS NULL OR l.loanDate = :loanDate)
            AND (:returnDate IS NULL OR l.returnDate = :returnDate)
            AND (:loanStatus IS NULL OR l.loanStatus = :loanStatus)
            AND (:user IS NULL OR l.user = :user)
            AND (:fine IS NULL OR l.fine = :fine)
    """)
    List<Loan> getAllLoans(
            @Param("loanDate") LocalDate loanDate,
            @Param("returnDate") LocalDate returnDate,
            @Param("loanStatus") LoanStatus loanStatus,
            @Param("user") User user,
            @Param("fine") Fine fine
    );
}
