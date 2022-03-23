import java.util.LinkedList;

/**
 * Class that simulates a pizzeria warehouse.
 */
public class Warehouse {
    private int warehouseSize;
    private LinkedList<Integer> storage;

    /**
     * Basic constructor
     * @param warehouseSize - the maximum number of orders that can fit into a warehouse.
     */
    public Warehouse(int warehouseSize) {
        this.warehouseSize = warehouseSize;
        storage = new LinkedList<>();
    }

    /**
     * Adds a new order to the warehouse.
     * @param order - the number of the order.
     * @throws InterruptedException - throws if wait is interrupted.
     */
    synchronized void storeOrder(int order) throws InterruptedException {
        if (storage.size() >= warehouseSize) wait();
        System.out.println("[" + order + "], [in warehouse]");
        storage.add(order);
    }

    /**
     * Removes an order from the warehouse.
     * @return - the number of the removed order.
     */
    int removeOrder() {
        return storage.removeFirst();
    }

    /**
     * @return - returns true if warehouse is empty.
     */
    boolean isEmpty() {
        return storage.isEmpty();
    }
}
