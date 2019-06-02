package com.test.jinke.hashMap;


import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K,V> implements MyMap<K, V> {
	/**初始哈希表大小，必须是2的幂次方*/
    private static int DEFAULT_INITIAL_CAPACITY = 4;
	/**默认最大哈希表容量,2^30*/
    private static int MAXIMUM_CAPACITY = 1 << 30;
    /**哈希表容量*/
    private int capacity;
    /**默认装载因子*/
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
    /**指出特定的装载因子*/
    private float loadFactorThreshold;
    /**entry数量*/
    private int size = 0;
    
    /**定义哈希表*/
    LinkedList<MyMap.Entry<K, V>>[] table;
    
    
    /**使用默认容量和装载因子的构造函数*/
    public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY,DEFAULT_MAX_LOAD_FACTOR);
	}
    
    /**指定初始容量*/
    public MyHashMap(int initialCapacity) {
    	this(initialCapacity,DEFAULT_MAX_LOAD_FACTOR);
    }
    
    /**指定初始容量及装载因子*/
    public MyHashMap(int initialCapacity,float loadFactorThreshold) {
    	if(initialCapacity > MAXIMUM_CAPACITY)
    		this.capacity = MAXIMUM_CAPACITY;
    	else
    		this.capacity = trimToPowerOf2(initialCapacity);
    	
    	this.loadFactorThreshold = loadFactorThreshold;
    	table = new LinkedList[capacity];
    }
    
    /**Return a power of 2 for initialCapacity*/
	private int trimToPowerOf2(int initialCapacity) {
		int capacity = 1;
		while(capacity < initialCapacity) {
			capacity <<= 1;
		}
		
		return capacity;
	}

	/**Remove all of the entries from this map*/
	@Override
	public void clear() {
		size = 0;
		removeEntries();
	}

	private void removeEntries() {
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				table[i].clear();
			}
		}
	}

	/**Return true if the specified key is in the map*/
	@Override
	public boolean containKey(K key) {
		if(get(key) != null)
			return true;
		else 
		    return false;
	}

	/**Return true if this map contains the value*/
	@Override
	public boolean containValue(V value) {
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				LinkedList<Entry<K, V>> bucket = table[i];
				for(Entry<K, V> entry : bucket) {
					if(entry.getValue().equals(value))
						return true;
				}
			}
		}
		
		return false;
	}

	/**Return a set of entries in the map*/
	@Override
	public Set<Entry<K, V>> entrySet() {
		java.util.Set<Entry<K, V>> set = new java.util.HashSet<>();
		
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				LinkedList<Entry<K, V>> bucket = table[i];
				for(Entry<K, V> entry : bucket)
					set.add(entry);
			}
		}
		return set;
	}

	/**Return the value that mathces the specified key*/
	@Override
	public V get(K key) {
		int bucketIndex = hash(key.hashCode());
		if(table[bucketIndex] != null) {
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for(Entry<K, V> entry : bucket)
				if(entry.getKey().equals(key))
					return entry.getValue();
		}
		return null;
	}

	/**hash fucntion*/
	private int hash(int hashCode) {
		return supplementalHash(hashCode) & (capacity - 1);
	}

	/**确保hash值均匀分布*/
	private int supplementalHash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	/**Return a set consisting of the keys in this map*/
	@Override
	public Set<K> keySet() {
		java.util.Set<K> set = new java.util.HashSet<>();
		
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				LinkedList<Entry<K, V>> bucket = table[i];
				for(Entry<K, V> entry : bucket) {
					set.add(entry.getKey());
				}
			}
		}
		return set;
	}

	/**Add an entry(key,value) into the map*/ 
	@Override
	public V put(K key, V value) {
		if(get(key) != null) {
			int bucketIndex = hash(key.hashCode());
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for(Entry<K, V> entry : bucket) {
				if(entry.getKey().equals(key)) {
					V oldValue = entry.getValue();
					entry.value = value;
					return oldValue;
				}
			}
		}
		
		//Check load factor
		if(size >= capacity * loadFactorThreshold) {
			if(capacity == MAXIMUM_CAPACITY)
				throw new RuntimeException("Exceeding maximum capacity");
			
			rehash();
		}
		
		int bucketIndex = hash(key.hashCode());
		
		//Create a linked list for the bucket if not already created
		if(table[bucketIndex] == null) {
			table[bucketIndex] = new LinkedList<>();
		}
		
		table[bucketIndex].add(new MyMap.Entry<K, V>(key, value));
		
		size++;
		return value;
	}

	/**Rehash the map*/
	private void rehash() {
		java.util.Set<Entry<K, V>> set = entrySet();
		capacity <<= 1;
		table = new LinkedList[capacity];
		size = 0;
		
		for(Entry<K, V> entry : set) {
			put(entry.getKey(), entry.getValue());
		}
		
	}

	/**Remove the entries for the specified key*/
	@Override
	public void remove(K key) {
		int bucketIndex = hash(key.hashCode());
		
		if(table[bucketIndex] != null) {
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for(Entry<K, V> entry : bucket) {
				if(entry.getKey().equals(key)) {
					bucket.remove(entry);
					size--;
					break;
				}
			}
		}
	}

	/**Return the number of entries in this map*/
	@Override
	public int size() {
		return size;
	}

	/**Return a set consisting of the valuse in this map*/
	@Override
	public Set<V> values() {
		java.util.Set<V> set = new java.util.HashSet<>();
		
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				LinkedList<Entry<K, V>> bucket = table[i];
				for(Entry<K, V> entry : bucket) {
					set.add(entry.getValue());
				}
			}
		}
		return null;
	}

	/**Return true if this map contains no entries*/
	@Override
	public boolean isEmpty() {		
		return size == 0;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null && table[i].size() > 0) {
				for(Entry<K, V> entry : table[i])
					builder.append(entry);
			}
		}
		
		builder.append("]");
		return builder.toString();
	}

}
