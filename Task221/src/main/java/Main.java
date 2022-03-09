import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Scanner input = new Scanner(System.in);

        System.out.println("Enter how many orders should the pizzeria process in total: ");
        int totalOrders = input.nextInt();

        Pizzeria pizzeria = new Pizzeria(totalOrders);
        Thread pizzeriaThread = new Thread(pizzeria);
        pizzeriaThread.start();

        pizzeria.acceptNewOrder();
        for (int i = 1; i < totalOrders; i++) {
            Thread.sleep((random.nextInt(4) + 1) * 1000);
            pizzeria.acceptNewOrder();
        }
        pizzeriaThread.join();
    }
}
