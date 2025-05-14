package javatest;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/6/13 上午10:12
 */
public class 类泛型 {

	private
	Map<Class<?>, Object> favorites = new HashMap<>();

	public <T> void putFavorite(Class<T> type, T instance) {
		favorites.put(Objects.requireNonNull(type), instance);
	}

	public <T> T getFavorite(Class<T> type) {
		return type.cast(favorites.get(type));
	}

	public static void main(String[] args) {
		类泛型 test = new 类泛型();
		test.putFavorite(String.class, "Java");
		String favorite = test.getFavorite(String.class);
		System.out.println(favorite);
	}
}
