import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class that simulates a pizzeria queue of incoming orders.
 */
public class OrderQueue {
    private int queuedOrder;
    private LinkedList<Integer> orders;
    private AtomicBoolean enabled;

    /**
     * Basic constructor that initializes an empty queue and enables it.
     */
    public OrderQueue() {
        enabled = new AtomicBoolean(true);
        queuedOrder = 0;
        orders = new LinkedList<>();
    }

    /**
     * @return - returns true if queue is enabled.
     */
    public boolean isEnabled() {
        return enabled.get();
    }

    /**
     * Disables the queue.
     */
    synchronized void disable() {
        enabled.set(false);
    }

    /**
     * Enables the queue.
     */
    synchronized void enable() {
        enabled.set(true);
    }

    /**
     * Submits a new order to the queue.
     */
    public synchronized void acceptNewOrder() {
        orders.add(++queuedOrder);
    }

    /**
     * @return - returns true if queue is empty.
     */
    boolean isEmpty() {
        return orders.isEmpty();
    }

    /**
     * Removes the first element of the queue.
     * @return - returns the number of removed order.
     */
    synchronized int takeOrder() {
        return orders.removeFirst();
    }
}
