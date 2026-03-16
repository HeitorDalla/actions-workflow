package com.heitor.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heitor.app.enumerate.StatusUsuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
// @Cacheable  // anotacao para a implementacao do cache nivel 2 do hibernate
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_usuario", nullable = false)
    private String nome;

    @Column(name = "telefone_usuario", nullable = false)
    private String telefone;

    @Column(name = "email_usuario", nullable = false, unique = true)
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_cadastro_usuario", nullable = false)
    private LocalDate dataCadastro;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_usuario", nullable = false)
    private StatusUsuario statusUsuario;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Emprestimo> emprestimos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Reserva> reservas = new ArrayList<>();

    public Usuario() {}

    public Usuario(Long id,
                   String nome,
                   String telefone,
                   String email,
                   StatusUsuario statusUsuario,
                   LocalDate dataCadastro,
                   List<Emprestimo> emprestimos,
                   List<Reserva> reservas) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.statusUsuario = statusUsuario;
        this.dataCadastro = dataCadastro;
        this.emprestimos = emprestimos;
        this.reservas = reservas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusUsuario getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(StatusUsuario statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}