package com.example.series.model.entity;

public class SerieDetailExtras {

    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String Poster;
    private Rating[] Ratings;
    private String Metascore;
    private String imdbRating;
    private String imdbID;
    private String Type;
    private String totalSeasons;
    private String Response;

    public SerieDetailExtras(String title, String year, String rated, String released, String runtime, String genre, String director, String actors, String plot, String language, String country, String awards, String poster, Rating[] ratings, String metascore, String imdbRating, String imdbID, String type, String totalSeasons, String response) {
        Title = title;
        Year = year;
        Rated = rated;
        Released = released;
        Runtime = runtime;
        Genre = genre;
        Director = director;
        Actors = actors;
        Plot = plot;
        Language = language;
        Country = country;
        Awards = awards;
        Poster = poster;
        Ratings = ratings;
        Metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
        Type = type;
        this.totalSeasons = totalSeasons;
        Response = response;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public String getPoster() {
        return Poster;
    }

    public Rating[] getRatings() {
        return Ratings;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getTotalSeasons() {
        return totalSeasons;
    }

    public String getResponse() {
        return Response;
    }
}

class Rating {
    private String Source;
    private String Value;

    public Rating(String source, String value) {
        Source = source;
        Value = value;
    }

    public String getSource() {
        return Source;
    }

    public String getValue() {
        return Value;
    }
}
