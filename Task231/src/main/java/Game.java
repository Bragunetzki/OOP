public class Game {
    private final Snake[] snakes;
    private final Map map;

    public Game() {
        map = new Map(20, 20);
        snakes = new Snake[1];
        snakes[0] = new Snake(map.getCell(10, 10));
        generateFood();
    }

    public void generateFood() {
        int x = (int) (Math.random() * map.getWidth());
        int y = (int) (Math.random() * map.getHeight());
        boolean collides = false;
        for (Snake s : snakes) {
            if (s.checkCollision(map.getCell(x, y))) {
                collides = true;
                break;
            }
        }
        if (collides) generateFood();
        else map.getCell(x, y).setType(CellType.FOOD);
    }

    public void update() {
        for (Snake s : snakes) {
            Cell newCell = map.getCell(s.getHead().getX(), s.getHead().getY(), s.getDirection());
            s.update(newCell);
        }
    }
}
