package com.reobotnet.financeiro.entities;

public class ReminderItemView {

    private String title;
    private String icon;
    private ItemView itemView;
    private Object bottomSheet;

    public Object getBottomSheet() {
        return bottomSheet;
    }

    public void setBottomSheet(Object bottomSheet) {
        this.bottomSheet = bottomSheet;
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

    public ItemView getItemView() {
        return itemView;
    }

    public void setItemView(ItemView itemView) {
        this.itemView = itemView;
    }
}

