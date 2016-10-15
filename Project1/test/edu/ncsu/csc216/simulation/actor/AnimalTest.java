package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;
/**
 * Test Animal class
 * @author Jialang Li
 *
 */
public class AnimalTest {

    /**
     * Test constructor
     */
    @Test
    public void testAnimal() {
        PurePredator a1 = new PurePredator('a');
        assertEquals(a1.getSymbol(), 'a');
        assertTrue(a1.isAlive());
        assertEquals(0, a1.getTimeSinceLastMeal());
        assertEquals(0, a1.getTimeSinceLastBreed());
        assertFalse(a1.canAct());
        a1.enable();
        assertTrue(a1.canAct());
        a1.disable();
        assertFalse(a1.canAct());
        a1.die();
        assertFalse(a1.isAlive());
        a1.incrementTimeSinceLastBreed();
        assertEquals(1, a1.getTimeSinceLastBreed());
        a1.incrementTimeSinceLastMeal();
        assertEquals(1, a1.getTimeSinceLastMeal());
        
        EcoGrid eco = new Ecosystem(3, 3);
        eco.add(a1, new Location(1, 1));
        
        PurePrey b = new PurePrey('b');
        eco.add(b, new Location(1, 0));
        a1.eat(new Location(1, 1), eco);
        assertNull(eco.getItemAt(new Location(1, 1)));
        assertEquals(0, a1.getTimeSinceLastMeal());
        
        eco.add(a1, new Location(1, 1));
        eco.add(b, new Location(0, 1));
        a1.eat(new Location(1, 1), eco);
        assertNull(eco.getItemAt(new Location(1, 1)));
        
        eco.add(a1, new Location(1, 1));
        eco.add(b, new Location(1, 2));
        a1.eat(new Location(1, 1), eco);
        assertNull(eco.getItemAt(new Location(1, 1)));
        assertEquals(a1, eco.getItemAt(new Location(1, 2)));
        
        eco.add(a1, new Location(1, 1));
        eco.add(b, new Location(2, 1));
        a1.eat(new Location(1, 1), eco);
        assertNull(eco.getItemAt(new Location(1, 1)));
        assertEquals(a1, eco.getItemAt(new Location(2, 1)));
        
        eco.add(a1, new Location(0, 0));
        eco.add(b, new Location(0, 2));
        a1.eat(new Location(0, 0), eco);
        assertNull(eco.getItemAt(new Location(0, 0)));
        assertEquals(a1, eco.getItemAt(new Location(0, 2)));
        
        eco.add(b, new Location(0, 0));
        a1.eat(new Location(0, 2), eco);
        assertNull(eco.getItemAt(new Location(0, 2)));
        assertEquals(a1, eco.getItemAt(new Location(0, 0)));
        
        eco.add(a1, new Location(0, 2));
        eco.add(b, new Location(2, 0));
        a1.eat(new Location(0, 0), eco);
        assertNull(eco.getItemAt(new Location(0, 0)));
        assertEquals(a1, eco.getItemAt(new Location(2, 0)));
        
        eco.add(b, new Location(0, 0));
        a1.eat(new Location(2, 0), eco);
        assertNull(eco.getItemAt(new Location(2, 0)));
        assertEquals(a1, eco.getItemAt(new Location(0, 0)));
     
        assertTrue(a1.breed(new Location(0, 0), eco));
        
        a1.move(new Location(2, 1), eco);
        assertNull(eco.getItemAt(new Location(2, 1)));
    }

}
