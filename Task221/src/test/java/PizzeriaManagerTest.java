import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PizzeriaManagerTest {
    @Test
    public void pizzeriaManagerSmallWarehouseTest() throws InterruptedException {
        Pizzeria pizzeria = new Pizzeria(new File("smallPizzeria.json"),new File("bakers.json"), new File("couriers.json"));
        ClientManager clientManager = new ClientManager(pizzeria, "clients.json");
        PizzeriaManager pizzeriaManager = new PizzeriaManager(pizzeria);
        Thread pizzeriaThread = new Thread(pizzeriaManager);
        Thread clientThread = new Thread(clientManager);

        pizzeriaThread.start();
        clientThread.start();

        pizzeriaThread.join();
        clientThread.join();
        assert(pizzeria.getWarehouse().isEmpty());
    }
}