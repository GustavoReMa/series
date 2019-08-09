package com.example.series.model.entity;

public class NumberSeason {
    private int numberSeason;
    private boolean selected;

    public NumberSeason(int numberSeason, boolean selected) {
        this.numberSeason = numberSeason;
        this.selected = selected;
    }

    public int getNumberSeason() {
        return numberSeason;
    }

    public boolean isSelected() {
        return selected;
    }
}
