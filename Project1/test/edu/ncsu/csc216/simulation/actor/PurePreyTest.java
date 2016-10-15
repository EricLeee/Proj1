package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.*;
import edu.ncsu.csc216.simulation.environment.utils.Location;
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
        EcoGrid eco = new Ecosystem(3, 3);
        Location mid = new Location(1, 1);
        
        assertEquals(Color.green, p.getColor()); 
        assertFalse(p.pastBreedTime(0));
        assertTrue(p.pastBreedTime(10));
        
        assertEquals(0, p.getFoodChainRank());
        
        PurePrey b = new PurePrey('p');
        assertEquals(b.getColor(), p.makeNewBaby().getColor()); 
        
        eco.add(p, mid);
        p.enable();
        p.act(mid, eco);
        assertNull(eco.getItemAt(mid));
        
        
    }
        
}
