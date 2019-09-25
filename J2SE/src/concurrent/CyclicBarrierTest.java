package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 陌上丶天琊
 * @date 2019-9-24 20:18
 * @description
 */
public class CyclicBarrierTest {
    private final static int THREAD_NUM = 10;

    public static void main(String[] args) {
        CyclicBarrier lock = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("大家都准备完成了");
            }
        });
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            exec.submit(new CyclicBarrierTest.CountdownLatchTask(lock, "Thread-" + i));
        }
        exec.shutdown();

    }

    static class CountdownLatchTask implements Runnable {
        private final CyclicBarrier lock;
        private final String threadName;

        CountdownLatchTask(CyclicBarrier lock, String threadName) {
            this.lock = lock;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1; i++) {
                System.out.println(threadName + " 准备完成");
                try {
                    lock.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " 执行完成");
            }
        }
    }
}
