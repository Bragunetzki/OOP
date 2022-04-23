package ru.nsu.fit.oop.task321.model;

import java.security.InvalidParameterException;

public class Map {
    private final int width;
    private final int height;
    private final Cell[][] mapCells;

    public Map(int width, int height) {
        if (width < 0 || height < 0) throw new InvalidParameterException("Map width and height cannot be negative!");
        this.width = width;
        this.height = height;
        mapCells = new Cell[height][width];
        initCells();
    }

    private void initCells() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mapCells[y][x] = new Cell(x, y, CellType.FREE);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            throw new InvalidParameterException("Coordinates out of map bounds!");
        return mapCells[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int x, int y, Direction direction) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            throw new InvalidParameterException("Coordinates out of map bounds!");
        int newX = x, newY = y;
        switch (direction) {
            case TOP -> {
                newX = x;
                newY = y + 1;
            }
            case LEFT -> {
                newX = x - 1;
                newY = y;
            }
            case RIGHT -> {
                newX = x + 1;
                newY = y;
            }
            case BOTTOM -> {
                newX = x;
                newY = y - 1;
            }
        }

        if (newX < 0) newX = width-1;
        if (newX >= width) newX = 0;
        if (newY < 0) newY = height-1;
        if (newY >= height) newY = 0;

        return mapCells[y][x];
    }
}
