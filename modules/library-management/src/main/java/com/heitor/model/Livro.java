package com.heitor.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo_livro", nullable = false)
    private String titulo;

    @Column(name = "ano_publicacao_livro", nullable = false)
    private int anoPublicacao;

    @Column(name = "idioma_livro", nullable = false)
    private String idioma;

    @Column(name = "data_cadastro_livro", nullable = false)
    private LocalDate dataCadastro;

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LivroCategoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LivroAutor> autores = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_editora", nullable = false)
    private Editora editora;

    public Livro() {}

    public Livro(Long id,
                 String titulo,
                 int anoPublicacao,
                 String idioma,
                 LocalDate dataCadastro,
                 List<LivroCategoria> categorias,
                 List<LivroAutor> autores,
                 Editora editora) {
        this.id = id;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.idioma = idioma;
        this.dataCadastro = dataCadastro;
        this.categorias = categorias;
        this.autores = autores;
        this.editora = editora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<LivroCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<LivroCategoria> categorias) {
        this.categorias = categorias;
    }

    public List<LivroAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<LivroAutor> autores) {
        this.autores = autores;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", idioma='" + idioma + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", categorias=" + categorias +
                ", autores=" + autores +
                ", editora=" + editora +
                '}';
    }
}