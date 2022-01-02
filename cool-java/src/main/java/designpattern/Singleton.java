package designpattern;


/**
 * 单例模式
 * 如何写出安全优雅的单例？
 * 懒模式 延迟初始化实例对象
 * 使用内部类?
 * <p>
 * 先构造一个私有的构造器，让外部无法进行初始化
 * 构造一个公开方法来获取实例
 * 构造一个实例对象 当被调用的时候生成实例
 * volatile and lock?
 */
public class Singleton {

    private volatile Singleton instance;

    private Singleton() {
    }

    public Singleton getInstance() {
        if (instance == null) {
            synchronized (this) {
                instance = new Singleton();
            }
        }
        return instance;
    }

    public void printVersion() {
        System.out.println("v1");
    }

}
