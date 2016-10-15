package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;
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
        EcoGrid eco = new Ecosystem(3, 3);
        Location mid = new Location(1, 1);
        
        assertEquals(Color.orange, p.getColor());
        assertFalse(p.pastBreedTime(0));
        assertTrue(p.pastBreedTime(10));
        
        assertEquals(10, p.getFoodChainRank());
        
        PredatorPrey b = new PredatorPrey('p');
        assertEquals(b.getColor(), p.makeNewBaby().getColor());
        
        eco.add(p, mid);
        p.enable();
        p.act(mid, eco);
        assertNull(eco.getItemAt(mid));
    }

}
