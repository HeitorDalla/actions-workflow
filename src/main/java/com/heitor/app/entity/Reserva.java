package com.heitor.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heitor.app.enumerate.StatusReserva;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_reserva", nullable = false)
    private LocalDate dataReserva;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_reserva", nullable = false)
    private StatusReserva statusReserva;

    public Reserva() {}

    public Reserva(Long id,
                   Usuario usuario,
                   Livro livro,
                   LocalDate dataReserva,
                   StatusReserva statusReserva) {
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.dataReserva = dataReserva;
        this.statusReserva = statusReserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public StatusReserva getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(StatusReserva statusReserva) {
        this.statusReserva = statusReserva;
    }
}