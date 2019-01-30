/**
 * This class describes a car in the parking garage
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.model;

import java.awt.*;
import java.util.Random;

public abstract class Car {
    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    private Random random = new Random();
    protected int stayMinutes;

    
    /**
     * Constructor for objects of class Car
     */
    public Car() {
    stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
    }

    /**
     * This methods gives the location of the car
     * @return a Location object with the location of the car
     */
    public Location getLocation() {
        return location;
    }

    /**
     * This method sets this car at a specific location
     * @param location an object with a specific location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * This method gives the the minutes that the car still will stay in the parking garage
     * @return an int with the minutes a car has left in the parking garage
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * This method sets the minutes that a car still can stay in the parking garage
     * @param minutesLeft the minutes a car still can stay in the
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     * If this car(customer) is paying, This method gives true.
     * If not, this method gives false
     * @return a boolean that says if a car(customer) is paying or not
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * This method decides whether this car(customer) is paying or not
     * @param isPaying a boolean that decides if this car(customer) is paying
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }
     /**
     * If this car(customer)has to pay, This method gives true.
     * If not, this method gives false
     * @return a boolean that says if a car(customer) has to pay or not
     */
    public boolean getHasToPay() {
        return hasToPay;
    }
    /**
     * This method decides whether this car(customer) has to pay or not
     * @param hasToPay a boolean that decides if this car(customer) has to pay
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     * This method decrements the amount of minutes this car can still stay in the parking garaga
     */
    public void tick() {
        minutesLeft--;
    }

    /**
     * This methods returns the total minutes this car will stay in the parking garage
     * @return an int with the total amount of minutes this car will stay in the parking garage
     */
    public int getStayMinutes(){ return stayMinutes; };

    /**
     * This method gives the color of the car and forces subclasses to implement this method
     * @return an enum with a color
     */
    public abstract Color getColor();
}