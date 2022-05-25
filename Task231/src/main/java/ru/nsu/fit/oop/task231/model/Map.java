package ru.nsu.fit.oop.task231.model;

import java.security.InvalidParameterException;
import java.util.Random;

/**
 * A map with a set width and height consisting of Cells.
 */
public class Map {
    private final int width;
    private final int height;
    private final Cell[][] mapCells;
    private final static int OBSTACLE_DENSITY = 3;

    /**
     * Creates a Map with a set size.
     *
     * @param width  - the map's width.
     * @param height - the map's height.
     */
    public Map(int width, int height) {
        if (width < 0 || height < 0) throw new InvalidParameterException("Map width and height cannot be negative!");
        this.width = width;
        this.height = height;
        mapCells = new Cell[height][width];
        initCells();
    }

    /**
     * Clears all map cells.
     */
    public void reset() {
        initCells();
    }

    public Cell getRandomFreeCell() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);
        boolean collides = getCell(x, y).getType() == CellType.OBSTACLE
                || getCell(x, y).getType() == CellType.FOOD
                || getCell(x, y).getType() == CellType.SNAKE;

        if (collides) return getRandomFreeCell();
        else return getCell(x, y);
    }

    /**
     * Initializes all map cells. Called in constructor.
     */
    private void initCells() {
        Random rand = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (rand.nextInt(101) < OBSTACLE_DENSITY) {
                    mapCells[y][x] = new Cell(x, y, CellType.OBSTACLE);
                } else {
                    mapCells[y][x] = new Cell(x, y, CellType.FREE);
                }
            }
        }
    }

    /**
     * Returns a cell at specific coordinates.
     *
     * @param x - the x coordinate of the cell.
     * @param y - the y coordinate of the cell.
     * @return - the cell at the specified coordinates.
     */
    public Cell getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            throw new InvalidParameterException("Coordinates out of map bounds!");
        return mapCells[y][x];
    }

    /**
     * @return - returns the width of the map in cells.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return - returns the height of the map in cells.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns a cell that is one unit away from a different cell in the specified direction.
     * If the search goes out of map bounds, it loops around the game map.
     *
     * @param cell      - the cell from which to look for the next cell.
     * @param direction - the direction in which to get the cell.
     * @return - the new cell.
     */
    public Cell getCell(Cell cell, Direction direction) {
        int newX = cell.getX(), newY = cell.getY();
        switch (direction) {
            case TOP -> newY -= 1;
            case LEFT -> newX -= 1;
            case RIGHT -> newX += 1;
            case BOTTOM -> newY += 1;
        }

        if (newX < 0) newX = width - 1;
        if (newX >= width) newX = 0;
        if (newY < 0) newY = height - 1;
        if (newY >= height) newY = 0;

        return mapCells[newY][newX];
    }
}