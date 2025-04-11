package com.reobotnet.financeiro.entities;


public class BottomSheet {
    private Button button;
    private String serviceOrderNumber;
    private String title;
    private String icon;
    private String orderText;
    private String copyIcon;

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getCopyIcon() {
        return copyIcon;
    }

    public void setCopyIcon(String copyIcon) {
        this.copyIcon = copyIcon;
    }

    public String getServiceOrderNumber() {
        return serviceOrderNumber;
    }

    public void setServiceOrderNumber(String serviceOrderNumber) {
        this.serviceOrderNumber = serviceOrderNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }
}