package com.heitor.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_categoria", nullable = false)
    private String nome;

    @Column(name = "descricao_categoria", nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LivroCategoria> categorias = new ArrayList<>();

    public Categoria() {}

    public Categoria(Long id,
                     String nome,
                     String descricao,
                     List<LivroCategoria> categorias) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categorias = categorias;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<LivroCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<LivroCategoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categorias=" + categorias +
                '}';
    }
}