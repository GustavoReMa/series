package com.example.series.model.entity;

public class SerieDetail {
    private int id;
    private String seriesName;
    private String banner;
    private String seriesId;
    private String firstAired;
    private String runtime;
    private String[] genre;
    private String overview;
    private String airsDayOfWeek;
    private String airsTime;
    private String rating;
    private String imdbId;
    private int siteRatingCount;

    public SerieDetail(int id, String seriesName, String banner, String seriesId, String firstAired, String runtime, String[] genre, String overview, String airsDayOfWeek, String airsTime, String rating, String imdbId, int siteRatingCount) {
        this.id = id;
        this.seriesName = seriesName;
        this.banner = banner;
        this.seriesId = seriesId;
        this.firstAired = firstAired;
        this.runtime = runtime;
        this.genre = genre;
        this.overview = overview;
        this.airsDayOfWeek = airsDayOfWeek;
        this.airsTime = airsTime;
        this.rating = rating;
        this.imdbId = imdbId;
        this.siteRatingCount = siteRatingCount;
    }

    public int getId() {
        return id;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public String getBanner() {
        return banner;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public String getRuntime() {
        return runtime;
    }

    public String[] getGenre() {
        return genre;
    }

    public String getOverview() {
        return overview;
    }

    public String getAirsDayOfWeek() {
        return airsDayOfWeek;
    }

    public String getAirsTime() {
        return airsTime;
    }

    public String getRating() {
        return rating;
    }

    public String getImdbId() {
        return imdbId;
    }

    public int getSiteRatingCount() {
        return siteRatingCount;
    }
}
