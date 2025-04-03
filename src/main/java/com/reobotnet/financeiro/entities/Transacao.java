package com.reobotnet.financeiro.entities;

import com.reobotnet.financeiro.enuns.MeioPagamento;
import com.reobotnet.financeiro.enuns.TipoTransacao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @ManyToOne(optional = false) // Relacionamento ManyToOne com Categoria
    @JoinColumn(name = "categoria_id", nullable = false) // Define a coluna de chave estrangeira
    private Categoria categoria;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false, length = 255) // Campo descrição com limite de 255 caracteres
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20) // Campo para o meio de pagamento
    private MeioPagamento meioPagamento;

    // Construtor padrão
    public Transacao() {
    }

    // Construtor com todos os argumentos
    public Transacao(Long id, BigDecimal valor, TipoTransacao tipo, Categoria categoria, LocalDateTime data, String descricao, MeioPagamento meioPagamento) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.data = data;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
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

    // Implementação de equals e hashCode
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