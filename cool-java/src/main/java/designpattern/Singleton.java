package designpattern;


/**
 * 单例模式
 * 如何写出安全优雅的单例？
 * 懒模式 延迟初始化实例对象
 * 使用静态内部类可以实现类似的延迟加载的实例
 * <p>
 * 先构造一个私有的构造器，让外部无法进行初始化
 * 构造一个公开方法来获取实例
 * 构造一个实例对象 当被调用的时候生成实例
 * volatile and lock?
 * 单例可以引出java的好多知识点 多线程并发 内存模型  类加载等
 * 为什么私有的静态内部类不会在类加载的时候加载？
 *
 *
 */
public class Singleton {

    private static volatile Singleton INSTANCE;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (INSTANCE != null) return INSTANCE;
        synchronized (Singleton.class) {
            if (INSTANCE == null)
                INSTANCE = new Singleton();
            return INSTANCE;
        }
    }

    public void printVersion() {
        System.out.println("v1");
    }


//    public static Singleton getInstance2() {
//        return SingletonHelper.INSTANCE;
//    }
//
//    //private inner static class that contains the instance of the singleton class.
//    //When the singleton class is loaded, SingletonHelper class is not loaded into memory
//    //    and only when someone calls the getInstance method,
//    //this class gets loaded and creates the Singleton class instance.

//    private static class SingletonHelper {
//        private static final Singleton INSTANCE = new Singleton();
//    }

}
