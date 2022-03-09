import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Pizzeria implements Runnable {
    private int warehouseSize;
    private int queuedOrder;
    private LinkedList<Integer> orderQueue;
    private LinkedList<Integer> warehouse;
    private List<Baker> bakers;
    private List<Courier> couriers;
    private AtomicBoolean enabled;
    private AtomicInteger ordersCompleted;
    private int totalOrders;

    public synchronized void acceptNewOrder() {
        queuedOrder++;
        orderQueue.add(queuedOrder);
    }

    synchronized void completeOrder(int order) {
        System.out.println("[" + order + "], [complete]");
        ordersCompleted.set(ordersCompleted.get()+1);
    }

    synchronized boolean orderQueueEmpty() {
        return orderQueue.isEmpty();
    }

    synchronized void storeOrder(int order) throws InterruptedException {
        if (warehouse.size() >= warehouseSize) wait();
        warehouse.add(order);
        System.out.println("[" + order + "], [in warehouse]");
    }

    public Pizzeria(int totalOrders) {
        this.totalOrders = totalOrders;
        ordersCompleted = new AtomicInteger(0);
        enabled = new AtomicBoolean(true);
        List<BakerParams> bakerParamsList = new ArrayList<>();
        List<CourierParams> courierParamsList = new ArrayList<>();
        queuedOrder = 0;
        orderQueue = new LinkedList<>();
        warehouse = new LinkedList<>();
        bakers = new ArrayList<>();
        couriers = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            PizzeriaParams params = mapper.readValue(new File("pizzeria.json"), PizzeriaParams.class);
            warehouseSize = params.getWareHouseSize();
            bakerParamsList = mapper.readValue(new File("bakers.json"), new TypeReference<>() {
            });
            courierParamsList = mapper.readValue(new File("couriers.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (BakerParams bakerParams : bakerParamsList) {
            bakers.add(new Baker(this, bakerParams.getOrderTime()));
        }
        for (CourierParams courierParams : courierParamsList) {
            couriers.add(new Courier(this, courierParams.getOrderTime(), courierParams.getBaggageCap()));
        }

        bakers.sort(new cookComparator());
        couriers.sort(new courierComparator());
    }

    @Override
    public void run() {
        ExecutorService service = Executors.newFixedThreadPool(bakers.size() + couriers.size());

        while (ordersCompleted.get() < totalOrders) {
            //assign any pending orders to available bakers.
            for (Baker b : bakers) {
                if (orderQueue.isEmpty()) break;

                if (b.isAvailable()) {
                    b.setCurrentOrder(orderQueue.removeFirst());
                    service.submit(b);
                    System.out.println("[" + b.getCurrentOrder() + "], [cooking]");
                }
            }


            //assign any stored orders to available couriers.
            for (Courier c : couriers) {
                if (warehouse.isEmpty()) break;
                if (c.isAvailable()) {
                    synchronized (this) {
                        for (int i = 0; i < c.getBaggageCap() && !warehouse.isEmpty(); i++) {
                            int o = warehouse.removeFirst();
                            notifyAll();
                            System.out.println("[" + o + "], [in delivery]");
                            c.addOrder(o);
                        }
                        service.submit(c);
                    }
                }
            }
        }
        service.shutdown();
    }

    static class PizzeriaParams {
        private int warehouseSize;

        public PizzeriaParams() {

        }

        public PizzeriaParams(int warehouseSize) {
            this.warehouseSize = warehouseSize;
        }

        public int getWareHouseSize() {
            return warehouseSize;
        }

        public void setWarehouseSize(int warehouseSize) {
            this.warehouseSize = warehouseSize;
        }
    }

    private static class cookComparator implements Comparator<Baker> {
        @Override
        public int compare(Baker o1, Baker o2) {
            return Long.compare(o1.getOrderTime(), o2.getOrderTime());
        }
    }

    private static class courierComparator implements Comparator<Courier> {
        @Override
        public int compare(Courier o1, Courier o2) {
            return Long.compare(o1.getOrderTime(), o2.getOrderTime());
        }
    }
}
