package com.reobotnet.financeiro.dtos;


import com.reobotnet.financeiro.enuns.MeioPagamento;
import com.reobotnet.financeiro.enuns.TipoTransacao;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoDTO {

    private Long id;
    private BigDecimal valor;
    private TipoTransacao tipo;
    private Long categoriaId;
    private LocalDate dataLancamento;
    private LocalDate dataVencimento;
    private String descricao;
    private MeioPagamento meioPagamento;

    public TransacaoDTO() {
    }

    public TransacaoDTO(Long id, BigDecimal valor, TipoTransacao tipo, Long categoriaId,
                        LocalDate dataLancamento, LocalDate dataVencimento, String descricao,
                        MeioPagamento meioPagamento) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.categoriaId = categoriaId;
        this.dataLancamento = dataLancamento;
        this.dataVencimento = dataVencimento;
        this.descricao = descricao;
        this.meioPagamento = meioPagamento;
    }

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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
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
}
