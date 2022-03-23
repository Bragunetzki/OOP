import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class that simulates a pizzeria courier.
 */
public class Courier implements Callable<Void> {
    private int orderTime;
    private int baggageCap;
    private ArrayList<Integer> orders;
    private Pizzeria parentPizzeria;
    AtomicBoolean available;

    /**
     * Returns the orderTime of the courier.
     * @return - the orderTime.
     */
    int getOrderTime() {
        return orderTime;
    }

    /**
     * Returns the baggageCap of the courier.
     * @return - the baggageCap.
     */
    int getBaggageCap() { return baggageCap; }

    /**
     * Adds an order to the courier's list of orders. Throws a RuntimeException if the courier's number of orders exceeds baggageCap.
     * @param order - the number of the order that will be added.
     */
    void addOrder(int order) {
        if (orders.size() >= baggageCap) throw new RuntimeException("cannot add orders to a full courier!");
        orders.add(order);
    }

    /**
     * @return - returns true if courier is available for work.
     */
    boolean isAvailable() {
        return available.get();
    }

    /**
     * Basic constructor.
     * @param parentPizzeria - the pizzeria that the Courier will work with.
     * @param orderTime - the time in miliseconds that it takes to complete an order.
     * @param baggageCap - the maximum number of orders that a courier can carry.
     */
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

    /**
     * Activates a courier. They will sleep for orderTime miliseconds to deliver each order stored in their baggage, after which they will terminate.
     * @return - void.
     * @throws InterruptedException - if sleep is interrupted.
     */
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
