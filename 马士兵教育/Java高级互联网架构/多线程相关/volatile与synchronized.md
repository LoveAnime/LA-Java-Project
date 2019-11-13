[toc]

# volatile与synchronized

## volatile的作用
① 保证一个变量在多个线程可见
原理：MESI、CPU的缓存一致性协议
② 禁止指令重排序
例子：双重检查的单例模式

## 堆内存与栈内存
多个线程访问同一个变量时，java的处理方式是：主内存中存该变量，然后不同的线程都从主内存中将该变量拷贝到自己的栈内存里任何线程对该变量的修改，都是先修改栈内存的copy，改完之后再写到主内存中。

这个过程的问题是：一个线程修改了某个变量后，另一个线程并不会立马读到更改后的值。

而volatile可以保证可见性。

## 指令重排
以`Integer i =  new Integer(2);`为例，初始化一个对象时，实际上有四个步骤：申请内存、赋默认值0、赋初值2、指向对象`i=2`。

正常情况下是第一、二、三、四的顺序执行，但在指令重排之后，可能会按第一、二、四、三的顺序执行。

这样子会有一个瞬间出现`i=0`的情况，这种在某些超高并发的场景下会发生意想不到的bug。

## 双重检测的单例模式
```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    /**
     * @return 获取单例的方法
     */
    public synchronized static Singleton6 newInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    // 可以在此暂停线程测试是否可以实现真正的单例模式
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
```
双重检测的单例模式能够实现真正意义上的单例，但是如果`instance`变量不使用`volatile`修饰的话，在执行`instance = new Singleton6();`的时候，如果发生指令重排，给`instance`赋予了一个非null的默认值之后，而还未赋予真正的值之前，这个时候有另一个线程调用`newInstance()`就会的到一个错误的值。

因此双重检测的单例模式必须同时使用volatile与synchronized关键字。

当然了，这个过程很短，这个出现的概率很低，只有在特别高的并发才容易出现。

## volatile与synchronized的关系
synchronized：能保证一个复合操作的原子性，但不能阻止指令重排序。
volatile：能保证可见性、禁止指令重排。但并不能保证原子性，即多个线程同时修改同一个变量时所带来的不一致问题。

也就是说两者的功能不一样，volatile不能替代synchronized，synchronized也不能代替volatile。


## synchronized的底层实现
JDK早期：重量级锁 - OS（向操作系统申请）

后来的改进
锁升级的概念：
    我就是厕所所长 （一 二）

synchronized (object)               -- 锁住某个对象
markword 记录这个线程ID （偏向锁）  -- 第一个线程访问该对象时，只记录了这个线程的id
如果线程争用：升级为 自旋锁         -- 第二个线程来申请访问时，CPU进行类似轮询的操作去等待加锁
10次以后，升级为系统锁（重量级锁）  -- 默认轮询十次后，仍然得不到锁才向OS申请加锁

自旋锁只在用户态进行操作，只占用CPU资源
而访问操作系统不需要占用CPU资源，但加解锁需要切换到内核态

执行时间短（加锁代码），线程数少，用自旋锁
执行时间长，线程数多，用系统锁

不能给String、Integer、Long等加synchronized锁。

