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
	
	/**
	 * test findFirstNeighborEmptyNeighbor
	 */
	@Test
	public void testFindFirstEmptyNeighbor() {
		Location l = new Location(1, 1);
		EcoGrid eco = new Ecosystem(3, 3);
		PurePredator p = new PurePredator('l');
		eco.add(p, l);
		
		assertEquals(1, eco.findFirstEmptyNeighbor(l, 0).getRow());
		assertEquals(0, eco.findFirstEmptyNeighbor(l, 0).getCol());
		
		assertEquals(0, eco.findFirstEmptyNeighbor(l, 1).getRow());
		assertEquals(1, eco.findFirstEmptyNeighbor(l, 1).getCol());
		
		assertEquals(1, eco.findFirstEmptyNeighbor(l, 2).getRow());
		assertEquals(2, eco.findFirstEmptyNeighbor(l, 2).getCol());
		
		assertEquals(2, eco.findFirstEmptyNeighbor(l, 3).getRow());
		assertEquals(1, eco.findFirstEmptyNeighbor(l, 3).getCol());
		
		p.act(l, eco);
		assertEquals(0, eco.findFirstEmptyNeighbor(l, 0).getRow());
		
		Animal c = eco.getItemAt(new Location(1, 0));
		assertEquals(2, eco.findFirstEmptyNeighbor(new Location(1, 0), 0).getCol());
		c.act(new Location(1, 0), eco);
		assertEquals(0, eco.findFirstEmptyNeighbor(new Location(1, 0), 0).getRow());
		assertEquals(0, eco.findFirstEmptyNeighbor(new Location(1, 0), 0).getCol());
		
		assertEquals(0, eco.findFirstEmptyNeighbor(new Location(0, 0), 0).getRow());
		assertEquals(2, eco.findFirstEmptyNeighbor(new Location(0, 0), 0).getCol());
		
		eco.add(p, new Location(0, 2));
		assertEquals(2, eco.findFirstEmptyNeighbor(new Location(0, 0), 0).getRow());
		assertEquals(0, eco.findFirstEmptyNeighbor(new Location(0, 0), 0).getCol());
		
		assertEquals(2, eco.findFirstEmptyNeighbor(new Location(2, 0), 0).getRow());
		assertEquals(2, eco.findFirstEmptyNeighbor(new Location(2, 0), 0).getCol());
		
		eco.add(p, new Location(2, 0));
		assertEquals(0, eco.findFirstEmptyNeighbor(new Location(0, 0), 0).getRow());
		assertEquals(1, eco.findFirstEmptyNeighbor(new Location(0, 0), 0).getCol());
		
		eco.remove(new Location(1, 0));
		assertEquals(1, eco.findFirstEmptyNeighbor(new Location(1, 2), 0).getRow());
		assertEquals(0, eco.findFirstEmptyNeighbor(new Location(1, 2), 0).getCol());
		
		eco.add(p, new Location(2, 2));
		assertEquals(0, eco.findFirstEmptyNeighbor(new Location(2, 1), 0).getRow());
		assertEquals(1, eco.findFirstEmptyNeighbor(new Location(2, 1), 0).getCol());
		
		eco.remove(new Location(2, 0));
		eco.add(p, new Location(0, 1));
		assertEquals(2, eco.findFirstEmptyNeighbor(new Location(2, 1), 3).getRow());
		assertEquals(0, eco.findFirstEmptyNeighbor(new Location(2, 1), 3).getCol());
		
		EcoGrid grid = new Ecosystem(3, 3);
		grid.add(p, new Location(1, 0));
		grid.add(p, new Location(1, 2));
		assertEquals(2, grid.findFirstEmptyNeighbor(new Location(1, 2), 2).getRow());
		
		assertEquals(0, grid.findFirstEmptyNeighbor(new Location(0, 0), 3).getRow());
	}
}
