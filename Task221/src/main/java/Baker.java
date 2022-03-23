import java.security.InvalidParameterException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class that simulates a pizzeria baker.
 */
public class Baker implements Callable<Void> {
    private final int orderTime;
    private final Warehouse parentWarehouse;
    AtomicBoolean available;
    private int currentOrder;

    /**
     * Returns the number of current order given to baker.
     * @return - the number of the order.
     */
    int getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Sets the current order of the baker to a new value.
     * @param order - the new value of the order.
     */
    void setCurrentOrder(int order) {
        currentOrder = order;
    }

    /**
     * Returns the time that the baker takes to complete an order.
     * @return - the time in miliseconds.
     */
    public int getOrderTime() {
        return orderTime;
    }

    /**
     * @return - Returns true if the baker is available for work.
     */
    public boolean isAvailable() {
        return available.get();
    }

    /**
     * Default baker constructor.
     * @param parentWarehouse - the warehouse class that the baker works with.
     * @param orderTime - the time that the baker should take to complete an order.
     */
    public Baker(Warehouse parentWarehouse, int orderTime) {
        this.parentWarehouse = parentWarehouse;
        this.available = new AtomicBoolean(true);
        if (orderTime <= 0) {
            throw new InvalidParameterException("cook order time must be above zero!");
        }
        this.orderTime = orderTime;
    }

    /**
     * Activates a baker. They will first sleep for their specified orderTime number of miliseconds,
     * after which they will attempt to place an order into the warehouse. If the warehouse is full,
     * the baker will wait for it to become free.
     * @return - returns null
     * @throws InterruptedException - throws due to synchronisation.
     */
    @Override
    public Void call() throws InterruptedException {
        available.set(false);
        Thread.sleep(orderTime);
        parentWarehouse.storeOrder(currentOrder);
        available.set(true);
        return null;
    }
}
