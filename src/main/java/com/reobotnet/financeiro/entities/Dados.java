package com.reobotnet.financeiro.entities;

public class Dados {
    private Header header;
    private BottomSheet bottomSheet;
    private InfoCard infoCard;
    private Reminders reminders;
    private Object extras;

    public Object getExtras() {
        return extras;
    }

    public void setExtras(Object extras) {
        this.extras = extras;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public BottomSheet getBottomSheet() {
        return bottomSheet;
    }

    public void setBottomSheet(BottomSheet bottomSheet) {
        this.bottomSheet = bottomSheet;
    }

    public InfoCard getInfoCard() {
        return infoCard;
    }

    public void setInfoCard(InfoCard infoCard) {
        this.infoCard = infoCard;
    }

    public Reminders getReminders() {
        return reminders;
    }

    public void setReminders(Reminders reminders) {
        this.reminders = reminders;
    }
}
