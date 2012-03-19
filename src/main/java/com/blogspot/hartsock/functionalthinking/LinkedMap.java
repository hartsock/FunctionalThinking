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
	public void put(Object key, Object value) {
		if(key == null) {
			throw new NullKeyIsIllegal();
		}
		Link current = filter(key, new Actor() {
			public Link action(Object key, Link link) {
				return link.keyIs(key) ? link : null;
			}
		});
		if (current != null) {
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
		} else {
			Link tail = new Link(key, value);
			filter(tail,new Actor() {
				public Link action(Object tail, Link current) {
					if(current.next == null) {
						current.next = (Link) tail;
						return (Link) tail;
					}
					else 
						return null;
				}
			});
		}
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
		Link current = filter(key, new Actor() {
			public Link action(Object key, Link link) {
				return link.keyIs(key) ? link : null;
			}
		});
		
		return (current != null) ? current.getValue() : null;
	}

}
