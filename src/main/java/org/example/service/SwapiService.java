package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.*;


/**
 * Сервис для взаимодействия с API SWAPI.
 * <p>
 * Предоставляет методы для поиска персонажей, планет и фильмов
 * и возвращает информацию в формате строк для отображения.
 */
public class SwapiService {

    private static final Logger logger = LogManager.getLogger(SwapiService.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Поиск информации о персонаже по имени.
     * @param query имя персонажа
     * @return информация о персонаже или сообщение об ошибке
     */
    public String searchPersonInfo(String query) {
        return search("people", query, PersonDTO.class);
    }

    /**
     * Поиск информации о планете по названию.
     * @param query название планеты
     * @return информация о планете или сообщение об ошибке
     */
    public String searchPlanetInfo(String query) {
        return search("planets", query, PlanetDTO.class);
    }

     /**
     * Поиск информации о фильме по названию.
     * @param query название фильма
     * @return информация о фильме или сообщение об ошибке
     */
    public String searchFilmInfo(String query) {
        return search("films", query, FilmDTO.class);
    }

    /**
     * Выполняет GET-запрос к SWAPI и возвращает JSON ответ.
     * Сделан protected для возможности переопределения в unit-тестах.
     * @param url URL запроса
     * @return JSON строка ответа
     */
    protected String executeRequest(String url) {
        Response response = RestAssured.get(url);
        logger.info("Получен ответ: HTTP {}", response.getStatusCode());
        return response.asString();
    }

    private <T> String search(String type, String query, Class<T> dtoClass) {
        try {
            String url = "https://swapi.dev/api/" + type + "/?search=" + query;
            logger.info("Отправка запроса к SWAPI [{}]: {}", type, url);

            String json = executeRequest(url);

            SearchResponseDTO<T> searchResponse = objectMapper.readValue(
                    json,
                    objectMapper.getTypeFactory()
                            .constructParametricType(SearchResponseDTO.class, dtoClass)
            );

            if (searchResponse.getCount() == 0) {
                logger.warn("{} '{}' не найден", type, query);
                return type.substring(0, 1).toUpperCase() + type.substring(1) + " не найден";
            }

            T item = searchResponse.getResults().get(0);
            logger.info("Данные {} успешно получены для '{}'", type, query);

            return item.toString();

        } catch (Exception e) {
            logger.error("Ошибка при получении данных {} для '{}'", type, query, e);
            return "Ошибка при получении данных";
        }
    }
}
