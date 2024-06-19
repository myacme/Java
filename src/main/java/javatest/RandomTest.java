package javatest;


import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/6/19 上午9:47
 */
public class RandomTest {

    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        Random random = new Random();
        for (int i = 0; i < 100000000; i++) {
            random.nextInt(10000);
        }
        System.out.println(Duration.between(now, LocalTime.now()).toMillis());
        LocalTime now1 = LocalTime.now();
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int i = 0; i < 100000000; i++) {
            current.nextInt(10000);
        }
        System.out.println(Duration.between(now1, LocalTime.now()).toMillis());
    }
}
