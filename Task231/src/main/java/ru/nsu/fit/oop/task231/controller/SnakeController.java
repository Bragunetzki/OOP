package ru.nsu.fit.oop.task231.controller;

/**
 * A controller that can update the direction of snakes assigned to it.
 */
public interface SnakeController {
    /**
     * Adds a ControlledSnake to the controller's list of managed snakes.
     *
     * @param snake - the snake that should be subscribed to the controller.
     */
    void subscribe(ControlledSnake snake);

    /**
     * Updates the controller's list of managed snakes accordingly.
     */
    void update();
}
