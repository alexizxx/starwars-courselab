package org.example.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Класс словаря доступных персонажей, планет и фильмов.
 * <p>
 * Содержит методы проверки валидности введённых значений
 * и методы получения отсортированных списков.
 */
public class Dictionary {

    private static final List<String> CHARACTERS = Arrays.asList(
        "Luke Skywalker", "Darth Vader", "Leia Organa", "Yoda", "Han Solo",
        "Chewbacca", "Obi-Wan Kenobi", "R2-D2", "C-3PO", "Palpatine",
        "Boba Fett", "Lando Calrissian", "Anakin Skywalker", "Padmé Amidala", "Qui-Gon Jinn"
    );

    private static final List<String> PLANETS = Arrays.asList(
        "Tatooine", "Dagobah", "Naboo", "Coruscant", "Hoth",
        "Endor", "Bespin", "Kamino", "Geonosis", "Mustafar"
    );

    private static final List<String> FILMS = Arrays.asList(
        "A New Hope", "The Empire Strikes Back", "Return of the Jedi",
        "The Phantom Menace", "Attack of the Clones", "Revenge of the Sith"
    );

    // ===== Проверка валидности =====

    /**
     * Проверяет, существует ли фильм в словаре.
     * @param name имя персонажа
     * @return true, если персонаж найден
     */
    public static boolean isValidCharacter(String name) {
        String trimmed = name.trim();
        return CHARACTERS.stream().anyMatch(c -> c.equalsIgnoreCase(trimmed));
    }


    /**
     * Проверяет, существует ли фильм в словаре.
     * @param name название планеты
     * @return true, если планета найдена
     */
    public static boolean isValidPlanet(String name) {
        String trimmed = name.trim();
        return PLANETS.stream().anyMatch(p -> p.equalsIgnoreCase(trimmed));
    }

    /**
     * Проверяет, существует ли фильм в словаре.
     * @param name название фильма
     * @return true, если фильм найден
     */
    public static boolean isValidFilm(String name) {
        String trimmed = name.trim();
        return FILMS.stream().anyMatch(f -> f.equalsIgnoreCase(trimmed));
    }

    // ===== Вывод списков =====

    /** @return Список персонажей в виде строки */
    public static String showCharacters() {
        return CHARACTERS.stream().sorted().collect(Collectors.joining("\n"));
    }

    /** @return Список планет в виде строки */
    public static String showPlanets() {
        return PLANETS.stream().sorted().collect(Collectors.joining("\n"));
    }

    /** @return Список фильмов в виде строки */
    public static String showFilms() {
        return FILMS.stream().sorted().collect(Collectors.joining("\n"));
    }
}
