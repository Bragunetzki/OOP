import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Class that manages client threads.
 */
public class ClientManager implements Runnable {
    private List<Client> clients;
    private Pizzeria parentPizzeria;

    /**
     * Basic constructor. Reads all clients from a .json file.
     * @param parentPizzeria - the pizzeria that the managed clients will interact with.
     */
    public ClientManager(Pizzeria parentPizzeria, String file) {
        this.parentPizzeria = parentPizzeria;
        clients = new ArrayList<>();
        List<ClientParams> clientParamsList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            clientParamsList = mapper.readValue(new File(file), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (ClientParams clientParams : clientParamsList) {
            clients.add(new Client(parentPizzeria.getOrderQueue(), clientParams.getTotalOrders()));
        }
    }

    /**
     * Runs the ClientManager. It will invoke all clients and wait for them to terminate.
     */
    @Override
    public void run() {
        ExecutorService service = Executors.newFixedThreadPool(clients.size());

        try {
            service.invokeAll(clients);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        parentPizzeria.getOrderQueue().disable();
    }
}
