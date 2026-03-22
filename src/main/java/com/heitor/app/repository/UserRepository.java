package com.heitor.app.repository;

import com.heitor.app.entity.User;
import com.heitor.app.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
    SELECT u
    FROM User u
    WHERE (:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%')))
      AND (:number IS NULL OR u.number = :number)
      AND (:email IS NULL OR u.email = LOWER(:email))
      AND (:registrationDate IS NULL OR u.registrationDate = :registrationDate)
      AND (:userStatus IS NULL OR u.userStatus = :userStatus)
    """)
    List<User> getAllUsers(
            @Param("name") String name,
            @Param("number") String number,
            @Param("email") String email,
            @Param("registrationDate") LocalDate registrationDate,
            @Param("userStatus") UserStatus userStatus);
}
