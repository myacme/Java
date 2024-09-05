package javatest;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/5 上午10:34
 */
public class 循环字节码对比 {
    public void forTest() {
        for (; ; ) {
            System.out.println("循环");
        }
    }

    public void whileTest() {
        while (true) {
            System.out.println("循环");
        }
    }
}
