package br.com.screenMatchWSpring.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer season;
    private String title;
    private Integer episodeNumber;
    private double imdbRating;
    private LocalDate releasedData;

    public Episode(Integer seasonNumber, EpisodeData episodeData) {
        this.season = seasonNumber;
        this.title = episodeData.title();
        this.episodeNumber = episodeData.episode();
        try {
            this.imdbRating = Double.parseDouble(episodeData.imdbRating());
        } catch (NumberFormatException ex) {
            this.imdbRating = 0.0;
        }

        try {
            this.releasedData = LocalDate.parse(episodeData.releasedData());
        } catch (DateTimeParseException ex) {
            this.releasedData = null;
        }

    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public LocalDate getReleasedData() {
        return releasedData;
    }

    public void setReleasedData(LocalDate releasedData) {
        this.releasedData = releasedData;
    }

    @Override
    public String toString() {
        return "season=" + season +
                ", title='" + title + '\'' +
                ", episodeNumber=" + episodeNumber +
                ", imdbRating=" + imdbRating +
                ", releasedData=" + releasedData;
    }
}
