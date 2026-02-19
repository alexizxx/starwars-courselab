package org.example.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @Test
    void shouldValidateCharacterCorrectly() {
        assertTrue(Dictionary.isValidCharacter("Luke Skywalker"));
        assertTrue(Dictionary.isValidCharacter("luke skywalker")); // проверка регистра
        assertFalse(Dictionary.isValidCharacter("Random Guy"));
    }

    @Test
    void shouldValidatePlanetCorrectly() {
        assertTrue(Dictionary.isValidPlanet("Tatooine"));
        assertFalse(Dictionary.isValidPlanet("Earth"));
    }

    @Test
    void shouldValidateFilmCorrectly() {
        assertTrue(Dictionary.isValidFilm("The Last Jedi"));
        assertFalse(Dictionary.isValidFilm("Avengers"));
    }

    @Test
    void showFilmsShouldNotBeEmpty() {
        String films = Dictionary.showFilms();
        assertNotNull(films);
        assertFalse(films.isEmpty());
    }
}
