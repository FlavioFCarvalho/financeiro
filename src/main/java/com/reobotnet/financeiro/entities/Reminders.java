package com.reobotnet.financeiro.entities;

import java.util.List;

public class Reminders {
    private String title;
    private List<ReminderItemView> itemViews;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ReminderItemView> getItemViews() {
        return itemViews;
    }

    public void setItemViews(List<ReminderItemView> itemViews) {
        this.itemViews = itemViews;
    }
}
