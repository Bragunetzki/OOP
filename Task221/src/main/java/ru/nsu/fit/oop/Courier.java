package ru.nsu.fit.oop;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class that simulates a pizzeria courier.
 */
public class Courier implements Callable<Void> {
    private final int orderTime;
    private final int baggageCap;
    private ArrayList<Integer> orders;
    private final Warehouse parentWarehouse;
    AtomicBoolean available;

    /**
     * Returns the orderTime of the courier.
     * @return - the orderTime.
     */
    int getOrderTime() {
        return orderTime;
    }

    /**
     * @return - returns true if courier is available for work.
     */
    boolean isAvailable() {
        return available.get();
    }

    /**
     * Basic constructor.
     * @param parentWarehouse - the warehouse that the Courier will work with.
     * @param orderTime - the time in miliseconds that it takes to complete an order.
     * @param baggageCap - the maximum number of orders that a courier can carry.
     */
    public Courier(Warehouse parentWarehouse, int orderTime, int baggageCap) {
        if (orderTime <= 0 || baggageCap <= 0) {
            throw new InvalidParameterException("courier order time and baggage cap must be above zero!");
        }

        available = new AtomicBoolean(true);
        this.orderTime = orderTime;
        this.baggageCap = baggageCap;
        this.parentWarehouse = parentWarehouse;
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

        //get orders
        synchronized (this) {
            orders.addAll(parentWarehouse.takeOrderList(baggageCap));
        }

        //delivery
        for (Integer i : orders) {
            Thread.sleep(orderTime);
            parentWarehouse.completeOrder();
            System.out.println("[" + i + "], [complete]!");
        }
        orders.clear();
        available.set(true);
        return null;
    }
}
