package com.blogspot.hartsock.functionalthinking;

/**
 * Shawn Hartsock
 * hartsock@acm.org<p/>
 * @author Shawn Hartsock
 */
public class LinkedMap implements ObjectMap {
	class NullKeyIsIllegal extends IllegalStateException {};
	
	class Link extends Pair {
		public Link(Object left, Object right) {super(left, right);}
		Link next = null;
	}
	
	Link head = new Link(null,null);
	
	@Override
	public boolean put(Object key, Object value) {
		if(key == null) {
			throw new NullKeyIsIllegal();
		}
		Link current = find(key);
		return (current != null) ?
			swap(current, value) : append(key, value);
		
	}

	private boolean swap(Link current, Object value) {
		Link replacement = new Link(current.getKey(), value);
		replacement.next = current.next;
		Link last = filter(current, new Actor() {
			public Link action(Object current, Link link) {
				return link.next == current ? link : null;
			}
		});
		if (last != null) {
			last.next = replacement;
		} else {
			head = replacement;
		}
		return true;
	}

	private boolean append(Object key, Object value) {
		filter(new Link(key, value) ,new Actor() {
			public Link action(Object tail, Link current) {
				if(current.next == null) {
					current.next = (Link) tail;
					return (Link) tail;
				}
				else 
					return null;
			}
		});
		return true;
	}

	private Link find(Object key) {
		return filter(key, new Actor() {
			public Link action(Object key, Link link) {
				return link.keyIs(key) ? link : null;
			}
		});
	}

	interface Actor {
		/** non-null return means stop */
		Link action(Object criteria, Link link);
	}
	
	Link filter(Object criteria, Actor actor) {
		Link result = null;
		Link it = this.head;
		while(it != null) {
			result = actor.action(criteria, it);
			if(result != null) {
				break;
			}
			it = it.next;
		}
		return result;
	}
	
	@Override
	public Object get(Object key) {
		Link current = find(key);
		return (current != null) ? current.getValue() : null;
	}

}
