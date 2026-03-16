package com.heitor.app.repository;

import com.heitor.app.entity.Usuario;
import com.heitor.app.enumerate.StatusUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    @Query("""
    SELECT u
    FROM Usuario u
    WHERE (:nome IS NULL OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
      AND (:telefone IS NULL OR u.telefone = :telefone)
      AND (:email IS NULL OR u.email = LOWER(:email))
      AND (:dataCadastro IS NULL OR u.dataCadastro = :dataCadastro)
      AND (:statusUsuario IS NULL OR u.statusUsuario = :statusUsuario)
    """)
    List<Usuario> searchWithFilters(
            @Param("nome") String nome,
            @Param("telefone") String telefone,
            @Param("email") String email,
            @Param("dataCadastro") LocalDate dataCadastro,
            @Param("statusUsuario") StatusUsuario statusUsuario);
}
