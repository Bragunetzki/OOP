package ru.nsu.fit.oop.task321.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.task321.model.Cell;
import ru.nsu.fit.oop.task321.model.GameModel;
import ru.nsu.fit.oop.task321.model.Map;

public class Drawer {
    private static final int CELL_SIZE = 20;
    private static final Color BG_COLOR = Color.BLACK;
    private static final Color WALL_COLOR = Color.BEIGE;
    private static final Color SNAKE_COLOR = Color.DARKGREEN;
    private static final Color FOOD_COLOR = Color.RED;

    public void draw(GameModel model, GraphicsContext gc) {
        Map map = model.getMap();
        gc.setFill(BG_COLOR);
        gc.fillRect(0, 0, map.getWidth() * CELL_SIZE, map.getHeight() * CELL_SIZE);

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                drawCell(map.getCell(x, y), gc);
            }
        }
    }

    private void drawCell(Cell cell, GraphicsContext gc) {
        switch (cell.getType()) {
            case FREE -> {
                return;
            }
            case OBSTACLE -> {
                gc.setFill(WALL_COLOR);
            }
            case FOOD -> {
                gc.setFill(FOOD_COLOR);
            }
            case SNAKE -> {
                gc.setFill(SNAKE_COLOR);
            }
        }
        gc.fillRect(cell.getX() * CELL_SIZE, cell.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
}
