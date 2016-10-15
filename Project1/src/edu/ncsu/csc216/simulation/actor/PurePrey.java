package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Constructor for prey
 * @author Jialang Li
 *
 */
public class PurePrey extends Animal {

    /** age of the animal */
    private int age;
    
    /**
     * 
     * construct prey 
     * @param s symbol for the animal
     */
    public PurePrey(char s) {
        super(s);
        this.age = 0;
    }

    /**
     * returns color
     * @return color
     */
    @Override
    public Color getColor() {
        return Configs.getPreyColor();
    }

    /**
     * animal act
     */
    @Override
    public void act(Location location, EcoGrid ecoGrid) {
        if(this.canAct()) {
            if(this.pastBreedTime(getTimeSinceLastBreed()) &&
                    this.breed(location, ecoGrid)) {
                this.age++;
                this.disable();
            } else {
                this.incrementTimeSinceLastBreed();
                this.age++;
                this.move(location, ecoGrid);
                this.disable();
            }
        }
        if (this.age >= Configs.getPreyStarveTime()) {
            this.die();
        }
        
    }

    /**
     * returns true if the animal has past breed time
     * @return true if the animal has past breed time
     */
    @Override
    protected boolean pastBreedTime(int time) {
        return time >= Configs.getPreyBreedTime();
    }

    /**
     *  returns new animal
     * @return new animal
     */
    @Override
    protected Animal makeNewBaby() {
    	PurePrey p = new PurePrey(this.getSymbol());
    	return p;
    }

    /**
     * returns food chain rank
     * @return food chain rank
     */
    @Override
    protected int getFoodChainRank() {
        return Configs.getPreyFoodChainRank();
    }

}
