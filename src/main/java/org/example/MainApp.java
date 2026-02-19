package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.service.SwapiService;
import org.example.util.Dictionary;


/**
 * Главный класс приложения SWAPI Viewer на JavaFX.
 * Отвечает за создание графического интерфейса и обработку событий.
 * <p>
 * Позволяет пользователю искать информацию о персонажах, планетах и фильмах
 * вселенной Star Wars, а также выводить списки из словаря.
 */
public class MainApp extends Application {

    /** Логгер для записи событий приложения */
    private static final Logger logger = LogManager.getLogger(MainApp.class);

    /** Сервис для взаимодействия с SWAPI */
    private SwapiService swapiService = new SwapiService();


    /**
     * Метод запуска JavaFX приложения.
     *
     * @param stage основной Stage приложения
     */
    @Override
    public void start(Stage stage) {

        // ComboBox для типа запроса
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Персонаж", "Планета", "Фильм");
        typeCombo.setValue("Персонаж");

        // Поле ввода
        TextField input = new TextField();
        input.setPromptText("Введите имя или название...");
        input.setStyle("-fx-font-size: 14;");
        input.setPrefWidth(250);

        HBox inputRow = new HBox(10, typeCombo, input);
        inputRow.setAlignment(Pos.TOP_LEFT);

        // TextArea для вывода
        TextArea output = new TextArea();
        output.setWrapText(true);
        output.setEditable(false);
        output.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 14;");

        ScrollPane scrollPane = new ScrollPane(output);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // ===== Кнопки =====
        Button showCharactersBtn = new Button("Список персонажей");
        Button showPlanetsBtn = new Button("Список планет");
        Button showFilmsBtn = new Button("Список фильмов");
        Button clearBtn = new Button("Очистить");

        showCharactersBtn.setOnAction(e -> {
            output.setText(Dictionary.showCharacters());
            logger.info("Пользователь запросил список персонажей");
        });

        showPlanetsBtn.setOnAction(e -> {
            output.setText(Dictionary.showPlanets());
            logger.info("Пользователь запросил список планет");
        });

        showFilmsBtn.setOnAction(e -> {
            output.setText(Dictionary.showFilms());
            logger.info("Пользователь запросил список фильмов");
        });

        clearBtn.setOnAction(e -> {
            input.clear();
            output.clear();
            logger.info("Пользователь очистил поля ввода и вывода");
        });

        HBox buttons = new HBox(10, showCharactersBtn, showPlanetsBtn, showFilmsBtn, clearBtn);
        buttons.setAlignment(Pos.TOP_LEFT);

        // ===== Поиск при Enter =====
        input.setOnAction(e -> performSearch(typeCombo, input, output));

        VBox root = new VBox(10, inputRow, buttons, scrollPane);
        root.setPadding(new Insets(15));

        // разрешаем ScrollPane занимать всё свободное место
        VBox.setVgrow(scrollPane, Priority.ALWAYS);


        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("SWAPI Viewer");
        stage.setScene(scene);
        stage.show();
    }

  private void performSearch(ComboBox<String> typeCombo, TextField input, TextArea output) {
    String query = input.getText().trim();
    if (query.isEmpty()) {
        output.setText("Введите значение для поиска!");
        return;
    }

    String type = typeCombo.getValue();
    String result;

    switch (type) {
        case "Персонаж":
            if (!Dictionary.isValidCharacter(query)) {
                output.setText("Персонаж не найден в словаре!\n" + Dictionary.showCharacters());
                logger.warn("Попытка запроса недоступного персонажа: {}", query);
                return;
            }
            result = swapiService.searchPersonInfo(query);
            break;

        case "Планета":
            if (!Dictionary.isValidPlanet(query)) {
                output.setText("Планета не найдена в словаре!\n" + Dictionary.showPlanets());
                logger.warn("Попытка запроса недоступной планеты: {}", query);
                return;
            }
            result = swapiService.searchPlanetInfo(query);
            break;

        case "Фильм":
            if (!Dictionary.isValidFilm(query)) {
                output.setText("Фильм не найден в словаре!\n" + Dictionary.showFilms());
                logger.warn("Попытка запроса недоступного фильма: {}", query);
                return;
            }
            result = swapiService.searchFilmInfo(query);
            break;

        default:
            result = "Неизвестный тип запроса";
    }

    output.setText(result);
    logger.info("Выполнен поиск '{}': {}", type, query);
}

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        launch();
    }
}
