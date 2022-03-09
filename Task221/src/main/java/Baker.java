import java.security.InvalidParameterException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class Baker implements Callable<Void> {
    private final int orderTime;
    private final Pizzeria parentPizzeria;
    AtomicBoolean available;
    private int currentOrder;

    int getCurrentOrder() {
        return currentOrder;
    }

    void setCurrentOrder(int order) {
        currentOrder = order;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public boolean isAvailable() {
        return available.get();
    }

    public Baker(Pizzeria parentPizzeria, int orderTime) {
        this.parentPizzeria = parentPizzeria;
        this.available = new AtomicBoolean(true);
        if (orderTime <= 0) {
            throw new InvalidParameterException("cook order time must be above zero!");
        }
        this.orderTime = orderTime;
    }

    @Override
    public Void call() throws InterruptedException {
        available.set(false);
        Thread.sleep(orderTime);
        parentPizzeria.storeOrder(currentOrder);
        available.set(true);
        return null;
    }
}
