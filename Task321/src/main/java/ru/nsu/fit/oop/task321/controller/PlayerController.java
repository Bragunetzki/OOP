package ru.nsu.fit.oop.task321.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.task321.model.Direction;

import java.util.ArrayList;

public class PlayerController implements EventHandler<KeyEvent>, SnakeController {
    private final ArrayList<ControlledSnake> controlledActors;
    private Direction direction;
    private boolean keyPressed;

    public PlayerController() {
        controlledActors = new ArrayList<>();
        keyPressed = false;
    }

    public void resetKeyPressed() {
        keyPressed = false;
    }

    @Override
    public void subscribe(ControlledSnake actor) {
        controlledActors.add(actor);
    }

    @Override
    public void update() {
        keyPressed = true;
        for (ControlledSnake s : controlledActors) {
            s.setDirection(direction);
        }
    }

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
        }
    }
}
