package collection;


import java.util.*;

/**
 * 查看hashMap源码 理解几个重要的问题？
 * 1.为什么说hashMap的大小必须是2的n次方？
 * 获取下标的方式是 hash & (length - 1) //length = 2^n 的时候 2^n-1 的二进制表示的时候是1111..11全都是1  这样取 与 运算 获取到下标
 * 这种获取下标的方式 当其进行扩容的时候，直接扩成了2倍， 则新计算出来的下标， 要么和原来的下标相同  要么是原来下标的2倍
 * 根据这个就很方便理解 resize后transfer的代码，即把原来链表拆分成两个链表，一个是低位（原来相同的）和一个高位的链表。
 *
 * 2.hashMap的底层实现是什么？
 * 1.7 是数组加链表的经典实现
 * 1.8 是数组加链表和红黑树的实现
 * 为什么会改成红黑树？ 加大搜索效率, 当table size>64 and linklist >8的时候开始 把链表变成树
 *
 * 3.负载因子和初始容量对hashMap有什么影响？
 *   负载因子和初始容量影响着hashmap的性能，时间空间的一个平衡。
 *   load fact设置高一些，空间利用率高一些，但是因为碰撞产生的链表的长度就高，造成get/put等操作就更耗时
 *   所以如果大概知道数据量，可以把初始化容量设置得接近它，这样可以避免多次resize,提高效率。
 *
 * 4.假设一开始hashMap有三个buckets,在添加元素的时候，添加了三个元素，但是都添加到了桶1，请问这时候，是否达到了threadhold？
 *  和这个无关，就是计算公式， 三个buckets肯定被转成了4个 (tableforsize方法2的n次方）
 *  都添加到了1 仍然是3个 3/4 0.75
 *
 * 5.1.8中hashMap()的 hash函数是怎么实现的？为什么这么实现可以高效利用空间减少碰撞？
 * *  hash =  (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
 * 高位传播到低位 然后再与原值进行异或 这样高位和低位都参与到了运算
 * 让下面计算 索引位置的时候更均匀
 * 避免特殊情况下 高位的索引的空间一直空着的情况
 *
 *    求下标方法如下：
 *    (n - 1) & hash
 *    11111 & 10001
 *    与运算 全1得1 其余得0
 *    如果算得的hash值 大于 容量值 则只会用到低位的值  如果小于 则是其原值
 *
 * 6.hashMap的扩容是怎么做的？
 * 扩成二倍，关键是原有桶中的元素会发生什么变化？还在原来的桶中，还是换捅？怎么换的？
 *
 * 7. 1.8 解决了 1.7中的死锁问题 jdk1.7 coolshell hashmap 可以搜到死锁的问题
 * 1.7在resize的tansfer中没有保留元素在链表中的顺序，使用的是头插法，在多线程环境中，可能会造成链表环，造成在get的时候死循环。
 * 在1.8中已经preserve element order了，使用的是尾插法。
 *

 *  bilibili 硬核空间饥人谷的编程讲解挺好的 有助于理解
 *
 *  如果你需要在put到hashmap中的元素保持put时的顺序，可以使用LInkedHashMap
 *  TreeMap 使用在什么场景？ 你想你输入到map中的数据key是有序的时候，可以使用
 *
 */
public class MyHashMap {

    public static void main(String[] args) {

        HashMap<String,String> map = new HashMap<>(4);
        List<String> list = Arrays.asList("Aa","BB","C#");
        for(String str: list){
            System.out.println(str.hashCode());
            map.put(str,str);
        }

        //测试treeMap
        Map<String,String> treeMap = new TreeMap<>();
        treeMap.put("z","zz");
        treeMap.put("x","xx");
        treeMap.put("y","yy");
        treeMap.put("a","aa");
        treeMap.forEach((x,y) -> System.out.println(x)); //可以看到key的输出顺序是 a/x/y/z

        //linkedhashMap因为是放在链表中的，保持了原来的顺序
        Map<String,String> lmap = new LinkedHashMap<>();
        lmap.put("z","zz");
        lmap.put("x","xx");
        lmap.put("y","yy");
        lmap.put("a","aa");
        lmap.forEach((x,y) -> System.out.println(x)); //可以看到key的输出顺序是 z/x/y/z 保持了输入时候的顺序

        System.out.println(8 >>> 2);
        System.out.println(8 ^ (8 >>> 2));

    }

}
