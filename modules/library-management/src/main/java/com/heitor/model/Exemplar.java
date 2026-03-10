package com.heitor.model;

import com.heitor.enumerate.StatusExemplar;
import jakarta.persistence.*;

@Entity
@Table(name = "exemplar")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @Column(name = "codigo_patrimonio", nullable = false, unique = true)
    private int codigo_patrimonio;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_exemplar", nullable = false)
    private StatusExemplar status;

    public Exemplar() {}

    public Exemplar(Long id,
                    Livro livro,
                    int codigo_patrimonio,
                    StatusExemplar status) {
        this.id = id;
        this.livro = livro;
        this.codigo_patrimonio = codigo_patrimonio;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getCodigo_patrimonio() {
        return codigo_patrimonio;
    }

    public void setCodigo_patrimonio(int codigo_patrimonio) {
        this.codigo_patrimonio = codigo_patrimonio;
    }

    public StatusExemplar getStatus() {
        return status;
    }

    public void setStatus(StatusExemplar status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Exemplar{" +
                "id=" + id +
                ", livro=" + livro +
                ", codigo_patrimonio=" + codigo_patrimonio +
                ", status=" + status +
                '}';
    }
}