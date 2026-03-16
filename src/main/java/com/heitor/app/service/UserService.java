package com.heitor.app.service;

import com.heitor.app.entity.Usuario;
import com.heitor.app.enumerate.StatusUsuario;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<Usuario> getAllUsers(String nome, String telefone, String email, LocalDate dataCadastro, StatusUsuario statusUsuario);

    Usuario getUserById(Long id);

    Usuario createUser(Usuario endereco);
}
