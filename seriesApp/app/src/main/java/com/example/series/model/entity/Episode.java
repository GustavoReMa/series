package com.example.series.model.entity;

public class Episode {
    private String filename;
    private String episodeName;
    private String overview;
    private String firstAired;
    private String siteRating;
    private int airedSeason;

    public Episode(String filename, String episodeName, String overview, String firstAired, String siteRating, int airedSeason) {
        this.filename = filename;
        this.episodeName = episodeName;
        this.overview = overview;
        this.firstAired = firstAired;
        this.siteRating = siteRating;
        this.airedSeason = airedSeason;
    }

    public String getFilename() {
        return filename;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public String getOverview() {
        return overview;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public String getSiteRating() {
        return siteRating;
    }

    public int getAiredSeason() {
        return airedSeason;
    }
}
