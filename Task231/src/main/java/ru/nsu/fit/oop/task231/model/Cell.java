package ru.nsu.fit.oop.task231.model;

/**
 * Represents a cell in the game map with X and Y coordinates and a CellType.
 */
public class Cell {
    private final int x;
    private final int y;
    private CellType type;

    /**
     * @param x    - the cell's x coordinate on the game map.
     * @param y    - the cell's y coordinate on the game map.
     * @param type - the initial type of the cell.
     */
    public Cell(int x, int y, CellType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Sets the cell's type.
     *
     * @param type - the CellType that will be set.
     */
    public void setType(CellType type) {
        this.type = type;
    }

    /**
     * @return - the cell's x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * @return - the cell's y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * @return - the cell's current CellType.
     */
    public CellType getType() {
        return type;
    }
}
