import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 07.02.2017.
 */
public class SheepManager {

    private AtomicInteger sheepCount;

//    public SheepManager(){
//        synchronized (this) {
//            sheepCount = new AtomicInteger(0);
//        }
//    }

        public SheepManager(){
            sheepCount = new AtomicInteger(0);
    }

    public synchronized void incrementAndReport() {
            System.out.println((sheepCount.incrementAndGet()) + " ");
    }

    public static void main(String[] args) {

        ExecutorService service = null;
        try {

            service = Executors.newFixedThreadPool(20);

            final SheepManager manager = new SheepManager();

                for (int i = 0; i < 10; i++) {
                    service.submit(() -> manager.incrementAndReport());
            }

        } finally {
            if (service != null) service.shutdown();
        }
    }
}