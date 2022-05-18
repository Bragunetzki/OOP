package ru.nsu.fit.oop.task231.controller;

import ru.nsu.fit.oop.task231.model.Direction;

/**
 * A snake inside the game model that can change direction.
 */
public interface ControlledSnake {
    /**
     * Sets the direction of the ControlledSnake.
     *
     * @param direction - the direction that the snake should change to.
     */
    void setDirection(Direction direction);

    /**
     * Returns the ControlledSnake's direction.
     *
     * @return - the snake's direction.
     */
    Direction getDirection();
}
