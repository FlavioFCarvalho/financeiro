package com.reobotnet.financeiro.dtos;

import java.math.BigDecimal;

public class CategoriaResumoDTO {

    private String categoria;
    private BigDecimal total;

    public CategoriaResumoDTO() {
    }

    public CategoriaResumoDTO(String categoria, BigDecimal total) {
        this.categoria = categoria;
        this.total = total;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
