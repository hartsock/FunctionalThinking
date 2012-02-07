package com.blogspot.hartsock.functionalthinking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Shawn Hartsock
 * hartsock@acm.org<p/>
 * <p/>
 *
 * @author Shawn Hartsock
 */
public class ThreadSafeLinkedMap extends LinkedMap {
    Lock writeLock;

    public ThreadSafeLinkedMap() {
        writeLock = new ReentrantLock();
    }

    ThreadSafeLinkedMap(Lock lock) {
        writeLock = lock;
    }

    void add(final Object key, final Object value) {
        writeLock.lock();
        try {
            super.add(key,value);
        } finally {
            writeLock.unlock();
        }
    }

    void delete(final Object key) {
        writeLock.lock();
        try {
            super.delete(key);
        } finally {
            writeLock.unlock();
        }
    }

    void swap(final Link link, final Object value) {
        writeLock.lock();
        try {
            super.swap(link,value);
        } finally {
            writeLock.unlock();
        }
    }
}
