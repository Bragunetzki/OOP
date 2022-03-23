/**
 * Class that holds a client's parameters serialized from .json file.
 */
public class CourierParams {
    private int orderTime;
    private int baggageCap;

    public int getOrderTime() {
        return orderTime;
    }

    public int getBaggageCap() {
        return baggageCap;
    }

    public void setOrderTime(int orderTime) {
        this.orderTime = orderTime;
    }

    public void setBaggageCap(int baggageCap) {
        this.baggageCap = baggageCap;
    }

    public CourierParams() {

    }

    public CourierParams(int baggageCap, int orderTime) {
        this.baggageCap = baggageCap;
        this.orderTime = orderTime;
    }
}
