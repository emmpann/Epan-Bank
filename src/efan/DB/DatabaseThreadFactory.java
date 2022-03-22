package efan.DB;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseThreadFactory implements ThreadFactory{

    final AtomicInteger poolNumber = new AtomicInteger(1);
    @Override
    public Thread newThread(Runnable runnable) {

        // make a thread contract
        Thread thread = new Thread(runnable, "Database-Conntection-" + poolNumber.getAndIncrement() + "-thread");
        thread.setDaemon(true);
        return thread;
    }
}
