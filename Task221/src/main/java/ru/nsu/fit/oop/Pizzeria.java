package ru.nsu.fit.oop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class that simulates a pizzeria with a list of bakers, a list of couriers, a queue of orders and a warehouse.
 */
public class Pizzeria {
    private final List<Baker> bakers;
    private final List<Courier> couriers;
    private Warehouse warehouse;
    private final OrderQueue orderQueue;
    private int ordersTotal;

    public int getOrdersTotal() {
        return ordersTotal;
    }

    public void setOrdersTotal(int total) {
        ordersTotal = total;
    }

    /**
     * @return - returns the pizzeria's warehouse object.
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * @return - returns the pizzeria's order queue object.
     */
    public OrderQueue getOrderQueue() {
        return orderQueue;
    }

    /**
     * Return the number of pizzeria workers, which is the sum of the number of bakers and the number of couriers.
     * @return - the number of workers.
     */
    public int numberOfWorkers() {
        return bakers.size() + couriers.size();
    }

    /**
     * @return - returns the list of pizzeria bakers.
     */
    public List<Baker> getBakers() {
        return bakers;
    }

    /**
     * @return - returns the list of pizzeri couriers.
     */
    public List<Courier> getCouriers() {
        return couriers;
    }

    /**
     * Basic constructor. The ru.nsu.fit.oop.Pizzeria initializes a new orderQueue, a new ru.nsu.fit.oop.Warehouse of specified size,
     * all couriers and bakers. It also sorts the lists of bakers and couriers by efficiency.
     * @param pizzeriaFile - handle of .json file containing pizzeria parameters.
     * @param bakersFile - handle of .json file containing baker parameters.
     * @param couriersFile - handle of .json file containing courier parameters.
     */
    public Pizzeria(File pizzeriaFile, File bakersFile, File couriersFile) {
        orderQueue = new OrderQueue();
        ordersTotal = 0;
        List<BakerParams> bakerParamsList = new ArrayList<>();
        List<CourierParams> courierParamsList = new ArrayList<>();
        bakers = new ArrayList<>();
        couriers = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            PizzeriaParams params = mapper.readValue(pizzeriaFile, PizzeriaParams.class);
            warehouse = new Warehouse(params.getWareHouseSize());
            bakerParamsList = mapper.readValue(bakersFile, new TypeReference<>() {
            });
            courierParamsList = mapper.readValue(couriersFile, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (BakerParams bakerParams : bakerParamsList) {
            bakers.add(new Baker(warehouse, bakerParams.getOrderTime()));
        }
        for (CourierParams courierParams : courierParamsList) {
            couriers.add(new Courier(warehouse, courierParams.getOrderTime(), courierParams.getBaggageCap()));
        }

        bakers.sort(Comparator.comparingInt(Baker::getOrderTime));
        couriers.sort(Comparator.comparingInt(Courier::getOrderTime));
    }

    /**
     * Class that is used for storing parameters serialized from .json file.
     */
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
}
