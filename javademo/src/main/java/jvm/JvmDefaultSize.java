package jvm;


import java.lang.management.ManagementFactory;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2025/2/10 下午4:30
 */
public class JvmDefaultSize {


    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        //默认分配内存 物理内存的1/64
        System.out.println("总内存：" + runtime.totalMemory() / 1024 / 1024 + "M");
        System.out.println("剩余内存：" + runtime.freeMemory() / 1024 / 1024 + "M");
        //默认最大内存 物理内存的1/4
        System.out.println("最大内存：" + runtime.maxMemory() / 1024 / 1024 + "M");
        System.out.println("进程ID：" + ManagementFactory.getRuntimeMXBean().getName());
        System.out.println("当前运行环境：" + ManagementFactory.getRuntimeMXBean().getInputArguments());
        System.out.println("当前线程：" + ManagementFactory.getThreadMXBean());
        System.out.println("当前类加载器：" + ManagementFactory.getClassLoadingMXBean());
        //对象表示堆内存使用情况
        System.out.println("堆内存使用情况" + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
        ManagementFactory.getGarbageCollectorMXBeans().forEach(gc -> {
            System.out.println("垃圾收集器：" + gc.getName());
            System.out.println("垃圾收集器统计：" + gc.getCollectionCount());
            System.out.println("垃圾收集器花费时间：" + gc.getCollectionTime());
        });
    }
}
