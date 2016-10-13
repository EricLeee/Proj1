package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;
/**
 * Test PredatorPrey
 * @author Jialang Li
 *
 */
public class PredatorPreyTest {

    /**
     * Test PredatorPrey
     */
    @Test
    public void testPredatorPrey() {
        PredatorPrey p = new PredatorPrey('p');
        Configs.setToDefaults();
        
        assertEquals(Color.orange, p.getColor());
        assertFalse(p.pastBreedTime(0));
        assertTrue(p.pastBreedTime(1));
        
        assertEquals(10, p.getFoodChainRank());
        
        PredatorPrey b = new PredatorPrey('p');
        assertEquals(b.getColor(), p.makeNewBaby().getColor());
    }

}
