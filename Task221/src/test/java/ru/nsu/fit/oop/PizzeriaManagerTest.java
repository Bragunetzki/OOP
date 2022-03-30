package ru.nsu.fit.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

class PizzeriaManagerTest {
    @Test
    public void pizzeriaManagerWarehouseTest() throws InterruptedException {
        Warehouse warehouse = new Warehouse(1);
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            bakers.add(new Baker(warehouse, 1000));
            bakers.get(i).setCurrentOrder(i+1);
            couriers.add(new Courier(warehouse, 1000, 1));
        }

        ArrayList<Callable<Void>> workers = new ArrayList<>(bakers);
        workers.addAll(couriers);
        ExecutorService service = Executors.newFixedThreadPool(20);
        service.invokeAll(workers);
        service.shutdown();
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        assertTrue(warehouse.isEmpty());
    }
}