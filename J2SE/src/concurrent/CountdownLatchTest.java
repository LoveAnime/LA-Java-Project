package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 陌上丶天琊
 * @date 2019-9-24 20:12
 * @description
 */
public class CountdownLatchTest {
    private final static int THREAD_NUM = 10;

    public static void main(String[] args) {
        CountDownLatch lock = new CountDownLatch(THREAD_NUM);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            service.submit(new CountdownLatchTask(lock, "Thread-" + i));
        }
        try {
            lock.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("大家都执行完成了，做总结性工作");
        service.shutdown();
    }

    static class CountdownLatchTask implements Runnable {
        private final CountDownLatch lock;
        private final String threadName;

        CountdownLatchTask(CountDownLatch lock, String threadName) {
            this.lock = lock;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.println(threadName + " 执行完成");
            lock.countDown();
        }
    }
}
