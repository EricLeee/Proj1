package edu.ncsu.csc216.simulation.simulator;

import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

/**
 * Simulator interface
 * @author Jialang Li
 *
 */
public interface SimulatorInterface {

    /**
     * move
     */
    void step();
    
    /**
     * returns location info
     * @return location info
     */
    PaintedLocation[][] getView();
    
    /**
     * returns name
     * @return name
     */
    String[] getNames();
}
