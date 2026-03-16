package com.heitor.app.service.impl;

import com.heitor.app.entity.Usuario;
import com.heitor.app.enumerate.StatusUsuario;
import com.heitor.app.exception.UserNotFoundException;
import com.heitor.app.repository.UserRepository;
import com.heitor.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Usuario> getAllUsers(String nome,
                                     String telefone,
                                     String email,
                                     LocalDate dataCadastro,
                                     StatusUsuario statusUsuario) {
        return userRepository.searchWithFilters(nome, telefone, email, dataCadastro, statusUsuario);
    }

    @Override
    public Usuario getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Usuario createUser(Usuario usuario) {
        return userRepository.save(usuario);
    }
}
