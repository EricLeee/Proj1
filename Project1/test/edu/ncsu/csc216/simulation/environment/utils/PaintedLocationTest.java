package edu.ncsu.csc216.simulation.environment.utils;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * test PaintedLocation
 * @author Jialang Li
 *
 */
public class PaintedLocationTest {

    /**
     * test PaintedLocation
     */
    @Test
    public void testPaintedLocation() {
        PaintedLocation p = new PaintedLocation(6, 6, Color.GREEN, 'l');
        assertEquals(Color.GREEN, p.getColor());
        assertEquals('l', p.getSymbol());
    }

}
