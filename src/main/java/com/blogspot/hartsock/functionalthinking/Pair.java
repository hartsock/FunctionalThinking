package com.blogspot.hartsock.functionalthinking;

public class Pair {
	public final Object key;
	public final Object value;
	
	public Pair(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

	public Object getKey() { return this.key; }
	public Object getValue() { return this.value; }
	public boolean hasKey() { return this.key != null; }
	public boolean hasValue() { return this.value != null; }
	public boolean keyIs(Object obj) { return hasKey() && key.equals(obj); }
	public boolean valueIs(Object obj) { return hasValue() && value.equals(obj); }
}
