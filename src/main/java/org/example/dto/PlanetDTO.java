package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO-класс для планет SWAPI.
 * <p>
 * Используется Jackson для десериализации JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetDTO {
    private String name;
    private String climate;
    private String terrain;
    private String population;

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getClimate() { return climate; }
    public void setClimate(String climate) { this.climate = climate; }

    public String getTerrain() { return terrain; }
    public void setTerrain(String terrain) { this.terrain = terrain; }

    public String getPopulation() { return population; }
    public void setPopulation(String population) { this.population = population; }

    @Override
    public String toString() {
        return "Название: " + name + "\nКлимат: " + climate + "\nРельеф: " + terrain + "\nНаселение: " + population;
    }
}
