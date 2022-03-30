package ru.nsu.fit.oop;

import java.io.File;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Pizzeria pizzeria = new Pizzeria(new File("pizzeria.json"), new File("bakers.json"), new File("couriers.json"));
        ClientManager clientManager = new ClientManager(pizzeria, "clients.json");
        PizzeriaManager pizzeriaManager = new PizzeriaManager(pizzeria);
        Thread pizzeriaThread = new Thread(pizzeriaManager);
        Thread clientThread = new Thread(clientManager);

        pizzeriaThread.start();
        clientThread.start();

        pizzeriaThread.join();
        clientThread.join();
    }
}
