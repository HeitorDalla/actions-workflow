package com.heitor.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "autor_livro", nullable = false, length = 150)
    private String autor;

    @Column(name = "isbn_livro", nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(name = "ano_publicacao_livro", nullable = false)
    private int anoPublicacao;

    @Column(name = "idioma_livro", nullable = false)
    private String idioma;

    @Column(name = "quantidade_total_livro", nullable = false)
    private Integer quantidadeTotal;

    @Column(name = "quantidade_disponivel_livro", nullable = false)
    private Integer quantidadeDisponivel;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_cadastro_livro", nullable = false)
    private LocalDate dataCadastro;

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
    private List<ItemEmprestimo> itensEmprestimo = new ArrayList<>();

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
    private List<Reserva> reservas = new ArrayList<>();

    public Livro() {}

    public Livro(Long id,
                 String titulo,
                 String autor,
                 String isbn,
                 int anoPublicacao,
                 String idioma,
                 Integer quantidadeTotal,
                 Integer quantidadeDisponivel,
                 LocalDate dataCadastro,
                 List<ItemEmprestimo> itensEmprestimo,
                 List<Reserva> reservas) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.idioma = idioma;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.dataCadastro = dataCadastro;
        this.itensEmprestimo = itensEmprestimo;
        this.reservas = reservas;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<ItemEmprestimo> getItensEmprestimo() {
        return itensEmprestimo;
    }

    public void setItensEmprestimo(List<ItemEmprestimo> itensEmprestimo) {
        this.itensEmprestimo = itensEmprestimo;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}