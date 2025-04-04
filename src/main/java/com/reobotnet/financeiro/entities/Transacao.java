package com.reobotnet.financeiro.entities;

import com.reobotnet.financeiro.enuns.MeioPagamento;
import com.reobotnet.financeiro.enuns.TipoTransacao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TipoTransacao tipo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private LocalDate dataLancamento;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MeioPagamento meioPagamento;

    // Construtor padrão
    public Transacao() {
    }

    // Construtor com todos os argumentos
    public Transacao(Long id, BigDecimal valor, TipoTransacao tipo, Categoria categoria,
                     LocalDate dataLancamento, LocalDate dataVencimento,
                     String descricao, MeioPagamento meioPagamento) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.dataLancamento = dataLancamento;
        this.dataVencimento = dataVencimento;
        this.descricao = descricao;
        this.meioPagamento = meioPagamento;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MeioPagamento getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(MeioPagamento meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    @PrePersist
    protected void onCreate() {
        if (dataLancamento == null) {
            dataLancamento = LocalDate.now();
        }
    }

    // equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(id, transacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
