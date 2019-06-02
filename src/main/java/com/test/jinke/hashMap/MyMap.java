package com.test.jinke.hashMap;


public interface MyMap<K,V> {
    /**Remove all of the entries from this map*/
    public void clear();
    /**是否存在特定的键值*/
	public boolean containKey(K key);
	/**是否存在特定的值*/
	public boolean containValue(V value);
	/**Return a set of entries in the map*/
	public java.util.Set<Entry<K, V>> entrySet();
	/**返回特定的键对应的值*/
	public V get(K key);
	/**是否为空*/
	public boolean isEmpty();
	/**返回键值集合*/
	public java.util.Set<K> keySet();
	/**Add a entry(key,value) into the map*/
	public V put(K key,V value);
	/**Remove the number for the specified key*/
	public void remove(K key);
	/**返回Entry个数*/
	public int size();
	/**返回map中的所有的值集合*/
	public java.util.Set<V> values();

	/**定义内部类Entry*/
	public static class Entry<K,V>{
		K key;
		V value;
		
		public Entry(K key,V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return "[" + key + ", " + value + "]";
		}
	}
}
