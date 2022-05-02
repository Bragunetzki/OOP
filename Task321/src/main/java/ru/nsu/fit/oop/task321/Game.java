package ru.nsu.fit.oop.task321;

import javafx.scene.canvas.GraphicsContext;
import ru.nsu.fit.oop.task321.controller.PlayerController;
import ru.nsu.fit.oop.task321.model.GameModel;
import ru.nsu.fit.oop.task321.view.Drawer;

public class Game implements Runnable {
    private static final float gameSpeed = 3;
    private static final float updateInterval = 1000.0f / gameSpeed;
    private final GameModel model;
    private final Drawer drawer;
    private final PlayerController controller;
    private final GraphicsContext gc;
    private boolean running;

    public Game(GameModel model, Drawer drawer, PlayerController controller, GraphicsContext gc) {
        this.model = model;
        this.drawer = drawer;
        this.controller = controller;
        this.gc = gc;
        running = false;
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            float delta = System.currentTimeMillis();

            model.update();
            drawer.draw(model, gc);
            controller.resetKeyPressed();

            if (model.isGameOver()) {
                running = false;
            }

            delta = System.currentTimeMillis() - delta;

            if (delta < updateInterval) {
                try {
                    Thread.sleep((long) (updateInterval - delta));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
