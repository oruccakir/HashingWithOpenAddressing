import java.util.Collection;

public interface MapInterface<K, V> {
    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with the specified key, or null if there was no mapping for the key
     */
    V put(K key, V value);

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    V get(K key);

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with the specified key, or null if there was no mapping for the key
     */
    V remove(K key);

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    boolean containsKey(K key);

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    int size();

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings
     */
    boolean isEmpty();

    /**
     * Removes all of the mappings from this map.
     */
    void clear();

    /**
     * Returns a set view of the keys contained in this map.
     *
     * @return a set view of the keys contained in this map
     */
    SetInterface <K> keySet();

    /**
     * Returns a collection view of the values contained in this map.
     *
     * @return a collection view of the values contained in this map
     */
    Collection<V> values();

    /**
     * Returns a set view of the key-value mappings contained in this map.
     *
     * @return a set view of the key-value mappings contained in this map
     */
    SetInterface<Entry<K, V>> entrySet();
}

