import java.util.LinkedList;

public class Snake {
    private LinkedList<Cell> parts;
    private Cell head;

    private Direction direction;

    public Snake(Cell startingPosition, Direction direction) {
        parts = new LinkedList<>();
        startingPosition.setType(CellType.SNAKE);
        parts.add(startingPosition);
        head = startingPosition;
        this.direction = direction;
    }

    public Snake(Cell startingPosition) {
        new Snake(startingPosition, Direction.TOP);
    }

    public boolean update(Cell newCell) {
        boolean isFood = newCell.getType() == CellType.FOOD;
        if (checkCrash(newCell)) {
            clear();
            //tell game that snake is destroyed
        }
        move(newCell);
        if (isFood) {
            grow();
            //tell game to generate new food
        }
        return true;
    }

    public void move(Cell newCell) {
        Cell tail = parts.removeLast();
        tail.setType(CellType.FREE);
        head = newCell;
        head.setType(CellType.SNAKE);
    }

    public void grow() {
        parts.add(parts.getLast());
    }

    public void clear() {
        for (Cell c : parts) {
            c.setType(CellType.FREE);
        }
    }

    public boolean checkCollision(Cell cell) {
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
