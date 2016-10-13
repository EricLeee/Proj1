package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import org.junit.Test;
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
    }

}
