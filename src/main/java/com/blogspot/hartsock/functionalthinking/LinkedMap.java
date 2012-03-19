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
		Link next;
	}
	
	Link head;
	
	@Override
	public void put(Object key, Object value) {
		if(key == null) {
			throw new NullKeyIsIllegal();
		}
		if(head == null) {
			head = new Link(key,value);
		}
		else {
			Link last = null;
			Link current = head;
			while(current != null && !current.keyIs(key)) {
				last = current;
				current = current.next;
			}
			if(current != null) {
				Link replacement = new Link(current.getKey(),value);
				replacement.next = current.next;
				if(last != null) {
					last.next = replacement;					
				}
				else {
					head = replacement;
				}
			}
			else {
				last.next = new Link(key,value);
			}
		}
	}

	@Override
	public Object get(Object key) {
		Link current = head;
		
		while(current != null && !current.keyIs(key)) {
			current = current.next;
		}
		
		if(current != null)
			return current.getValue();
		else
			return null;
	}

}
