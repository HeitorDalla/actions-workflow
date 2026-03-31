package com.heitor.app.repository;

import com.heitor.app.entity.User;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Busca usuarios, aplicando filtros quando os parametros forem informados
    @Query("""
        SELECT u
        FROM User u
        WHERE (:name IS NULL OR u.name = :name)
          AND (:number IS NULL OR u.number = :number)
          AND (:email IS NULL OR :email = '' OR LOWER(u.email) = LOWER(:email))
          AND (:userStatus IS NULL OR u.userStatus = :userStatus)
          AND (:recordStatus IS NULL OR u.recordStatus = :recordStatus)
    """)
    List<User> getAllUsers(
            @Param("name") String name,
            @Param("number") String number,
            @Param("email") String email,
            @Param("userStatus") UserStatus userStatus,
            @Param("recordStatus") RecordStatus recordStatus
    );
}
