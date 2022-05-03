package ru.nsu.fit.oop.task321;

import javafx.scene.canvas.GraphicsContext;
import ru.nsu.fit.oop.task321.controller.PlayerController;
import ru.nsu.fit.oop.task321.model.GameModel;
import ru.nsu.fit.oop.task321.view.Drawer;

/**
 * The class that runs the main game loop.
 */
public class Game implements Runnable {
    private static final float gameSpeed = 5;
    private static final float updateInterval = 1000.0f / gameSpeed;
    private final GameModel model;
    private final PlayerController controller;
    private final GraphicsContext gc;
    private boolean running;

    /**
     * Creates a new inactive game runnable.
     *
     * @param model      - the model that the game will update.
     * @param controller - the controller that the game will reference.
     * @param gc         - the GraphicsContext that the game will use for its graphics.
     */
    public Game(GameModel model, PlayerController controller, GraphicsContext gc) {
        this.model = model;
        this.controller = controller;
        this.gc = gc;
        running = false;
    }

    /**
     * @return - returns true if the game is still running.
     */
    synchronized public boolean isRunning() {
        return running;
    }

    /**
     * Runs the game loop.
     * It will first update the model, then draw it.
     * The game is updates at specific intervals.
     * Only one key input is read between each update.
     * The game loop terminates after the model's isGameOver() method returns true.
     */
    @Override
    public void run() {
        running = true;

        while (running) {
            float delta = System.currentTimeMillis();

            model.update();
            Drawer.draw(model, gc);
            controller.resetKeyPressed();

            if (model.isGameOver()) {
                running = false;
                controller.resetKeyPressed();
                Drawer.drawMessage("Game Over! Press ENTER to restart.", gc);
                break;
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
