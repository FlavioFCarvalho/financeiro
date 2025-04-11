package com.reobotnet.financeiro.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReminderItemView {

    private String title;
    private String icon;
    private ItemView itemView;
    private Object bottomSheet;


}

