package ru.nsu.fit.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class that simulates a pizzeria warehouse.
 */
public class Warehouse {
    private final int warehouseSize;
    private final LinkedList<Integer> storage;
    private int ordersCompleted;

    public int getOrdersCompleted() {
        return ordersCompleted;
    }

    /**
     * Basic constructor
     * @param warehouseSize - the maximum number of orders that can fit into a warehouse.
     */
    public Warehouse(int warehouseSize) {
        this.warehouseSize = warehouseSize;
        ordersCompleted = 0;
        storage = new LinkedList<>();
    }

    /**
     * Adds a new order to the warehouse.z
     * @param order - the number of the order.
     * @throws InterruptedException - throws if wait is interrupted.
     */
    synchronized void storeOrder(int order) throws InterruptedException {
        while (storage.size() >= warehouseSize) wait();
        storage.add(order);
        System.out.println("[" + order + "], [in warehouse]");
        notifyAll();
    }

    synchronized ArrayList<Integer> takeOrderList(int capacity) throws InterruptedException {
        while (storage.isEmpty()) wait();
        ArrayList<Integer> bulk = new ArrayList<>();
        while (!storage.isEmpty() && bulk.size() < capacity) {
            int o = storage.removeFirst();
            System.out.println("[" + o + "], [in delivery]");
            bulk.add(o);
        }
        if (!bulk.isEmpty()) notifyAll();
        return bulk;
    }

    public synchronized void completeOrder() {
        ordersCompleted++;
    }

    /**
     * @return - returns true if warehouse is empty.
     */
    public synchronized boolean isEmpty() {
        return storage.isEmpty();
    }
}
