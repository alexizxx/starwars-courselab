package org.example.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetDTOTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldDeserializePlanetCorrectly() throws Exception {

        String json = """
        {
          "name": "Tatooine",
          "climate": "arid",
          "terrain": "desert",
          "population": "200000",
          "extra_field": "ignored"
        }
        """;

        PlanetDTO planet = objectMapper.readValue(json, PlanetDTO.class);

        assertEquals("Tatooine", planet.getName());
        assertEquals("arid", planet.getClimate());
        assertEquals("desert", planet.getTerrain());
        assertEquals("200000", planet.getPopulation());
    }

    @Test
    void shouldReturnCorrectToString() {

        PlanetDTO planet = new PlanetDTO();
        planet.setName("Tatooine");
        planet.setClimate("arid");
        planet.setTerrain("desert");
        planet.setPopulation("200000");

        String result = planet.toString();

        assertTrue(result.contains("Название: Tatooine"));
        assertTrue(result.contains("Климат: arid"));
        assertTrue(result.contains("Рельеф: desert"));
        assertTrue(result.contains("Население: 200000"));
    }
}