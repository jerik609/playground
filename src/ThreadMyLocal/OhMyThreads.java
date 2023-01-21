package ThreadMyLocal;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class OhMyThreads {

    public static class MyThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            System.out.println("Creating a new thread!");
            return new Thread(r, String.valueOf(UUID.randomUUID()));
        }
    }

    public static class MyRunner implements Runnable {
        private static ThreadLocal<String> name = new ThreadLocal<>();
        private static ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);

        MyRunner(String oops, int num) {
            name.set(oops);
            counter.set(num);
        }

        public int getCounter() {
            return counter.get();
        }

        @Override
        public void run() {
            counter.set(counter.get() + 1);
            System.out.println(Thread.currentThread().getName() + " -> previous name: " + name.get() + " " + counter.get()); // != null ? name.get() : Thread.currentThread().getName()
            System.out.println(Thread.currentThread().getName() + " -> setting name to: " + Thread.currentThread().getName() + " " + counter.get());
            name.set(Thread.currentThread().getName());
            if ((int)(Math.random() * 100 / 10) < 3) {
                System.out.println(Thread.currentThread().getName() + " -> CRASHING! " + counter.get());
                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        final var execServ = Executors.newCachedThreadPool(new MyThreadFactory());
                //newFixedThreadPool(4);
                //newCachedThreadPool(new MyThreadFactory());


        var funner = new MyRunner("mooops", 7777);

        for (int i = 1; i < 100; i++) {
            execServ.submit(funner);
        }

        System.out.println("HHHHHHHHHHHHHHHHHHHHHHH: " + funner.getCounter());

        execServ.shutdown();
    }

}
