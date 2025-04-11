package com.reobotnet.financeiro.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reobotnet.financeiro.entities.Dados;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDetails {

    @JsonProperty("data")
    private Dados dados;

    // Getters and Setters
    public Dados getDados() {
        return dados;
    }

    public void setDados(Dados dados) {
        this.dados = dados;
    }
}
