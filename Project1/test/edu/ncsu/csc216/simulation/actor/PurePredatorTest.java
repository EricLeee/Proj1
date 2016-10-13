package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;
/**
 * Test PurePredator
 * @author Jialang Li
 *
 */
public class PurePredatorTest {

    /**
     * Test PurePredator
     */
    @Test
    public void testPurePredator() {
        PurePredator p = new PurePredator('p');
        Configs.setToDefaults();
        
        assertEquals(Color.red, p.getColor()); 
        assertFalse(p.pastBreedTime(0));
        assertTrue(p.pastBreedTime(1));
        
        assertEquals(20, p.getFoodChainRank());
        
        PurePredator b = new PurePredator('p');
        assertEquals(b.getColor(), p.makeNewBaby().getColor());
    }

}
