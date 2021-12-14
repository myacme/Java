import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Demo {

    public static void main(String[] args) {
        Map<Integer,String > student = new TreeMap<>((o1,o2)-> o2 - o1);
        student.put(1, "1");
        student.put(3, "3");
        student.put(4, "4");
        student.put(2, "2");
        student.put(5, "5");
        Set<Map.Entry<Integer,String>> entrySet = student.entrySet();
//        for (Map.Entry<Integer,String> entry:entrySet){
//            System.out.println(entry.getKey()+"--"+entry.getValue());
//        }
        entrySet.forEach(k-> System.out.println(k));
        int i= 0b011;
        System.out.println(i);
        String a="abc";
        String b=a;
        System.out.println(a==b);
    }


}

