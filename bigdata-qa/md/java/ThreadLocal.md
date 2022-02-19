#### ThreadLocal
我们对于ThreadLocal的get, set，remove的操作结果
都是针对当前线程Thread实例的threadLocals存，取，删除操作。
##### 应用场景
Spring 声明式事务
