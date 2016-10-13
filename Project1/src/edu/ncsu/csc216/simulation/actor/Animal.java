package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;
import java.util.Random;

import edu.ncsu.csc216.simulation.environment.*;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Animal objects constructor
 * @author Jialang Li
 *
 */
public abstract class Animal {

    /** time since last meal */
    private int timeSinceLastMeal;
    
    /** time since last breed */
    private int timeSinceLastBreed;
    
    /** if the animal can act this step */
    private boolean canActThisStep;
    
    /** animal symbol */
    private char symbol;
    
    /** if the animal is alive */
    private boolean alive;
    
    /** seed for random generator */
    private static int seed = 500;
     
    /** random generator for move */
    private static Random randomGenerator;
    
    /**
     * Animal constructor
     * @param s symbol
     */
    public Animal(char s) {
        this.timeSinceLastMeal = 0;
        this.timeSinceLastBreed = 0;
        this.symbol = s;
        this.alive = true;
        this.canActThisStep = false;
    }
    
    /**
     * random number generator
     * @param seed for random generator
     */
    public static void setRandomSeed(int seed) {
//        seed = seed;
        randomGenerator = new Random(seed);
    }
    
    /**
     * returns animal symbol
     * @return animal symbol
     */
    public char getSymbol() {
        return this.symbol;
    }
    
    /**
     * returns true if the animal is alive
     * @return true if the animal is alive
     */
    public boolean isAlive() {
        return this.alive;
    }
    
    /**
     * enable the animal
     */
    public void enable() {
        this.canActThisStep = true;
    }
    
    /**
     * disable the animal
     */
    public void disable() {
        this.canActThisStep = false;
    }
    
    /**
     * kills the animal
     */
    protected void die() {
        this.alive = false;
    }
    
    /**
     * returns true if the animal can act
     * @return true if the animal can act
     */
    protected boolean canAct() {
        return this.canActThisStep;
    }
    
    /**
     * returns time since last breed
     * @return time since last breed
     */
    protected int getTimeSinceLastBreed() {
        return this.timeSinceLastBreed;
    }
    
    /**
     * returns time since last meal
     * @return time since last meal
     */
    protected int getTimeSinceLastMeal() {
        return this.timeSinceLastMeal;
    }
    
    /**
     * update meal counter
     */
    protected void incrementTimeSinceLastMeal() {
        this.timeSinceLastMeal++;
    }
    
    /**
     * increase Time since last breed
     */
    protected void incrementTimeSinceLastBreed() {
        this.timeSinceLastBreed++;
    }
    
    /**
     * returns if the animal can breed
     * @param location location
     * @param ecoGrid eco grid
     * @return true if the animal can breed
     */
    protected boolean breed(Location location, EcoGrid ecoGrid) {
        return true;
    }
    
    /**
     * move animal
     * @param location location of the animal
     * @param ecoGrid eco grid
     */
    protected void move(Location location, EcoGrid ecoGrid) {
        int direction = randomGenerator.nextInt(4); // Limits the results to 0, 1, 2, 3
        if (direction == 0) {
            
        }
        if (direction == 1) {
            // go north
        }
        if (direction == 2) {
            // go eas
        }
        if (direction == 3) {
            // go south
        }
    }
    
    /**
     * eat the animal in the given location
     * @param location location
     * @param ecoGrid eco grid
     * @return true if can eat
     */
    protected boolean eat(Location location, EcoGrid ecoGrid) {
        return false;
    }
    
    /**
     * returns animal color
     * @return animal color
     */
    public abstract Color getColor();
    
    /**
     * act 
     * @param location location of the animal
     * @param ecoGrid ecogrid 
     */
    public abstract void act(Location location, EcoGrid ecoGrid);
    
    /**
     * if the animal past breed time
     * @param time time
     * @return true if the animal has past breed time
     */
    protected abstract boolean pastBreedTime(int time);
    
    /**
     * returns new animal
     * @return new animal
     */
    protected abstract Animal makeNewBaby();
    
    /**
     * returns food chain rank
     * @return food chain rank
     */
    protected abstract int getFoodChainRank();
    
}