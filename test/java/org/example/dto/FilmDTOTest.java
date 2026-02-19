package org.example.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmDTOTest {

    @Test
    void shouldDeserializeFilmCorrectly() throws Exception {

        String json = """
        {
            "title": "The Last Jedi",
            "director": "Rian Johnson",
            "producer": "Ram Bergman",
            "release_date": "2017-12-15"
        }
        """;

        ObjectMapper mapper = new ObjectMapper();
        FilmDTO film = mapper.readValue(json, FilmDTO.class);

        assertEquals("The Last Jedi", film.getTitle());
        assertEquals("Rian Johnson", film.getDirector());
        assertEquals("Ram Bergman", film.getProducer());
        assertEquals("2017-12-15", film.getReleaseDate());
    }
}
