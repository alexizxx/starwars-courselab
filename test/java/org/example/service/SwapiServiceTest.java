package org.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwapiServiceTest {

    @Test
    void shouldReturnFilmInfoWhenFound() {

        SwapiService service = new SwapiService() {

            @Override
            protected String executeRequest(String url) {
                return """
                {
                  "count": 1,
                  "results": [
                    {
                      "title": "The Last Jedi",
                      "director": "Rian Johnson",
                      "producer": "Ram Bergman",
                      "release_date": "2017-12-15"
                    }
                  ]
                }
                """;
            }
        };

        String result = service.searchFilmInfo("The Last Jedi");

        assertTrue(result.contains("The Last Jedi"));
        assertTrue(result.contains("Rian Johnson"));
    }

    @Test
    void shouldReturnNotFoundWhenEmpty() {

        SwapiService service = new SwapiService() {
            @Override
            protected String executeRequest(String url) {
                return """
                {
                  "count": 0,
                  "results": []
                }
                """;
            }
        };

        String result = service.searchFilmInfo("Unknown");

        assertEquals("Films не найден", result);
    }

    @Test
    void shouldHandleException() {

        SwapiService service = new SwapiService() {
            @Override
            protected String executeRequest(String url) {
                throw new RuntimeException("Connection error");
            }
        };

        String result = service.searchFilmInfo("Error");

        assertEquals("Ошибка при получении данных", result);
    }
}