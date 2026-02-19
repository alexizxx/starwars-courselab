package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO-класс для персонажа SWAPI.
 * <p>
 * Используется Jackson для десериализации JSON.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO {

    private String name;

    @JsonProperty("height")
    private String height;

    @JsonProperty("mass")
    private String mass;

    @JsonProperty("hair_color")
    private String hairColor;

    @JsonProperty("skin_color")
    private String skinColor;

    @JsonProperty("eye_color")
    private String eyeColor;

    @JsonProperty("birth_year")
    private String birthYear;

    private String gender;

    @JsonProperty("homeworld")
    private String homeworld; // может быть URL, для расширения можно запросить название планеты

    // ===== Геттеры и сеттеры =====
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHeight() { return height; }
    public void setHeight(String height) { this.height = height; }

    public String getMass() { return mass; }
    public void setMass(String mass) { this.mass = mass; }

    public String getHairColor() { return hairColor; }
    public void setHairColor(String hairColor) { this.hairColor = hairColor; }

    public String getSkinColor() { return skinColor; }
    public void setSkinColor(String skinColor) { this.skinColor = skinColor; }

    public String getEyeColor() { return eyeColor; }
    public void setEyeColor(String eyeColor) { this.eyeColor = eyeColor; }

    public String getBirthYear() { return birthYear; }
    public void setBirthYear(String birthYear) { this.birthYear = birthYear; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getHomeworld() { return homeworld; }
    public void setHomeworld(String homeworld) { this.homeworld = homeworld; }

    @Override
    public String toString() {
        return "Имя: " + name +
               "\nРост: " + height +
               "\nВес: " + mass +
               "\nЦвет волос: " + hairColor +
               "\nЦвет кожи: " + skinColor +
               "\nЦвет глаз: " + eyeColor +
               "\nГод рождения: " + birthYear +
               "\nПол: " + gender;
    }
}
