package ru.nsu.fit.oop.task231;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.nsu.fit.oop.task231.controller.Game;
import ru.nsu.fit.oop.task231.controller.PlayerController;
import ru.nsu.fit.oop.task231.model.GameModel;


/**
 * The main application class of the program.
 */
public class SnakeApplication extends Application {
    private final static int WIDTH = 500;
    private final static int HEIGHT = 500;
    private PlayerController controller;
    private GameModel model;
    private Game game;
    private Thread gameThread;

    @Override
    public void start(Stage stage) {
        model = new GameModel();
        controller = new PlayerController(this);
        controller.subscribe(model.getSnake(0));

        StackPane pane = new StackPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(controller);
        pane.getChildren().add(canvas);

        Scene scene = new Scene(pane);

        stage.setTitle("Snake");
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> System.exit(0));
        stage.setScene(scene);
        stage.show();

        game = new Game(model, controller, canvas.getGraphicsContext2D());
        gameThread = new Thread(game);
        gameThread.start();
    }

    /**
     * Resets the game application.
     */
    public void reset() {
        if (!game.isRunning()) {
            model.reset();
            controller.reset();
            controller.subscribe(model.getSnake(0));
            gameThread = new Thread(game);
            gameThread.start();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
