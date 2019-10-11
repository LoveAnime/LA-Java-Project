/**
 * 懒汉式：lazy loading
 * <p>
 * 优点：按需初始化
 * 缺点：多线程访问下不能保证严格单例
 */
public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {
    }

    /**
     * @return 获取单例的方法
     */
    public static Singleton3 newInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
