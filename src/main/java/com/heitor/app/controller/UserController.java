package com.heitor.app.controller;

import com.heitor.app.entity.Usuario;
import com.heitor.app.enumerate.StatusUsuario;
import com.heitor.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Usuario> getAllUsers(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String telefone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate dataCadastro,
            @RequestParam(required = false) StatusUsuario statusUsuario) {

        List<Usuario> users = userService.getAllUsers(nome, telefone, email, dataCadastro, statusUsuario);

        LOGGER.info("Usuarios buscados com sucesso: {}", users);

        return users;
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Long id) { // pega a variavel da URL (/users/10)
        Usuario usuario = userService.getUserById(id);

        LOGGER.info("Usuario buscado com sucesso: {}", usuario);

        return usuario;
    }

    @PostMapping
    public Usuario createUser(@RequestBody Usuario usuario) { // pega o BODY da requisicao
        Usuario saldo = userService.createUser(usuario);

        LOGGER.info("Usuario salvo com sucesso: {}", saldo);

        return saldo;
    }
}
