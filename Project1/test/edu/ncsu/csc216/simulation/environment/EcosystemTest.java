package edu.ncsu.csc216.simulation.environment;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.simulation.actor.*;
import edu.ncsu.csc216.simulation.environment.utils.Location;
/**
 * Test Ecosystem class
 * @author jli29
 *
 */
public class EcosystemTest {

	/**
	 * test Ecosystem
	 */
	@Test
	public void testEcosystem() {
		EcoGrid eco = new Ecosystem(20, 16);
		assertEquals(20, eco.getMap().length);
	}
	
	/**
	 * test isEmpty
	 */
	@Test
	public void testIsEmpty() {
		EcoGrid eco = new Ecosystem(20, 20);
		Location loc = new Location(0, 0);
		PurePredator p = new PurePredator('l');
		assertTrue(eco.isEmpty(loc));
		
		eco.add(p, loc);
		assertFalse(eco.isEmpty(loc));
		
		eco.remove(loc);
		assertNull(eco.getItemAt(loc));
	} 
	
	/**
	 * test Remove
	 */
	@Test
	public void testDue() {
		EcoGrid eco = new Ecosystem(20, 20);
		Location loc = new Location(5, 5);
		Location north = new Location(0, 5);
		Location south = new Location(19, 5);
		Location west = new Location(5, 0);
		Location east = new Location(5, 19);

		assertEquals(north.getRow(), eco.dueNorth(loc).getRow());
		assertEquals(south.getRow(), eco.dueSouth(loc).getRow());
		assertEquals(west.getCol(), eco.dueWest(loc).getCol());
		assertEquals(east.getCol(), eco.dueEast(loc).getCol());
	
	}
}
