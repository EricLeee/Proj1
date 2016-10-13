package edu.ncsu.csc216.simulation.environment.utils;

/**
 * Construct location for animals
 * @author Jialang Li
 *
 */
public class Location {

    /** numbers of row */
    private int row;
    /** numbers of column */
    private int column;
    
    /**
     * construct location
     * @param row numbers of rows
     * @param column number of column
     */
    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }
    /**
     * returns row
     * @return number of row
     */
    public int getRow() {
        return this.row;
    }
    
    /**
     * returns number of column
     * @return number of column
     */
    public int getCol() {
        return this.column;
    }
}
