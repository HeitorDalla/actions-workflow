package com.heitor.model;

import com.heitor.enumerate.StatusEmprestimo;
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

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao_emprestimo", nullable = false)
    private LocalDate dataDevolucao;

    @Transient // anotacao para fazer o JPA ignorar a persistencia no banco de dados, mas util para regras de negoico
    private long diasAtraso;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_emprestimo", nullable = false)
    private StatusEmprestimo statusEmprestimo;

    @OneToMany(mappedBy = "emprestimo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEmprestimo> itens = new ArrayList<>();

    public Emprestimo() {}

    public Emprestimo(Long id,
                      Usuario usuario,
                      LocalDate dataEmprestimo,
                      LocalDate dataDevolucao,
                      long diasAtraso,
                      StatusEmprestimo statusEmprestimo,
                      List<ItemEmprestimo> itens) {
        this.id = id;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.diasAtraso = diasAtraso;
        this.statusEmprestimo = statusEmprestimo;
        this.itens = itens;
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

    public long getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(long diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public StatusEmprestimo getStatusEmprestimo() {
        return statusEmprestimo;
    }

    public void setStatusEmprestimo(StatusEmprestimo statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }

    public List<ItemEmprestimo> getItemEmprestimo() {
        return itens;
    }

    public void setItemEmprestimo(List<ItemEmprestimo> itemEmprestimo) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", diasAtraso=" + diasAtraso +
                ", statusEmprestimo=" + statusEmprestimo +
                ", itemEmprestimo=" + itens +
                '}';
    }
}