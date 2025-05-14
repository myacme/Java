package javatest;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2025/3/27 下午2:25
 */
public class Java8数组分组求和 {

    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Test test = new Test();
            test.setAreaunit("111");
            test.setPtype("name" + i);
            test.setSum1(1 + i);
            test.setSum2(2 + i);
            test.setSum3(3 + i);
            list.add(test);
        }
        Map<String, Test> collect1 = list.stream().collect(Collectors.toMap(
                Test::getAreaunit,
                Function.identity(),
                (existing, replacement) -> {
                    existing.setSum1(existing.getSum1() + replacement.getSum1());
                    existing.setSum2(existing.getSum2() + replacement.getSum2());
                    existing.setSum3(existing.getSum3() + replacement.getSum3());
                    return existing;
                }
        ));
        List<Test> collect = collect1.values().stream().collect(Collectors.toList());
        System.out.println();
    }

    static class Test {
        private String areaunit;


        private String ptype;

        private int sum1;
        private int sum2;
        private int sum3;

        public String getAreaunit() {
            return areaunit;
        }

        public void setAreaunit(String areaunit) {
            this.areaunit = areaunit;
        }

        public String getPtype() {
            return ptype;
        }

        public void setPtype(String ptype) {
            this.ptype = ptype;
        }

        public int getSum1() {
            return sum1;
        }

        public void setSum1(int sum1) {
            this.sum1 = sum1;
        }

        public int getSum2() {
            return sum2;
        }

        public void setSum2(int sum2) {
            this.sum2 = sum2;
        }

        public int getSum3() {
            return sum3;
        }

        public void setSum3(int sum3) {
            this.sum3 = sum3;
        }
    }
}
