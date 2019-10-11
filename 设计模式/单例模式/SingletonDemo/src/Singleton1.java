/**
 * 恶汉式
 * 类加载到内存后，就实例化一个单例，JVM保证严格实现单例
 * <p>
 * 优点：简单实用，推荐使用！
 * 缺点：不管用到与否，类装载时就完成实例化
 * <p>
 * ps:
 * 1、类加载到内存通过Class.forName()实现
 * 2、JVM加载一个class只会加载一次
 */
public class Singleton1 {
    private static final Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    /**
     * @return 获取单例的方法
     */
    public static Singleton1 newInstance() {
        return instance;
    }
}
