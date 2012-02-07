package com.blogspot.hartsock.functionalthinking;

import org.junit.Test;

import com.blogspot.hartsock.functionalthinking.ObjectMap;
import com.blogspot.hartsock.functionalthinking.ThreadSafeLinkedMap;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Shawn Hartsock
 * hartsock@acm.org<p/>
 * <p/>
 *
 * @author Shawn Hartsock
 */
public class ThreadSafeLinkedMapTest {
    Integer count;
    Lock testLock = new Lock() {
            public void lock() {
                if(count == null) {
                    count = 0;  // deliberate initialization here allows for diagnostics later.
                }
                count++;
            }
            public void lockInterruptibly() throws InterruptedException {
                lock();
            }
            public boolean tryLock() {
                return true;
            }
            public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
                return true;
            }
            public void unlock() {
                count--;
            }
            public Condition newCondition() {
                return null;
            }
        };
    Object key1 = "key1";
    Object key2 = "key2";
    Object val1 = "val1";
    Object val2 = "val2";

    /**
     * a call to put() will add the input key/value pair to the collection.
     */
    @Test
    public void testInput() {
        count = null;
        ObjectMap map = new ThreadSafeLinkedMap(testLock);
        map.put(key1,val1);
        assertEquals((Object) 0, count);
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
        count = null;
        ObjectMap map = new ThreadSafeLinkedMap(testLock);
        map.put(key1,val1);
        assertEquals((Object) 0, count);
        Object val = map.get(key1);
        assertNotNull(val);
        assertEquals(val1, val);
        map.put(key1, val2);
        assertEquals((Object) 0, count);
        val = map.get(key1);
        assertNotNull(val);
        assertEquals(val2,val);
    }


    /**
     * To validate we have not cheated and are actually a collection
     */
    @Test
    public void testTwoInputs() {
        count = null;
        ObjectMap map = new ThreadSafeLinkedMap(testLock);
        map.put(key1,val1);
        assertEquals((Object) 0, count);
        map.put(key2, val2);
        assertEquals((Object) 0, count);
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
    @Test
    public void testANullKeyIsNotValid() {
        count = null;
        ObjectMap map = new ThreadSafeLinkedMap(testLock);
        try {
            map.put(null, val1);
        }catch (Throwable t) {
        }
        assertNull(count); // we never get to an actual write situation
    }

    /**
     * A null "key" is not valid.
     */
    @Test
    public void testANullKeyIsNotValidNullObject() {
        count = null;
        ObjectMap map = new ThreadSafeLinkedMap(testLock);
        Object key = null;
        try {
            map.put(key,val1);
        } catch (Throwable t) {
        }
        assertNull(count); // we never actually get to a write situation
    }

    /**
     * A null "value" will remove any existing key from the collection.
     */
    @Test
    public void testInputThenDelete() {
        count = null;
        ObjectMap map = new ThreadSafeLinkedMap(testLock);
        map.put(key1, val1);
        assertEquals((Object) 0, count);
        map.put(key2, val2);
        assertEquals((Object) 0, count);
        Object val = map.get(key1);
        assertNotNull(val);
        assertEquals(val1, val);
        val = map.get(key2);
        assertNotNull(val);
        assertEquals(val2, val);
        assertEquals((Object) 0, count);
        map.put(key2, null);
        assertEquals((Object) 0, count);
        val = map.get(key1);
        assertNotNull(val);
        assertEquals(val1, val);
        val = map.get(key2);
        assertNull(val);
        assertEquals((Object) 0, count);
        map.put(key1, null);
        assertEquals((Object) 0, count);
        val = map.get(key1);
        assertNull(val);
    }

}
