package com.reobotnet.financeiro.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora propriedades desconhecidas
public class Button {
    private String style;
    private String text;
    private String analytics;
    private String link;
    private String url;
    private String action;
}