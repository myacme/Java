package javatest;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Steam.filter  与 list.removeAll  性能对比
 *
 * @author ljx
 * @version 1.0.0
 * @create 2025/4/11 下午3:44
 */
public class Filter_RemoveAll {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        List<Integer> collect = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        long start = System.currentTimeMillis();
        list.removeAll(collect);
        long end = System.currentTimeMillis();
        System.out.println("removeAll耗时：" + (end - start));
        start = System.currentTimeMillis();
        List<Integer> collect1 = list.stream().filter(i -> i % 2 != 0).collect(Collectors.toList());
        end = System.currentTimeMillis();
        System.out.println("stream耗时：" + (end - start));
    }
}
