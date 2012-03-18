package com.blogspot.hartsock.functionalthinking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;

public abstract class ObjectMapTest {
	ObjectMap map;
	
    final Object key1 = "key1";
    final Object key2 = "key2";
    final Object val1 = "val1";
    final Object val2 = "val2";

   
    public abstract void allocateMap();
    
    /**
     * a call to put() will add the input key/value pair to the collection.
     */
    @Test
    public void testInput() {
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

}
