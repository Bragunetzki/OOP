package ru.nsu.fit.oop.task231.model;

import java.util.ArrayList;

/**
 * Contains the game's state and data.
 */
public class GameModel {
    private static final int MAP_WIDTH = 25;
    private static final int MAP_HEIGHT = 25;
    private final ArrayList<Snake> snakes;
    private final ArrayList<Snake> snakesForRemoval;
    private final Map map;

    /**
     * Basic constructor.
     * Initializes the map with a single snake and a single food item.
     */
    public GameModel() {
        map = new Map(MAP_WIDTH, MAP_HEIGHT);
        snakes = new ArrayList<>();
        snakesForRemoval = new ArrayList<>();
        snakes.add(new Snake(this, map.getCell(10, 10)));
        generateFood();
    }

    /**
     * Resets the game map, the list of snakes and generates a new food item.
     */
    public void reset() {
        map.reset();
        snakes.add(new Snake(this, map.getCell(10, 10)));
        generateFood();
    }

    /**
     * @return - returns the game map.
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param index - the index of the snake to be returned.
     * @return - returns a snake at a specific index.
     */
    public Snake getSnake(int index) {
        return snakes.get(index);
    }

    /**
     * @return - returns true if the game is over and false otherwise.
     * The game is considered over if there are no snakes remaining.
     */
    public boolean isGameOver() {
        return snakes.size() <= 0;
    }

    /**
     * Marks the snake as one that should be removed after all the snakes have been updated.
     *
     * @param snake - the snakes that should be removed.
     */
    private void removeSnake(Snake snake) {
        snakesForRemoval.add(snake);
    }

    /**
     * Generates a new food item in a free cell on the game map.
     */
    private void generateFood() {
        int x = (int) (Math.random() * map.getWidth());
        int y = (int) (Math.random() * map.getHeight());
        boolean collides = false;

        for (Snake s : snakes) {
            if (s.contains(map.getCell(x, y)) || map.getCell(x, y).getType() == CellType.OBSTACLE || map.getCell(x, y).getType() == CellType.FOOD) {
                collides = true;
                break;
            }
        }

        if (map.getCell(x, y).getType() == CellType.OBSTACLE || map.getCell(x, y).getType() == CellType.FOOD)
            collides = true;

        if (collides) generateFood();
        else map.getCell(x, y).setType(CellType.FOOD);
    }

    /**
     * Updates the game by moving any remaining snakes in their directions.
     * Removes any crashed snakes from the game.
     */
    public void update() {
        for (Snake s : snakes) {
            Cell newCell = map.getCell(s.getHead(), s.getDirection());
            s.update(newCell);
        }
        for (Snake s : snakesForRemoval) {
            snakes.remove(s);
        }
    }

    /**
     * Determines the actions that should be taken when a food item is consumed.
     * Currently, the game simply calls generateFood().
     */
    public void onFoodConsumed() {
        generateFood();
    }

    /**
     * Determines the actions that should be taken when a snake is destroyed.
     * Currently, the game simply removes the snake from its list of snakes.
     *
     * @param snake - the snake that is destroyed.
     */
    public void onSnakeDestroyed(Snake snake) {
        removeSnake(snake);
    }
}
