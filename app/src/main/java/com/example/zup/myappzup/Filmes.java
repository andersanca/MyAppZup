package com.example.zup.myappzup;

/**
 * Created by Anderson Silva on 01/02/2017.
 */

public class Filmes {

    private int id_filme;
    private String title;
    private String year;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String imdb_rating;

    public static final String  TABLE_FILMES = "filmes";

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_YEAR = "year";
    public static final String KEY_RELEASED = "released";
    public static final String KEY_RUNTIME = "runtime";
    public static final String KEY_GENRE = "genre";
    public static final String KEY_DIRECTOR = "director";
    public static final String KEY_WRITER = "whiter";
    public static final String KEY_ACTORS = "actors";
    public static final String KEY_PLOT = "plot";
    public static final String KEY_LANGUAGE = "language";
    public static final String KEY_CONTRY = "contry";
    public static final String KEY_AWARDS = "awards";
    public static final String KEY_POSTER = "poster";
    public static final String KEY_IMDB_RATING = "imdb_rating";


    public static final String CREATE_TABLE =
            "CREATE TABLE filmes ("
                    + KEY_ID    +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_TITLE +" VARCHAR,"
                    + KEY_YEAR +" VARCHAR,"
                    + KEY_RELEASED +" VARCHAR,"
                    + KEY_RUNTIME + " VARCHAR,"
                    + KEY_GENRE +" VARCHAR,"
                    + KEY_DIRECTOR +" VARCHAR,"
                    + KEY_WRITER +" VARCHAR,"
                    + KEY_ACTORS +" VARCHAR,"
                    + KEY_PLOT +" VARCHAR,"
                    + KEY_LANGUAGE +" VARCHAR,"
                    + KEY_CONTRY +"  VARCHAR,"
                    + KEY_AWARDS +" VARCHAR,"
                    + KEY_POSTER +"  VARCHAR,"
                    + KEY_IMDB_RATING +" VARCHAR"
                    + ");";

    public int getId_filme() {
        return id_filme;
    }

    public void setId_filme(int id_filme) {
        this.id_filme = id_filme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImdb_rating() {
        return imdb_rating;
    }

    public void setImdb_rating(String imdb_rating) {
        this.imdb_rating = imdb_rating;
    }










}
