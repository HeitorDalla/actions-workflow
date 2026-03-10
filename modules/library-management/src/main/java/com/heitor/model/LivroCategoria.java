package com.heitor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livro_categoria")
public class LivroCategoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    public LivroCategoria() {}

    public LivroCategoria(Long id,
                          Livro livro,
                          Categoria categoria) {
        this.id = id;
        this.livro = livro;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "LivroCategoria{" +
                "id=" + id +
                ", livro=" + livro +
                ", categoria=" + categoria +
                '}';
    }
}
