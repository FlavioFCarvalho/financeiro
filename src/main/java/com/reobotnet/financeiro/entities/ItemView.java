package com.reobotnet.financeiro.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemView {
    private String icon;
    private String title;
    private String description;
    private BottomSheet bottomSheet;

}
