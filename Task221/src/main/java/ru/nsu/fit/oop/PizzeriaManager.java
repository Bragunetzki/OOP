package ru.nsu.fit.oop;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Class that distributes the orders between pizzeria workers.
 */
public class PizzeriaManager implements Runnable {
    private final Pizzeria workingPizzeria;

    /**
     * Basic costructer.
     * @param pizzeria - the pizzeria that the manager will work with.
     */
    public PizzeriaManager(Pizzeria pizzeria) {
        workingPizzeria = pizzeria;
    }

    /**
     * Runs the manager. It will attempt to assign orders from the pizzeria's orderQueue to available bakers,
     * starting with the fastest baker. It will do the same for couriers as long as the warehouse contains any orders.
     * The manager stops working after the orderQueue becomes disabled (most likely because a ru.nsu.fit.oop.ClientManager disabled it),
     * the warehouse is empty and no couriers or bakers are active.
     */
    @Override
    public void run() {
       ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(workingPizzeria.numberOfWorkers());
        List<Baker> bakers = workingPizzeria.getBakers();
        List<Courier> couriers = workingPizzeria.getCouriers();
        Warehouse warehouse = workingPizzeria.getWarehouse();
        OrderQueue orderQueue = workingPizzeria.getOrderQueue();

        while (orderQueue.isEnabled() || workingPizzeria.getOrdersTotal() != warehouse.getOrdersCompleted()) {
            //assign any pending orders to available bakers.
            for (Baker b : bakers) {
                if (orderQueue.isEmpty()) break;
                if (b.isAvailable()) {
                    synchronized (this) {
                        b.setCurrentOrder(orderQueue.takeOrder());
                        service.submit(b);
                    }
                }
            }
            //assign any stored orders to available couriers.
            for (Courier c : couriers) {
                if (warehouse.isEmpty()) break;
                if (c.isAvailable()) {
                    service.submit(c);
                }
            }
        }
        service.shutdown();
    }
}