package javatest;


import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ljx
 * @create 2021/4/13
 * @since 1.0.0
 */

public class MapTest {

	static class Node<K, V> implements Map.Entry<K, V> {
		final int hash;
		final K key;
		V value;
		Node<K, V> next = null;

		Node(int hash, K key) {
			this.hash = hash;
			this.key = key;
		}


		@Override
		public K getKey() {
			return null;
		}

		@Override
		public V getValue() {
			return null;
		}

		@Override
		public V setValue(V value) {
			return null;
		}
	}
}