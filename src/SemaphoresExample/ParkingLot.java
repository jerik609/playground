package SemaphoresExample;

import java.util.concurrent.Semaphore;

public class ParkingLot {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2);
        for (int i = 0; i < 50; ++i) {
            Car car = new Car("Car #" + i, sem, 3000L);
            car.goShopping();
        }
    }
}
