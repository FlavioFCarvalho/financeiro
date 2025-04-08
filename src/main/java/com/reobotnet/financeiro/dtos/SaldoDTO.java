package com.reobotnet.financeiro.dtos;

import java.math.BigDecimal;

public class SaldoDTO {

    private BigDecimal totalCredito;
    private BigDecimal totalDebito;
    private BigDecimal saldo;

    public SaldoDTO(BigDecimal totalCredito, BigDecimal totalDebito) {
        this.totalCredito = totalCredito != null ? totalCredito : BigDecimal.ZERO;
        this.totalDebito = totalDebito != null ? totalDebito : BigDecimal.ZERO;
        this.saldo = this.totalCredito.subtract(this.totalDebito);
    }

    // Getters e setters
    public BigDecimal getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(BigDecimal totalCredito) {
        this.totalCredito = totalCredito;
    }

    public BigDecimal getTotalDebito() {
        return totalDebito;
    }

    public void setTotalDebito(BigDecimal totalDebito) {
        this.totalDebito = totalDebito;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}

