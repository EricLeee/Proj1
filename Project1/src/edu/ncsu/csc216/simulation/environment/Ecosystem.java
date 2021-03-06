package edu.ncsu.csc216.simulation.environment;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.environment.utils.*;

/**
 * Constructor of the Ecosystem
 * @author Jialang Li
 *
 */
public class Ecosystem implements EcoGrid {
   
    /** number of max rows */
    private int maxRows;
    /** number of max column */
    private int maxCols;
    /** ecosystem map */
    private Animal[][] map;

    /**
     * Construct new ecosystem
     * @param row number of rows 
     * @param col number of column
     */
    public Ecosystem(int row, int col) {
        this.maxRows = row;
        this.maxCols = col;
        this.map = new Animal[maxRows][maxCols];
    }
    /**
     * check if the location is empty
     * @return true if location empty
     */
    @Override
    public boolean isEmpty(Location location) {
        Animal a = this.getItemAt(location);
        if (a == null) {
            return true;
        }
        return false;
    }

    /**
     * returns item at location
     * @return item at location
     */
    @Override
    public Animal getItemAt(Location location) {
        return this.map[location.getRow()][location.getCol()];
    }

    /**
     * remove the animal from the location
     */
    @Override
    public void remove(Location place) {
        this.map[place.getRow()][place.getCol()] = null;
    }

    /**
     * add animal in specific location
     */
    @Override
    public void add(Animal x, Location location) {
        this.map[location.getRow()][location.getCol()] = x;
    }

    /**
     * returns the first empty neighbor
     * @return the first empty neighbor
     */
    @Override
    public Location findFirstEmptyNeighbor(Location position, int startDirection) {
        Location location = null;
        Location temp = position;
        
        final int west = 0;
        final int north = 1;
        final int east = 2;
        final int south = 3;
        final int last = 4;
        int state = startDirection;
        int i = 0;
        
        while (state < last && i < 4) {
        	switch(state) {
        	case west:
        	    if (temp.getCol() == 0) {
        			if (this.map[temp.getRow()][this.maxCols - 1] == null) {
        				location = this.dueEast(temp);
        				state = last;
        				i++;
        				break;
        			} 
        			
        			state = north;
        			i++;
        			break;
        		} else if (this.map[temp.getRow()][temp.getCol() - 1] == null) {
        			location = new Location(temp.getRow(), temp.getCol() - 1);
        			state = last;
        			i++;
        			break;
        		}
        		state = north;
        		i++;
        		break;
        	case north:
        		if (temp.getRow() == 0) {
        			if (this.map[this.maxRows - 1][temp.getCol()] == null) {
        				location = this.dueSouth(temp);
        				state = last;
        				i++;
        				break;
        			}
        			state = east;
        			i++;
        			break;
        		} else if (this.map[temp.getRow() - 1][temp.getCol()] == null) {
        			location = new Location(temp.getRow() - 1, temp.getCol());
        			state = last;
        			i++;
        			break;
        		}
        		state = east;
        		i++;
        		break;
        	case east:
        		if (temp.getCol() == this.maxCols - 1) {
        			if (this.map[temp.getRow()][0] == null) {
        				location = this.dueWest(temp);
        				state = last;
        				i++;
        				break;
        			}
        			state = south;
        			i++;
        			break;
        		} else if (this.map[temp.getRow()][temp.getCol() + 1] == null) {
        			location = new Location(temp.getRow(), temp.getCol() + 1);
        			state = last;
        			i++;
        			break;
        		}
        		state = south;
        		i++;
        		break;
        	case south:
        		if (temp.getRow() == this.maxRows - 1) {
        			if (this.map[0][temp.getCol()] == null) {
        				location = this.dueNorth(temp);
        				state = last;
        				i++;
        				break;
        			}
        			state = west;
        			i++;
        			break;
        		} else if (this.map[temp.getRow() + 1][temp.getCol()] == null) {
        			location = new Location(temp.getRow() + 1, temp.getCol());
        			state = last;
        			i++;
        			break;
        		}
        		state = west;
        		i++;
        		break;
        	default:
        	    location = null;
        	}
        }
		return location;
    }

    /**
     * returns due north location
     * @return location
     */
    @Override
    public Location dueNorth(Location x) {
        Location location = new Location(0, x.getCol());
        return location;
    }

    /**
     * returns due south location
     * @return due south location
     */
    @Override
    public Location dueSouth(Location x) {
        Location location = new Location(this.maxRows - 1, x.getCol());
        return location;
    }

    /**
     * returns due west location
     * @return due west location
     */
    @Override
    public Location dueWest(Location x) {
        Location location = new Location(x.getRow(), 0);
        return location;
    }

    /**
     * returns due east location
     * @return due east location
     */
    @Override
    public Location dueEast(Location x) {
        Location location = new Location(x.getRow(), this.maxCols - 1);
        return location;
    }

    /**
     * returns map
     * @return map
     */
    @Override
    public Animal[][] getMap() {
        return this.map;
    }

    /**
     * enable the living animals
     */
    @Override
    public void enableTheLiving() {
        for(int i = 0; i < this.maxRows; i++) {
            for(int j = 0; j < this.maxCols; j++) {
                if(map[i][j] != null && map[i][j].isAlive()) {
                    map[i][j].enable();
                }
            }
        }
    }

    /**
     * eliminate dead animals
     */
    @Override
    public void buryTheDead() {
        for (int i = 0; i < this.maxRows; i++) {
            for (int j = 0; j < this.maxCols; j++) {
                if(map[i][j] != null && !map[i][j].isAlive()) {
                    map[i][j] = null;
                }
            }
         }
    }
}
