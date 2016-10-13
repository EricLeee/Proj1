package edu.ncsu.csc216.simulation.environment.utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test Location.java
 * @author jli29
 *
 */
public class LocationTest {

	/**
	 * test Location constructor and its getter methods
	 */
	@Test
	public void testLocation() {
		Location loc = new Location(0, 1);
		assertEquals(0, loc.getRow());
		assertEquals(1, loc.getCol());
	}

}
