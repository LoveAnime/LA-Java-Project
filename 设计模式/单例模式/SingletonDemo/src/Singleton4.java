/**
 * 懒汉式：synchronized
 * 在方法上加锁
 * <p>
 * 优点：可以实现严格的单例模式
 * 缺点：synchronized修饰类名，导致并发性能下降
 */
public class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {
    }

    /**
     * @return 获取单例的方法
     */
    public synchronized static Singleton4 newInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
