package ru.nsu.fit.oop.task321;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.nsu.fit.oop.task321.controller.PlayerController;
import ru.nsu.fit.oop.task321.model.GameModel;
import ru.nsu.fit.oop.task321.view.Drawer;


public class SnakeApplication extends Application {
    private final static int WIDTH = 500;
    private final static int HEIGHT = 500;
    private PlayerController controller;
    private GameModel model;
    private Drawer drawer;

    @Override
    public void start(Stage stage) {
        model = new GameModel();
        controller = new PlayerController();
        controller.subscribe(model.getSnake(0));
        drawer = new Drawer();

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

        Game game = new Game(model, new Drawer(), controller, canvas.getGraphicsContext2D());

        Thread gameLoop = new Thread(game);
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch();
    }
}