package com.blogspot.hartsock.functionalthinking;

import org.junit.AfterClass;
/**
 * Shawn Hartsock
 * hartsock@acm.org<p/>
 * <p/>
 *
 * @author Shawn Hartsock
 */
public class LinkedMapTest extends ObjectMapTest {

	@Override
	@AfterClass
	public void setUp() {
		map = new LinkedMap();
	}
}
