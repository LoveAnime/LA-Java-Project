/**
 * Created by 帝歌恋雪 on 2019-10-11.
 */

/**
 * 枚举
 * <p>
 * 优点：不仅可以解决多线程的单例问题，也可以防止反序列化，是《effective java》作者推荐的最完美写法！
 *
 * ps：
 * 1、java支持开发者通过加载class文件，采用反射的方式，new一个对象。
 *    所以即使使用1~6的方法，给构造器设置了private属性，也可以采用反射的方式，实现多个实例。
 * 2、此外，一个类对象反序列化之后，和原对象是两个实例。
 * 3、而枚举类一没有构造方法，二序列化与被反序列化是同一个实例，是语法上最完美的实现单例的方式。
 */
public enum Singleton7 {
    INSTANCE;
}
