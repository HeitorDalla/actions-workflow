package com.heitor.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "itemEmprestimo")
public class ItemEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_emprestimo", nullable = false)
    private Emprestimo emprestimo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_exemplar", nullable = false)
    private Exemplar exemplar;

    @Column(name = "data_devolucao_item_emprestimo", nullable = false)
    private LocalDate dataDevolucao;

    public ItemEmprestimo() {}

    public ItemEmprestimo(Long id,
                          Emprestimo emprestimo,
                          Exemplar exemplar,
                          LocalDate dataDevolucao) {
        this.id = id;
        this.emprestimo = emprestimo;
        this.exemplar = exemplar;
        this.dataDevolucao = dataDevolucao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "ItemEmprestimo{" +
                "id=" + id +
                ", emprestimo=" + emprestimo +
                ", exemplar=" + exemplar +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}