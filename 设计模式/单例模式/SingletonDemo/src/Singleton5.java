/**
 * 懒汉式：synchronized
 * 在方法内部加锁
 * <p>
 * 缺点：并不能实现严格的单例模式
 * 剖析：if判断和加锁并不是原子操作，导致实际上加锁没有起到真正的作用
 */
public class Singleton5 {
    private static Singleton5 instance;

    private Singleton5() {
    }

    /**
     * @return 获取单例的方法
     */
    public synchronized static Singleton5 newInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                // 可以在此暂停线程测试是否可以实现真正的单例模式
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
