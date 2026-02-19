package org.example.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDTOTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldDeserializePersonCorrectly() throws Exception {

        String json = """
        {
          "name": "Luke Skywalker",
          "height": "172",
          "mass": "77",
          "hair_color": "blond",
          "skin_color": "fair",
          "eye_color": "blue",
          "birth_year": "19BBY",
          "gender": "male",
          "homeworld": "https://swapi.dev/api/planets/1/",
          "unknown_field": "ignored"
        }
        """;

        PersonDTO person = objectMapper.readValue(json, PersonDTO.class);

        assertEquals("Luke Skywalker", person.getName());
        assertEquals("172", person.getHeight());
        assertEquals("77", person.getMass());
        assertEquals("blond", person.getHairColor());
        assertEquals("fair", person.getSkinColor());
        assertEquals("blue", person.getEyeColor());
        assertEquals("19BBY", person.getBirthYear());
        assertEquals("male", person.getGender());
        assertEquals("https://swapi.dev/api/planets/1/", person.getHomeworld());
    }

    @Test
    void shouldReturnCorrectToString() {

        PersonDTO person = new PersonDTO();
        person.setName("Luke Skywalker");
        person.setHeight("172");
        person.setMass("77");
        person.setHairColor("blond");
        person.setSkinColor("fair");
        person.setEyeColor("blue");
        person.setBirthYear("19BBY");
        person.setGender("male");

        String result = person.toString();

        assertTrue(result.contains("Имя: Luke Skywalker"));
        assertTrue(result.contains("Рост: 172"));
        assertTrue(result.contains("Вес: 77"));
        assertTrue(result.contains("Цвет волос: blond"));
        assertTrue(result.contains("Год рождения: 19BBY"));
    }
}