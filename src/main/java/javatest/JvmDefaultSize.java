package javatest;


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
    }


}
