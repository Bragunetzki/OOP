package ru.nsu.fit.oop.task321.model;

public class Cell {
    private final int x;
    private final int y;
    private CellType type;
    public Cell(int x, int y, CellType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellType getType() {
        return type;
    }
}
