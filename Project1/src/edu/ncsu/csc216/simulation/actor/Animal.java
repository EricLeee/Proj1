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
    private static int seed;
     
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
     * @param sd for random generator
     */
    public static void setRandomSeed(int sd) {
        seed = sd;
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
    	Location l = ecoGrid.findFirstEmptyNeighbor(location, 0);
    	if (l != null) {
    		ecoGrid.add(this.makeNewBaby(), l);
    		timeSinceLastBreed = 0;
    		this.incrementTimeSinceLastMeal();
    		return true;
    	}
        return false;
    }
    
    /**
     * move animal
     * @param location location of the animal
     * @param ecoGrid eco grid
     */
    protected void move(Location location, EcoGrid ecoGrid) {
        Location loc = null;
        randomGenerator = new Random();
        int direction = randomGenerator.nextInt(4); // Limits the results to 0, 1, 2, 3
        loc = ecoGrid.findFirstEmptyNeighbor(location, direction);
        if (loc != null) {
            ecoGrid.add(ecoGrid.getItemAt(location), loc);
            ecoGrid.remove(location);
        }
    }
    
    /**
     * eat the animal in the given location
     * @param location location
     * @param ecoGrid eco grid
     * @return true if can eat
     */
    protected boolean eat(Location location, EcoGrid ecoGrid) {
        Location temp = location;
        
        final int west = 0;
        final int north = 1;
        final int east = 2;
        final int south = 3;
        final int last = 4;
        int state = 0;
        int i = 0;
        
        while (state < last && i < 4) {
            switch(state) {
            case west:
                
                if (temp.getCol() == 0) {
                    if (ecoGrid.getMap()[temp.getRow()][ecoGrid.getMap().length - 1] == null) {
                        state = north;
                        i++;
                        break;
                    }
                    if (this.getFoodChainRank() > ecoGrid.getMap()[temp.getRow()][ecoGrid.getMap().length - 1].getFoodChainRank()) {
                        temp = ecoGrid.dueEast(temp);
                        state = last;
                        i++;
                        break;
                    } 
                    
                    state = north;
                    i++;
                    break;
                } else {
                    if (ecoGrid.getMap()[temp.getRow()][temp.getCol() - 1] == null) {
                        state = north;
                        i++;
                        break;
                    } else if (this.getFoodChainRank() > ecoGrid.getMap()[temp.getRow()][temp.getCol() - 1].getFoodChainRank()) {
                        temp = new Location(temp.getRow(), temp.getCol() - 1);
                        state = last;
                        i++;
                        break;
                    }
                }
                   
                state = north;
                i++;
                break;
            case north:
                if (temp.getRow() == 0) {
                    if (ecoGrid.getMap()[ecoGrid.getMap().length - 1][temp.getCol()] == null) {
                        state = east;
                        i++;
                        break;
                    } else if (this.getFoodChainRank() > ecoGrid.getMap()[ecoGrid.getMap().length - 1][temp.getCol()].getFoodChainRank()) {
                        temp = ecoGrid.dueSouth(temp);
                        state = last;
                        i++;
                        break;
                    }
                    state = east;
                    i++;
                    break;
                } else {
                    if (ecoGrid.getMap()[temp.getRow() - 1][temp.getCol()] == null) {
                        state = east;
                        i++;
                        break;
                    } else if (this.getFoodChainRank() > ecoGrid.getMap()[temp.getRow() - 1][temp.getCol()].getFoodChainRank()) {
                        temp = new Location(temp.getRow() - 1, temp.getCol());
                        state = last;
                        i++;
                        break;
                    }
                }
                state = east;
                i++;
                break;
            case east:
                if (temp.getCol() == ecoGrid.getMap().length - 1) {
                    if (ecoGrid.getMap()[temp.getRow()][0] == null) {
                        state = south;
                        i++;
                        break;
                    } else if (this.getFoodChainRank() > ecoGrid.getMap()[temp.getRow()][0].getFoodChainRank()) {
                        temp = ecoGrid.dueWest(temp);
                        state = last;
                        i++;
                        break;
                    }
                    state = south;
                    i++;
                    break;
                } else {
                    if (ecoGrid.getMap()[temp.getRow()][temp.getCol() + 1] == null) {
                        state = south;
                        i++;
                        break;
                    }  else if (this.getFoodChainRank() > ecoGrid.getMap()[temp.getRow()][temp.getCol() + 1].getFoodChainRank()) {
                        temp = new Location(temp.getRow(), temp.getCol() + 1);
                        state = last;
                        i++;
                        break;
                    }
                }
               
                state = south;
                i++;
                break;
            case south:
                if (temp.getRow() == ecoGrid.getMap().length - 1) {
                    if (ecoGrid.getMap()[0][temp.getCol()] == null) {
                        state = west;
                        i++;
                        break;
                    } else if (this.getFoodChainRank() > ecoGrid.getMap()[0][temp.getCol()].getFoodChainRank()) {
                        temp = ecoGrid.dueNorth(temp);
                        state = last;
                        i++;
                        break;
                    }
                    state = west;
                    i++;
                    break;
                } else {
                    if (ecoGrid.getMap()[temp.getRow() + 1][temp.getCol()] == null) {
                        state = west;
                        i++;
                        break;
                    } else if (this.getFoodChainRank() > ecoGrid.getMap()[temp.getRow() + 1][temp.getCol()].getFoodChainRank()) {
                        temp = new Location(temp.getRow() + 1, temp.getCol());
                        state = last;
                        i++;
                        break;
                    }

                }
                state = west;
                i++;
                break;
            default:
                temp = null;
            }
        }
        
        if (temp != null && temp != location) {
            this.timeSinceLastMeal = 0;
            this.incrementTimeSinceLastBreed();
            ecoGrid.remove(temp);
            ecoGrid.add(this, temp);
            ecoGrid.remove(location);
            return true;
        }
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