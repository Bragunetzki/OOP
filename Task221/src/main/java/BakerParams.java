/**
 * Class that holds a baker's parameters serialized from .json file.
 */
public class BakerParams {
    private int orderTime;

    public int getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(int orderTime) {
        this.orderTime = orderTime;
    }

    public BakerParams() {

    }

    public BakerParams(int orderTime) {
        this.orderTime = orderTime;
    }

}
