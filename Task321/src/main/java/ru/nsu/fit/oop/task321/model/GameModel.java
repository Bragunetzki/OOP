package ru.nsu.fit.oop.task321.model;

import java.util.ArrayList;

public class GameModel {
    private static final int MAP_WIDTH = 25;
    private static final int MAP_HEIGHT = 25;
    private final ArrayList<Snake> snakes;
    private final Map map;

    public GameModel() {
        map = new Map(MAP_WIDTH, MAP_HEIGHT);
        snakes = new ArrayList<>();
        snakes.add(new Snake(this, map.getCell(10, 10)));
        generateFood();
    }

    public Map getMap() {
        return map;
    }

    public Snake getSnake(int index) {
        return snakes.get(index);
    }

    public boolean isGameOver() {
        return snakes.size() <= 0;
    }

    private void removeSnake(Snake snake) {
        snakes.remove(snake);
    }

    private void generateFood() {
        int x = (int) (Math.random() * map.getWidth());
        int y = (int) (Math.random() * map.getHeight());
        boolean collides = false;
        for (Snake s : snakes) {
            if (s.contains(map.getCell(x, y))) {
                collides = true;
                break;
            }
        }
        if (collides) generateFood();
        else map.getCell(x, y).setType(CellType.FOOD);
    }

    public void update() {
        for (Snake s : snakes) {
            Cell newCell = map.getCell(s.getHead(), s.getDirection());
            s.update(newCell);
        }
    }

    public void foodConsumed() {
        generateFood();
    }

    public void snakeDestroyed(Snake snake) {
        removeSnake(snake);
    }
}
