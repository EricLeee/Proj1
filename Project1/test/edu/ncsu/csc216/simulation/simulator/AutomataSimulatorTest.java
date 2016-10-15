package edu.ncsu.csc216.simulation.simulator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test AutomataSimulator
 * @author Jialang Li
 *
 */
public class AutomataSimulatorTest {

    /**
     * Test AutomataSimulator
     */
	@Test
	public void testAutomataSimulator() {
		SimulatorInterface a = null; 
		
		try {
		    a = new AutomataSimulator("test-files\\ecosystem.txt");
		} catch (IllegalArgumentException e) {
		    fail();
		    assertEquals("File required to initialize the ecosystem", e.getMessage());
		}
        assertEquals(4, a.getNames().length);
		
		SimulatorInterface b = null;
		
		try {
		    b = new AutomataSimulator("test-files\\ecosystem.txt", "test-files\\testConf1.txt");
		} catch (IllegalArgumentException e) {
		    fail();
		    assertEquals("File required to initialize the ecosystem", e.getMessage());
		}
		assertEquals(4, b.getNames().length);
		
		assertEquals(20, a.getView().length);
	}
}
