package ru.nsu.fit.oop.task321.model;

import ru.nsu.fit.oop.task321.controller.ControlledSnake;

import java.util.LinkedList;

public class Snake implements ControlledSnake {
    private LinkedList<Cell> parts;
    private Cell head;
    private GameModel game;

    private Direction direction;

    public Snake(GameModel game, Cell startingPosition, Direction direction) {
        this.game = game;
        parts = new LinkedList<>();
        startingPosition.setType(CellType.SNAKE);
        parts.add(startingPosition);
        head = startingPosition;
        this.direction = direction;
    }

    public Snake(GameModel game, Cell startingPosition) {
        this(game, startingPosition, Direction.TOP);
    }

    public boolean update(Cell newCell) {
        if (newCell.getType() == CellType.FOOD) {
            parts.add(parts.getLast());
            game.foodConsumed();
        }
        if (checkCrash(newCell)) {
            destroy();
            game.snakeDestroyed(this);
        }
        move(newCell);
        return true;
    }

    public void move(Cell newCell) {
        Cell tail = parts.removeLast();
        tail.setType(CellType.FREE);
        head = newCell;
        head.setType(CellType.SNAKE);
        parts.addFirst(head);
    }

    public void destroy() {
        for (Cell c : parts) {
            c.setType(CellType.FREE);
        }
    }

    public boolean contains(Cell cell) {
        for (Cell c : parts) {
            if (cell == c) return true;
        }
        return false;
    }

    public boolean checkCrash(Cell newCell) {
        if (newCell.getType() == CellType.OBSTACLE) return true;

        for (Cell c : parts) {
            if (c == newCell) return true;
        }

        return false;
    }

    public Cell getHead() {
        return head;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
