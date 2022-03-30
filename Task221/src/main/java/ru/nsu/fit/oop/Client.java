package ru.nsu.fit.oop;

import java.util.Random;
import java.util.concurrent.Callable;


/**
 * Simulates a client that generates random orders.
 */
public class Client implements Callable<Void> {
    private final Random random;
    private final int totalOrders;
    private final OrderQueue orderQueue;

    /**
     * Basic client constructor.
     * @param orderQueue - the orderQueue into which the ru.nsu.fit.oop.Client will sumbit their orders.
     * @param totalOrders - the total number of orders that the client should complete.
     */
    public Client(OrderQueue orderQueue, int totalOrders) {
        random = new Random();
        this.totalOrders = totalOrders;
        this.orderQueue = orderQueue;
    }

    /**
     * Activates a client. They will submit the totalOrders number of orders at random intervals of 1-4 seconds.
     * @return - void.
     * @throws InterruptedException - throws exception if sleep is interrupted.
     */
    @Override
    public Void call() throws InterruptedException {
        for (int i = 1; i <= totalOrders; i++) {
            Thread.sleep((random.nextInt(4) + 1) * 1000);
            orderQueue.acceptNewOrder();
        }
        return null;
    }
}
