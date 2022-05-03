package ru.nsu.fit.oop.task321.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.task321.model.Cell;
import ru.nsu.fit.oop.task321.model.GameModel;
import ru.nsu.fit.oop.task321.model.Map;

/**
 * A class that is responsible for drawing the game's visuals.
 */
public abstract class Drawer {
    private static final int CELL_SIZE = 20;
    private static final Color BG_COLOR = Color.BLACK;
    private static final Color WALL_COLOR = Color.BEIGE;
    private static final Color SNAKE_COLOR = Color.DARKGREEN;
    private static final Color FOOD_COLOR = Color.RED;

    /**
     * Draws the entire game map.
     * @param model - the GameModel that the drawer will reference.
     * @param gc - the GraphicsContext that the drawer will use for graphics.
     */
    public static void draw(GameModel model, GraphicsContext gc) {
        Map map = model.getMap();
        gc.setFill(BG_COLOR);
        gc.fillRect(0, 0, map.getWidth() * CELL_SIZE, map.getHeight() * CELL_SIZE);

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                drawCell(map.getCell(x, y), gc);
            }
        }
    }

    /**
     * Draws a specific cell.
     * @param cell - the Cell that will be drawn.
     * @param gc - the GraphicsContext that the drawer will use for graphics.
     */
    private static void drawCell(Cell cell, GraphicsContext gc) {
        switch (cell.getType()) {
            case FREE -> {
                return;
            }
            case OBSTACLE -> gc.setFill(WALL_COLOR);
            case FOOD -> gc.setFill(FOOD_COLOR);
            case SNAKE -> gc.setFill(SNAKE_COLOR);
        }
        gc.fillRect(cell.getX() * CELL_SIZE, cell.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    /**
     * Displays a message.
     * @param message - the text of the message.
     * @param gc - the GraphicsContext that will be used for graphics.
     */
    public static void drawMessage(String message, GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillText(message, 30, 30);
    }
}
