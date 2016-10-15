package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Test;
/**
 * Test Configs.java
 * @author Jialang Li
 *
 */
public class ConfigsTest {

    /**
     * test Configs.java
     */
    @Test
    public void testConfigs() {
        Color[] c = {Color.red, Color.orange, Color.green};
        int[] s = {3, 2, 1};
        int[] b = {1, 2, 3};
        Configs.initConfigs(c, s, b);
        
        assertEquals(Color.green, Configs.getPredatorColor());
        assertEquals(Color.orange, Configs.getMiddleColor());
        assertEquals(Color.red, Configs.getPreyColor());
        
        assertEquals(1, Configs.getPredatorStarveTime());
        assertEquals(2, Configs.getMiddleStarveTime());
        assertEquals(3, Configs.getPreyStarveTime());
        
        assertEquals(3, Configs.getPredatorBreedTime());
        assertEquals(2, Configs.getMiddleBreedTime());
        assertEquals(1, Configs.getPreyBreedTime());
        
        assertEquals(20, Configs.getPredatorFoodChainRank());
        assertEquals(10, Configs.getMiddleFoodChainRank());
        assertEquals(0, Configs.getPreyFoodChainRank());
        
    } 
}
