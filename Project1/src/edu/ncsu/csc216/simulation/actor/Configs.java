package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;
/**
 * Configuration of Animal objects
 * @author Jialang Li
 *
 */
public class Configs {

    /** default food chain rank */
    private static final int[] DEFAULT_FOOD_CHAIN_RANK = {0, 10, 20};
    
    /** default colors for each animals in the grid */
    private static final Color[] DEFAULT_COLORS = {Color.green, Color.orange, Color.red};
    
    /** colors for animals of each food chain rank */
    private static Color[] playerColor;
    
    /** default starve time for animals */
    private static final int[] DEFAULT_STARVE_TIME = {10, 6, 5};
    
    /** starve time of an animal */ 
    private static int[] starveTime;
    
    /** default breed time */
    private static final int[] DEFAULT_BREED_TIME = {1, 7, 15};
    
    /** breed time */
    private static int[] breedTime;
    
    /**
     * Configuration Constructor
     */
    public Configs() {
        
    }
    
    /**
     * Construct configuration with custom properties
     * @param c custom color for each animals
     * @param s custom starve time
     * @param b custom breed time
     */
    public static void initConfigs(Color[] c, int[] s, int[] b) {
        playerColor = c;
        starveTime = s;
        breedTime = b;
    }
    
    /**
     * Construct configuration with default properties
     */
    public static void setToDefaults() {
        playerColor = DEFAULT_COLORS;
        starveTime = DEFAULT_STARVE_TIME;
        breedTime = DEFAULT_BREED_TIME;
    }
    
    /**
     * get prey color
     * @return prey color
     */
    public static Color getPreyColor() {
        return playerColor[0];
    }
    
    /**
     * returns middle color
     * @return middle color
     */
    public static Color getMiddleColor() {
        return playerColor[1];
    }
    
    /**
     * returns predator color
     * @return predator color
     */
    public static Color getPredatorColor() {
        return playerColor[2];
    }
    
    /**
     * returns prey food chain rank
     * @return prey food chain rank
     */
    public static int getPreyFoodChainRank() {
        return DEFAULT_FOOD_CHAIN_RANK[0];
    }
    
    /**
     * returns middle food chain rank
     * @return middle food chain rank
     */
    public static int getMiddleFoodChainRank() {
        return DEFAULT_FOOD_CHAIN_RANK[1];
    }
    
    /**
     * returns predator food chain rank
     * @return predator food chain rank
     */
    public static int getPredatorFoodChainRank() {
        return DEFAULT_FOOD_CHAIN_RANK[2];
    }
    
    /**
     * returns prey starve time
     * @return prey starve time
     */
    public static int getPreyStarveTime() {
        return starveTime[0];
    }
    
    /**
     * returns middle starve time
     * @return middle starve time
     */
    public static int getMiddleStarveTime() {
        return starveTime[1];
    }
    
    /**
     * returns predator starve time
     * @return predator starve time
     */
    public static int getPredatorStarveTime() {
        return starveTime[2];
    }
    
    /**
     * returns prey breed time
     * @return prey breed time
     */
    public static int getPreyBreedTime() {
        return breedTime[0];
    }
    
    /**
     * returns middle breed time 
     * @return middle breed time
     */
    public static int getMiddleBreedTime() {
        return breedTime[1];
    }
    
    /**
     * returns predator breed time
     * @return predator breed time
     */
    public static int getPredatorBreedTime() {
        return breedTime[2];
    }
}