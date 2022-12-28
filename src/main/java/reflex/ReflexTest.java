package reflex;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/4/20 14:35
 */

public class ReflexTest {
	public static void main(String[] args) throws Exception {
		ReflexBean reflexBean = new ReflexBean(1);
		System.out.println(reflexBean.toString());
		Class aClass = reflexBean.getClass();
		Class aClass1 = reflexBean.getClass();
		Class aClass2 = Class.forName("reflex.ReflexBean");
		System.out.println("类名：" + aClass.getName());
		Constructor[] constructors = aClass.getConstructors();
		System.out.print("所有公有的构造器：");
		for (Constructor constructor : constructors) {
			System.out.println(constructor);
		}
		Constructor[] declaredConstructors = aClass.getDeclaredConstructors();
		System.out.print("所有的构造器：");
		for (Constructor declaredConstructor : declaredConstructors) {
			System.out.println(declaredConstructor);
		}
		Constructor constructor = aClass.getConstructor(String.class);
		Object instance = constructor.newInstance("反射");
		System.out.println(instance.toString());
		Field[] fields = aClass.getFields();
		System.out.print("所有公有属性：");
		for (Field field : fields) {
			System.out.println(field);
		}
		Field[] declaredFields = aClass.getDeclaredFields();
		System.out.print("所有属性：");
		for (Field declaredField : declaredFields) {
			System.out.println(declaredField);
		}
		Field anInt = aClass.getDeclaredField("anInt");
		anInt.setAccessible(true);
		System.out.print("通过反射设置属性值：");
		anInt.set(instance,111111);
		System.out.println(anInt.get(instance));
		Method getAnInt = aClass.getDeclaredMethod("getAnInt");
		getAnInt.setAccessible(true);
		Object invoke = getAnInt.invoke(instance);
		System.out.print("通过反射调用方法：");
		System.out.println(invoke.toString());

		//绕过泛型检查
		List<String> list = new ArrayList<>();
		list.add("sss");
		list.add("ddd");
		Method add = list.getClass().getMethod("add", Object.class);
		add.invoke(list, 111);
		System.out.println(Arrays.toString(list.toArray()));
		Field string = aClass.getDeclaredField("string");
		System.out.print("获取所有注解：");
		Annotation[] annotations = string.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
		}
		Value annotation = string.getAnnotation(Value.class);
		System.out.print("获取注解值：");
		System.out.println(annotation.value());
		string.setAccessible(true);
		string.set(reflexBean, annotation.value());
		System.out.print("设置注解到属性上：");
		System.out.println(reflexBean.toString());

	}
}