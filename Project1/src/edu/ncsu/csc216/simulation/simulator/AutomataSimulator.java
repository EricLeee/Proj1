package edu.ncsu.csc216.simulation.simulator;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.simulation.actor.*;
import edu.ncsu.csc216.simulation.environment.*;
import edu.ncsu.csc216.simulation.environment.utils.*;

/**
 * Simulator construction
 * @author Jialang Li
 *
 */
public class AutomataSimulator implements SimulatorInterface {

    /** size of environment */
    private static final int SIZE = 20;
    /** threshold */
    private static final int THRESHOLD = 2;
    /** size error message string */
    private static final String SIZE_ERROR_MESSAGE = "File required to initialize the ecosystem";
    /** threshold error message string */
    private static final String THRESHOLD_ERROR_MESSAGE = "At least two different species are required.";
    /** name */
    private String[] names;
    /** number of names */
    private int numberOfNames;
    /** symbol */
    private char[] symbol;
    /** empty symbol */
    private static final char EMPTY = '.';
    /** ecosystem map for the simulator */
    private EcoGrid simpleSystem;
    
    /**
     * initialize simulator
     * @param init initial population file
     */
    public AutomataSimulator(String init) {
        Configs.setToDefaults();
        Scanner initScanner;
        try {
            initScanner = new Scanner(new FileInputStream(init));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
        
        this.numberOfNames = initScanner.nextInt();
        if (numberOfNames <= THRESHOLD) {
            initScanner.close();
            throw new IllegalArgumentException(THRESHOLD_ERROR_MESSAGE);
        }
        
        this.names = new String[numberOfNames];
        this.symbol = new char[numberOfNames];
        
        initScanner.nextLine();

        Scanner l = null;
        for (int i = 0; i < this.numberOfNames; i++) {
            l = new Scanner(initScanner.nextLine());
            symbol[i] = l.next().charAt(0);
            names[i] = symbol[i] + l.nextLine();
        }

        this.simpleSystem = new Ecosystem(SIZE, SIZE);
        for (int i = 0; i < SIZE; i++) {
        	String s = initScanner.next();
            for (int j = 0; j < SIZE; j++) {
                char c = s.charAt(j);
                if (c != EMPTY) {
                	if (c == symbol[0]) {
                		this.simpleSystem.add(new PurePredator(c), new Location(i, j));
                	} else if (c == symbol[numberOfNames - 1]) {
                		this.simpleSystem.add(new PurePrey(c), new Location(i, j));
                	} else {
                		this.simpleSystem.getMap()[i][j] = new PredatorPrey(c);
                	}
                }
            }
        }
        initScanner.close();
        l.close();
        
        this.simpleSystem.enableTheLiving();
    }
    
    /**
     * initialize simulator 
     * @param init name of the initial population info 
     * @param conf name of the configuration file
     */
    public AutomataSimulator(String init, String conf) {
        this(init);

        Scanner confScanner = null;
        try {
            confScanner = new Scanner(new FileInputStream(conf));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
        
        Color[] color = new Color[3];
        for (int i = color.length - 1; i >= 0; i--) {
            color[i] = Color.decode("0x" + confScanner.next());
        }
        confScanner.nextLine();
        
        int[] starveTime = new int[3];
        for (int i = starveTime.length - 1; i >= 0; i--) {
            starveTime[i] = confScanner.nextInt();
        }
        confScanner.nextLine();
        int[] breedTime = new int[3];
        for (int i = breedTime.length - 1; i >= 0; i--) {
            breedTime[i] = confScanner.nextInt();
        }
        
        confScanner.close();
        Configs.initConfigs(color, starveTime, breedTime);
        
        this.simpleSystem.enableTheLiving();
    }
    
    /**
     * step
     */
    public void step() {
        simpleSystem.enableTheLiving();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Location loc = new Location(i, j);
                Animal a = simpleSystem.getItemAt(loc);
                if (a != null) {
                    a.act(loc, simpleSystem);
                }
            }
        }
        simpleSystem.buryTheDead();
    }
    
    /**
     * get view
     * @return view
     */
    public PaintedLocation[][] getView() {
        PaintedLocation[][] p = new PaintedLocation[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if (this.simpleSystem.getMap()[i][j] != null) {
                    p[i][j] = new PaintedLocation(i, j, this.simpleSystem.getMap()[i][j].getColor(),
                            this.simpleSystem.getMap()[i][j].getSymbol());
                } else {
                    p[i][j] = new PaintedLocation(i, j, Color.BLACK, ' ');
                }
                
            }
        }
        return p;
    }
    
    /**
     * returns name
     * @return name
     */
    public String[] getNames() {
        return this.names;
    }
}
