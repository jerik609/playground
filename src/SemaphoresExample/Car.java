package SemaphoresExample;

import java.util.concurrent.Semaphore;

class Car extends Thread {
    private final Semaphore semaphore;
    private final long timeoutMs;

    public Car(String name, Semaphore semaphore, long timeoutMs) {
        super(name);
        this.semaphore = semaphore;
        this.timeoutMs = timeoutMs;
    }

    public void goShopping() {
        start();
    }

    @Override
    public void run() {
        try {
            if (!semaphore.tryAcquire()) {
                System.out.println(Thread.currentThread().getName() + " waits for parking");
                semaphore.acquire();
            }
            System.out.println(Thread.currentThread().getName() + " parked");
            Thread.sleep(timeoutMs); // shopping
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ": shopping was interrupted");
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " left");
            semaphore.release();
        }
    }
}