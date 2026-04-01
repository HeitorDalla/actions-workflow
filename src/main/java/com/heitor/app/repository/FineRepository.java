package com.heitor.app.repository;

import com.heitor.app.entity.Fine;
import com.heitor.app.entity.User;
import com.heitor.app.enums.FineStatus;
import com.heitor.app.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {
    // Busca multas, aplicando filtros quando os parametros forem informados
    @Query("""
        SELECT f
        FROM Fine f
        WHERE (:amount IS NULL OR f.amount = :amount)
          AND (:fineStatus IS NULL OR f.fineStatus = :fineStatus)
          AND (:recordStatus IS NULL OR f.recordStatus = :recordStatus)
    """)
    List<Fine> getAllFines(
            @Param("amount") BigDecimal amount,
            @Param("fineStatus") FineStatus fineStatus,
            @Param("recordStatus") RecordStatus recordStatus
    );

    // Vai verificar se tem alguma multa no usuario buscado
    @Query("""
        SELECT COUNT(f) > 0
        FROM Fine f
        WHERE f.loan.user = :user
            AND f.fineStatus = :fineStatus
    """)
    boolean existsByLoanUserAndFineStatus(
            @Param("user") User user,
            @Param("fineStatus") FineStatus fineStatus
    );
}
