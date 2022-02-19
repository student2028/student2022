package thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程并发之一 线程使用线程自己的变量 ThreadLocal变量
 * ThreadLocal 内存泄露？
 * Thread
 * ThreadLocalMap
 * 强引用：不会被回收
 * 弱引用 是否被回收取决于内存,如果内存足够，则保留，否则就放弃 胡书敏讲到一个使用示例就是 使用hashMap<key, weakref > 来做缓存，
 * threadLocalMap 中也是使用的weakRef
 * 软引用：垃圾回收触发时就会回收
 * 虚引用：当对象被回收时就会触发一个通知
 * <p>
 * ThreadLocalMap的 使用了ThreadLocal的弱引用
 * 如果map不存在，则创建并把threadLocal作为key,进行存储
 * 如果map存在，则直接存储threadLocal作为key进行存储，
 * 我们知道 threadLocal本身并不存储值，而是存在线程的map中
 * threadlocal为null的键在threadLocalMap当中,导致value无法被释放
 * 主动使用threadLocal中的remove方法
 *
 * Java世界里 只有值传递 引用传递也是传递引用的值
 */
public class MyThreadLocalTest {

    public static final Integer SIZE = 500;
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES,
            //new LinkedBlockingDeque<>()
            new ArrayBlockingQueue(2)
    );

    static class Student {
        private byte[] locale = new byte[1024 * 1024 * 5];
    }

    static ThreadLocal<Student> local = new ThreadLocal<>();

    public static void main(String[] args) {
        try {
            for (int i = 0; i < SIZE; i++) {
                executor.execute(() -> {
                    local.set(new Student());
                    System.out.println("start to do job..." + Thread.currentThread().getName());
                    //local.remove();

                });
                Thread.sleep(100);
                //local = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
