package com.heitor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livro_autor")
public class LivroAutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    public LivroAutor() {}

    public LivroAutor(Long id,
                      Livro livro,
                      Autor autor) {
        this.id = id;
        this.livro = livro;
        this.autor = autor;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "LivroAutor{" +
                "id=" + id +
                ", livro=" + livro +
                ", autor=" + autor +
                '}';
    }
}