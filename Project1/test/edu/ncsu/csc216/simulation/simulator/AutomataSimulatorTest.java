package edu.ncsu.csc216.simulation.simulator;

import static org.junit.Assert.*;

import org.junit.Test;

public class AutomataSimulatorTest {

	@Test
	public void testAutomataSimulator() {
		SimulatorInterface a = new AutomataSimulator("test-files\\testFile1.txt");
		assertEquals(4, a.getNames().length);
		
		
	}

}
