package com.reobotnet.financeiro.entities;

public class ItemView {
    private String icon;
    private String title;
    private String description;
    private BottomSheet bottomSheet;

    public BottomSheet getBottomSheet() {
        return bottomSheet;
    }

    public void setBottomSheet(BottomSheet bottomSheet) {
        this.bottomSheet = bottomSheet;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
