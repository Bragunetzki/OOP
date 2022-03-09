import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class Courier implements Callable<Void> {
    private int orderTime;
    private int baggageCap;
    private ArrayList<Integer> orders;
    private Pizzeria parentPizzeria;
    AtomicBoolean available;

    long getOrderTime() {
        return orderTime;
    }

    int getBaggageCap() { return baggageCap; }

    void addOrder(int order) {
        if (orders.size() >= baggageCap) throw new RuntimeException("cannot add orders to a full courier!");
        orders.add(order);
    }

    boolean isAvailable() {
        return available.get();
    }

    public Courier(Pizzeria parentPizzeria, int orderTime, int baggageCap) {
        if (orderTime <= 0 || baggageCap <= 0) {
            throw new InvalidParameterException("courier order time and baggage cap must be above zero!");
        }

        available = new AtomicBoolean(true);
        this.orderTime = orderTime;
        this.baggageCap = baggageCap;
        this.parentPizzeria = parentPizzeria;
        orders = new ArrayList<>();
    }

    @Override
    public Void call() throws InterruptedException {
        available.set(false);
        for (Integer i : orders) {
            Thread.sleep(orderTime);
            parentPizzeria.completeOrder(i);
        }
        orders = new ArrayList<>();
        available.set(true);
        return null;
    }
}
