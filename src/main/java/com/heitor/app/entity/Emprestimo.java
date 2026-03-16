package com.heitor.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heitor.app.enumerate.StatusEmprestimo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_devolucao_emprestimo")
    private LocalDate dataDevolucao;

//    @Transient // anotacao para fazer o JPA ignorar a persistencia no banco de dados, mas util para regras de negoico
//    private long diasAtraso;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_emprestimo", nullable = false)
    private StatusEmprestimo statusEmprestimo;

    @OneToMany(mappedBy = "emprestimo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEmprestimo> itens = new ArrayList<>();

    @OneToOne(mappedBy = "emprestimo", fetch = FetchType.LAZY)
    private Multa multa;

    public Emprestimo() {}

    public Emprestimo(Long id,
                      Usuario usuario,
                      LocalDate dataEmprestimo,
                      LocalDate dataDevolucao,
                      long diasAtraso,
                      StatusEmprestimo statusEmprestimo,
                      List<ItemEmprestimo> itens,
                      Multa multa) {
        this.id = id;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.statusEmprestimo = statusEmprestimo;
        this.itens = itens;
        this.multa = multa;
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

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public StatusEmprestimo getStatusEmprestimo() {
        return statusEmprestimo;
    }

    public void setStatusEmprestimo(StatusEmprestimo statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }

    public List<ItemEmprestimo> getItens() {
        return itens;
    }

    public void setItens(List<ItemEmprestimo> itens) {
        this.itens = itens;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }
}