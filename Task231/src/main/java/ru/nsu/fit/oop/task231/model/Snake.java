package ru.nsu.fit.oop.task231.model;

import ru.nsu.fit.oop.task231.controller.ControlledSnake;

import java.util.LinkedList;

/**
 * A snake consisting of an ordered list of cells.
 */
public class Snake implements ControlledSnake {
    private final LinkedList<Cell> parts;
    private Cell head;
    private final GameModel gameModel;
    private Direction direction;

    /**
     * Creates a snake with a single cell as its body,
     * starting from a specific cell and headed in a specified direction.
     *
     * @param gameModel        - the GameModel that the snake will communicate with.
     * @param startingPosition - the starting cell of the snake's head.
     * @param direction        - the initial direction of the snake.
     */
    public Snake(GameModel gameModel, Cell startingPosition, Direction direction) {
        this.gameModel = gameModel;
        parts = new LinkedList<>();
        startingPosition.setType(CellType.SNAKE);
        parts.add(startingPosition);
        head = startingPosition;
        this.direction = direction;
    }

    /**
     * Creates a snake with a single cell as its body,
     * starting from a specific cell and head upwards.
     *
     * @param gameModel        - the GameModel that the snake will communicate with.
     * @param startingPosition - the starting cell of the snake's head.
     */
    public Snake(GameModel gameModel, Cell startingPosition) {
        this(gameModel, startingPosition, Direction.TOP);
    }

    /**
     * Attempts to move the snake to a new cell and destroys the snake if it crashes.
     *
     * @param newCell - the cell that the snake will attempt to move to.
     */
    public void update(Cell newCell) {
        if (newCell.getType() == CellType.FOOD) {
            parts.add(parts.getLast());
            gameModel.onFoodConsumed();
        }
        if (checkCrash(newCell)) {
            destroy();
            gameModel.onSnakeDestroyed(this);
        }
        move(newCell);
    }

    /**
     * Moves the snake to a new cell.
     *
     * @param newCell - the cell that the will move to.
     */
    public void move(Cell newCell) {
        Cell tail = parts.removeLast();
        tail.setType(CellType.FREE);
        head = newCell;
        head.setType(CellType.SNAKE);
        parts.addFirst(head);
    }

    /**
     * Destroys the snake by clearing its cells.
     */
    public void destroy() {
        for (Cell c : parts) {
            c.setType(CellType.FREE);
        }
    }

    /**
     * Checks if a cell is part of the snake.
     *
     * @param cell - the cell that will be checked.
     * @return - returns true if the cell is part of the snake.
     */
    public boolean contains(Cell cell) {
        for (Cell c : parts) {
            if (cell == c) return true;
        }
        return false;
    }

    /**
     * Checks if a cell will crash the snake if it moves into it.
     *
     * @param newCell - the cell that will be tested.
     * @return - returns true if the specified cell is not an obstacle and isn't contained in the snake.
     */
    public boolean checkCrash(Cell newCell) {
        return newCell.getType() == CellType.OBSTACLE || newCell.getType() == CellType.SNAKE;
    }

    /**
     * @return - returns the head of the snake.
     */
    public Cell getHead() {
        return head;
    }

    /**
     * @return - returns the snake's current direction.
     */
    @Override
    public Direction getDirection() {
        return direction;
    }

    /**
     * Changes the snake's direction.
     *
     * @param direction - the direction that the snake will change to.
     */
    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
