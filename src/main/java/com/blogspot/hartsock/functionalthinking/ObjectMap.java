package com.blogspot.hartsock.functionalthinking;

/**
 * Shawn Hartsock
 * hartsock@acm.org<p/>
 * <p/>
 *
 * @author Shawn Hartsock
 */
public interface ObjectMap {
	/**
	 * <ol>
	 * <li> put a key and a value, get returns the last value put </li>
	 * <li>put a key and a null or empty value, key is no longer in the Map</li>
	 * <li>put the same key twice, the old value is over written by the new value</li>
	 * </ol>
	 * @param key
	 * @param value
	 */
    void put(Object key, Object value);
    /**
     * returns the last value put for a given key
     * @param key
     * @return
     */
    Object get(Object key);
}
