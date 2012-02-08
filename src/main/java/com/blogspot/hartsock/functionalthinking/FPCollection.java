package com.blogspot.hartsock.functionalthinking;

import com.blogspot.hartsock.functionalthinking.functions.BinaryFunction;
import com.blogspot.hartsock.functionalthinking.functions.Function;
import com.blogspot.hartsock.functionalthinking.functions.UnaryFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author mansoor
 * @since 2/7/12
 */
public class FPCollection<T> implements Collection<T> {
    final Collection<T> collection;

    public FPCollection() {
        collection = new ArrayList<T>();
    }

    public FPCollection(Collection<T> collection) {
        this.collection = collection;
    }

    public FPCollection(final T... values ) {
        this.collection = new ArrayList<T>(Arrays.asList(values));
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return collection.toArray(ts);
    }

    @Override
    public boolean add(T t) {
        return collection.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return collection.contains(objects);
    }

    @Override
    public boolean addAll(Collection<? extends T> ts) {
        return collection.addAll(ts);
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        return collection.removeAll(objects);
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        return collection.retainAll(objects);
    }

    @Override
    public void clear() {
        collection.clear();
    }

    /**
     * Applies the given function to each element in the collection
     * @param function function that will be applied
     */
    public void forEach(final Function<T> function) {
        if (function != null) {
            for (T t : collection) {
                function.apply(t);
            }
        }
    }

    /**
     *  Transform this collection to a new collection by applying given
     *  function to each element of this collection
     * @param function function that will be applied
     * @param <B>    Type of new collection
     * @return Collection of type B
     */
    public <B> FPCollection<B> map(final UnaryFunction<T, B> function) {
        FPCollection<B> result = new FPCollection<B>();
        if (function != null) {
            for (T t : collection) {
                final B b = function.apply(t);
                result.add(b);
            }
        }
        return result;
    }

    /**
     * The infamous FoldLeft method, also know as FOLDL in Haskell, FoldLeft in Scala
     * and Inject in Ruby
     * @param seed initial value
     * @param function  function that will be applied
     * @param <B>  type of the result
     * @return return Object of type B
     */
    public <B> B foldLeft(final B seed, final BinaryFunction<T, B> function) {
        B result = seed;
         if(function!=null){
             for (T t : collection) {
                 result=function.apply(result,t);
             }
         }
        return result;
    }

    @Override
    public String toString() {
        return foldLeft(new StringBuilder(),new BinaryFunction<T, StringBuilder>() {
            public StringBuilder apply(StringBuilder seed, T input) {
                seed.append(input).append(" ");
                return seed;
            }
        }).toString();
    }
}
