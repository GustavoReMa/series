package com.example.series.model.entity;

public class Serie {
    private String banner;

    private String firstAired;

    private int id;

    private String overview;

    private String seriesName;

    public Serie(String banner, String firstAired, int id, String overview, String seriesName) {
        this.banner = banner;
        this.firstAired = firstAired;
        this.id = id;
        this.overview = overview;
        this.seriesName = seriesName;
    }

    public String getBanner() {
        return banner;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getSeriesName() {
        return seriesName;
    }
}
