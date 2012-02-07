package com.blogspot.hartsock.functionalthinking;

import org.junit.Test;

import com.blogspot.hartsock.functionalthinking.ObjectMap;
import com.blogspot.hartsock.functionalthinking.LinkedMap;

import static org.junit.Assert.*;
/**
 * Shawn Hartsock
 * hartsock@acm.org<p/>
 * <p/>
 *
 * @author Shawn Hartsock
 */
public class LinkedMapTest {

    Object key1 = "key1";
    Object key2 = "key2";
    Object val1 = "val1";
    Object val2 = "val2";

    /**
     * a call to put() will add the input key/value pair to the collection.
     */
    @Test
    public void testInput() {
        ObjectMap map = new LinkedMap();
        map.put(key1,val1);
        Object val = map.get(key1);
        assertNotNull(val);
        assertEquals(val1,val);
    }

    /*
     * <p/>
     * If the key has already been added to the collection, then the latest value will replace the existing value.
     */
    @Test
    public void testInputSameKeyTwice() {
        ObjectMap map = new LinkedMap();
        map.put(key1,val1);
        Object val = map.get(key1);
        assertNotNull(val);
        assertEquals(val1,val);
        map.put(key1,val2);
        val = map.get(key1);
        assertNotNull(val);
        assertEquals(val2,val);
    }


    /**
     * To validate we have not cheated and are actually a collection
     */
    @Test
    public void testTwoInputs() {
        ObjectMap map = new LinkedMap();
        map.put(key1,val1);
        map.put(key2,val2);
        Object val = map.get(key1);
        assertNotNull(val);
        assertEquals(val1,val);
        val = map.get(key2);
        assertNotNull(val);
        assertEquals(val2,val);
    }

    /**
     * A null "key" is not valid.
     */
    @Test(expected = Throwable.class)
    public void testANullKeyIsNotValid() {
        ObjectMap map = new LinkedMap();
        map.put(null, val1);
    }

    /**
     * A null "key" is not valid.
     */
    @Test(expected = Throwable.class)
    public void testANullKeyIsNotValidNullObject() {
        ObjectMap map = new LinkedMap();
        Object key = null;
        map.put(key,val1);
    }

    /**
     * A null "value" will remove any existing key from the collection.
     */
    @Test
    public void testInputThenDelete() {
        ObjectMap map = new LinkedMap();
        map.put(key1,val1);
        map.put(key2,val2);
        Object val = map.get(key1);
        assertNotNull(val);
        assertEquals(val1,val);
        val = map.get(key2);
        assertNotNull(val);
        assertEquals(val2,val);
        map.put(key2,null);
        val = map.get(key1);
        assertNotNull(val);
        assertEquals(val1,val);
        val = map.get(key2);
        assertNull(val);
        map.put(key1,null);
        val = map.get(key1);
        assertNull(val);
    }

    /*
     * Two keys are the same if key1.value.equals(key2.value).
     */
    @Test
    public void testTwoKeysAreTheSame() {
        Object key = "key1";
        assertTrue(key.equals(key));
        // not really much to say here.
    }
}
