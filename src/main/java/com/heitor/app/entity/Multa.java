package com.heitor.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heitor.app.enumerate.StatusMulta;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "multa")
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_emprestimo", nullable = false)
    private Emprestimo emprestimo;

    @Column(name = "valor_multa", nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_multa", nullable = false)
    private StatusMulta status;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_geracao_multa", nullable = false)
    private LocalDate dataGeracao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_pagamento_multa")
    private LocalDate dataPagamento;

    public Multa() {}

    public Multa(Long id,
                 Emprestimo emprestimo,
                 BigDecimal valor,
                 StatusMulta status,
                 LocalDate dataGeracao,
                 LocalDate dataPagamento) {
        this.id = id;
        this.emprestimo = emprestimo;
        this.valor = valor;
        this.status = status;
        this.dataGeracao = dataGeracao;
        this.dataPagamento = dataPagamento;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public StatusMulta getStatus() {
        return status;
    }

    public void setStatus(StatusMulta status) {
        this.status = status;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}