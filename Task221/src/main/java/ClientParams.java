/**
 * Class that holds a client's parameters serialized from .json file.
 */
public class ClientParams {
    private int totalOrders;

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public ClientParams() {

    }

    public ClientParams(int totalOrders) {
        this.totalOrders = totalOrders;
    }
}
