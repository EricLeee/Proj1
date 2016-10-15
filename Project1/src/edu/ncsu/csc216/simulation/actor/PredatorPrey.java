package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Constructor for predator prey
 * @author Jialang Li
 *
 */
public class PredatorPrey extends Animal {

    /**
     * construct predator prey 
     * @param s symbol for the animal
     */
    public PredatorPrey(char s) {
        super(s);
    }

    /**
     * returns color
     * @return color
     */
    @Override
    public Color getColor() {        
        return Configs.getMiddleColor();
    }

    /**
     * animal act
     */
    @Override
    public void act(Location location, EcoGrid ecoGrid) {
        if(this.canAct()) {
            if(this.pastBreedTime(getTimeSinceLastBreed()) &&
                    this.breed(location, ecoGrid)) {
                this.disable();
            } else if (this.eat(location, ecoGrid)) {
                this.disable();
            } else {
                this.incrementTimeSinceLastBreed();
                this.incrementTimeSinceLastMeal();
                this.move(location, ecoGrid);
                this.disable();
            }
        }
        if (this.getTimeSinceLastMeal() >= Configs.getMiddleStarveTime()) {
            this.die();
        }
    }

    /**
     * returns true if the animal has past breed time
     * @return true if the animal has past breed time
     */
    @Override
    protected boolean pastBreedTime(int time) {
        return time >= Configs.getMiddleBreedTime();
    }

    /**
     * returns new animal
     * @return new animal
     */
    @Override
    protected Animal makeNewBaby() {
        PredatorPrey p = new PredatorPrey(this.getSymbol());
        return p;
    }

    /**
     * returns food chain rank
     * @return food chain rank
     */
    @Override
    protected int getFoodChainRank() {
        return Configs.getMiddleFoodChainRank();
    }
}