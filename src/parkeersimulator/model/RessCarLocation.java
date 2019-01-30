package parkeersimulator.model;

import java.util.Random;
import java.awt.*;
/**
 * Constructor for objects of class RessCarLocation
 */
public class RessCarLocation extends Car {
    private static final Color COLOR=Color.BLACK;
    private int initialMinutes;
    private Random randomNum;


    public RessCarLocation() {
        randomNum = new Random();
        initialMinutes = 5 + randomNum.nextInt(14) + randomNum.nextInt(11);
        int stayMinutes =  initialMinutes;
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }


    /**
     * Retrieves the color of the car
     * @return a Color data type.
     */
    public Color getColor(){
        return COLOR;
    }
    /**
     * Retrieves the initial amount of minutes the car has been standing there
     * @return an int data type.
     */
    public int getInitialMinutes() {return initialMinutes;}
}
