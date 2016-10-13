package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;
/**
 * Test PurePrey
 * @author Jialang Li
 *
 */
public class PurePreyTest {

    /**
     * Test PurePrey
     */
    @Test
    public void testPurePrey() {
        PurePrey p = new PurePrey('p');
        Configs.setToDefaults();
        
        assertEquals(Color.green, p.getColor()); 
        assertFalse(p.pastBreedTime(0));
        assertTrue(p.pastBreedTime(1));
        
        assertEquals(0, p.getFoodChainRank());
        
        PurePrey b = new PurePrey('p');
        assertEquals(b.getColor(), p.makeNewBaby().getColor());    }

}
