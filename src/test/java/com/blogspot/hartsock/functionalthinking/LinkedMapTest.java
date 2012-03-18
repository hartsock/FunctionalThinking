package com.blogspot.hartsock.functionalthinking;

import org.junit.Before;
/**
 * Shawn Hartsock
 * hartsock@acm.org<p/>
 * <p/>
 *
 * @author Shawn Hartsock
 */
public class LinkedMapTest extends ObjectMapTest {

	@Override
	@Before
	public void allocateMap() {
		map = new LinkedMap();
	}
}
