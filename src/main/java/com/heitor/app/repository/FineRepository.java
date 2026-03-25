package com.heitor.app.repository;

import com.heitor.app.entity.Fine;
import com.heitor.app.enums.FineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {
    @Query("""
        SELECT f
        FROM Fine f
        WHERE (:amount IS NULL OR LOWER(:amount) LIKE LOWER(CONCAT('%', :amount, '%')))
            AND (:fineStatus IS NULL OR f.fineStatus = :fineStatus)
    """)
    List<Fine> getAllFines(
            @Param("amount") BigDecimal amount,
            @Param("fineStatus") FineStatus fineStatus
    );
}
