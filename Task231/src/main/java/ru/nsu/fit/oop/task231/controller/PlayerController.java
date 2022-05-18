package ru.nsu.fit.oop.task231.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.task231.SnakeApplication;
import ru.nsu.fit.oop.task231.model.Direction;
import java.util.ArrayList;

/**
 * A SnakeController that takes input from the player.
 */
public class PlayerController implements EventHandler<KeyEvent>, SnakeController {
    private final ArrayList<ControlledSnake> controlledActors;
    private Direction direction;
    private boolean keyPressed;
    private final SnakeApplication application;

    /**
     * @param application - the SnakeApplication that the PlayerController communicates with.
     */
    public PlayerController(SnakeApplication application) {
        controlledActors = new ArrayList<>();
        this.application = application;
        keyPressed = false;
    }

    /**
     * Resets the controller's keyPressed value to false.
     */
    public void resetKeyPressed() {
        keyPressed = false;
    }

    /**
     * Clears the controller's list of managed snakes.
     */
    public void reset() {
        controlledActors.clear();
    }

    /**
     * Adds a ControlledSnake to the controller's list of managed snakes.
     *
     * @param snake - the snake that should be subscribed to the controller.
     */
    @Override
    public void subscribe(ControlledSnake snake) {
        controlledActors.add(snake);
    }

    /**
     * Updates the controller's list of snakes by changing their direction to the one input by the player.
     */
    @Override
    public void update() {
        keyPressed = true;
        for (ControlledSnake s : controlledActors) {
            s.setDirection(direction);
        }
    }

    /**
     * Handles a KeyEvent.
     * UP, TOP, LEFT and RIGHT keys change the direction of controlled snakes accordingly.
     * The ENTER keys attempts to reset the game.
     *
     * @param event - the KeyEvent that is handled.
     */
    @Override
    public void handle(KeyEvent event) {
        if (keyPressed) return;
        switch (event.getCode()) {
            case UP -> {
                direction = Direction.TOP;
                update();
            }
            case LEFT -> {
                direction = Direction.LEFT;
                update();
            }
            case RIGHT -> {
                direction = Direction.RIGHT;
                update();
            }
            case DOWN -> {
                direction = Direction.BOTTOM;
                update();
            }
            case ENTER -> application.reset();
        }
    }
}
