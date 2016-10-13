package edu.ncsu.csc216.simulation.environment.utils;

import java.awt.Color;

/**
 * painted location constructor
 * @author Jialang Li
 *
 */
public class PaintedLocation extends Location {

    /** tint */
    private Color tint;
    /** symbol */
    private char symbol;
    
    /**
     * construct painted location
     * @param row number of rows
     * @param col number of column
     * @param animalColor animal color
     * @param animalSymbol animal symbol
     */
    public PaintedLocation(int row, int col, Color animalColor, char animalSymbol) {
        super(row, col);
        this.tint = animalColor;
        this.symbol = animalSymbol;
    }
    
    /**
     * returns color
     * @return color
     */
    public Color getColor() {
        return this.tint;
    }
    
    /**
     * returns symbol
     * @return symbol
     */
    public char getSymbol() {
        return this.symbol;
    }
    
}
