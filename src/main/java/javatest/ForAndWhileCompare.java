package javatest;

import java.util.concurrent.TimeUnit;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2025/4/15 上午10:26
 */
public class ForAndWhileCompare {

    private static final int size = 1000000000;

    public static void forTest() {
        int i = 0;
        for (; ; ) {
            i++;
            if (i == size) {
                break;
            }
        }
    }

    public static void whileTest() {
        int i = 0;
        while (true) {
            i++;
            if (i == size) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        // 获取纳秒时间戳
        long start = System.nanoTime();
        // 转换为微秒
        long microSeconds = TimeUnit.NANOSECONDS.toMicros(start);
        forTest();
        long end = System.nanoTime();
        System.out.println("for循环执行时间：" + (end - start) + "ms");
        start = System.nanoTime();
        whileTest();
        end = System.nanoTime();
        System.out.println("while循环执行时间：" + (end - start) + "ms");
    }
}
