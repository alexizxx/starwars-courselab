package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO-класс для фильмов SWAPI.
 * <p>
 * Используется Jackson для десериализации JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmDTO {

    private String title;

    private String director;

    private String producer;

    @JsonProperty("release_date")
    private String releaseDate; 

    // ===== Геттеры и сеттеры =====
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getProducer() { return producer; }
    public void setProducer(String producer) { this.producer = producer; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    @Override
    public String toString() {
        return "Название: " + title +
               "\nРежиссер: " + director +
               "\nПродюсер: " + producer +
               "\nДата выхода: " + releaseDate;
    }
}
