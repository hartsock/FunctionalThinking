package com.blogspot.hartsock.functionalthinking;

/**
 * Shawn Hartsock
 * hartsock@acm.org<p/>
 * @author Shawn Hartsock
 */
public class LinkedMap implements ObjectMap {

    Link head;

    class Link {
    	final Object key;
    	Object value;
    	
    	Link next;
    	
    	public Link(final Object key, final Object value) {
    		this.key = key;
    		this.value = value;
    	}
    	

        boolean hasNext() {
            return next != null;
        }

        boolean match(final Object key) {
            if(key == null) {
                throw new IllegalArgumentException("Cannot compare null Object or null Object value!");
            }
            return key.equals(this.key);
        }
    }
    
    public LinkedMap() {
        // null entry simplifies iterator.
        head = new Link(null,null);
    }

    public void put(Object key, Object value) {
        if(bad(key)) {
            throw new IllegalArgumentException("You cannot specify a null key!");
        }
        if(bad(value)) {
            delete(key);
            return;
        }

        final Link link = find(key);
        if(link == null) {
            add(key,value);
        }
        else {
            swap(link, value);
        }
    }

    /**
     * @return the "value" from a previous call of put(key,value).
     *         <p/>
     *         If the key is not part of the collection, then return null.
     */
    public Object get(final Object key) {
        final Link link = find(key);
        if(good(link)) {
            return link.value;
        }
        return null;
    }

    private boolean good(final Link link) {
        return link != null;
    }

    private boolean bad(final Object object) {
        return object == null;
    }

    /**
     * Interface for hanging anonymous inner class on like a lambda.
     */
    private interface KeyLinkActor {
        boolean act(Object key, Link link);
    }

    /**
     * Okay, so I needed two kinds of lambdas after all.
     */
    private interface Finder extends KeyLinkActor {
        Link getFound();
    }

    void add(final Object key, final Object value) {
        Link it = head;
        while(it.hasNext()) {
            it = it.next;
        }
        it.next = new Link(key,value);
    }

    void delete(Object key) {
        filter(key, new KeyLinkActor() {
            public boolean act(Object key, Link link) {
                if (link.next.match(key)) {
                    link.next = link.next.next;
                    return true;
                }
                return false;
            }
        });
    }

    void swap(Link entry, Object value) {
         entry.value = value;
    }

    void filter(final Object key, final KeyLinkActor actor) {
        Link it = head;
        while(it.hasNext()) {
            if(actor.act(key,it)) {
                break;
            }
            it = it.next;
        }
    }


    /**
     * finds the link you want to work with.
     */
    Link find(final Object key) {
        final Finder finder = new Finder() {
            Link found = null;
            public Link getFound() { return found; }
            public boolean act(final Object key, final Link link) {
                if(link.next.match(key)) {
                    found = link.next;
                    return true;
                }
                return false;
            }
        };
        filter(key, finder);
        return finder.getFound();
    }
}
