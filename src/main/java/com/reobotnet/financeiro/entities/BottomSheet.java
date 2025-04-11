package com.reobotnet.financeiro.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BottomSheet {
    private Button button;
    private String serviceOrderNumber;
    private String title;
    private String icon;
    private String orderText;
    private String copyIcon;
}