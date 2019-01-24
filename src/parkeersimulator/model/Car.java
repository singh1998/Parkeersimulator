package parkeersimulator.model;

import java.awt.*;
import java.util.Random;

public abstract class Car {
    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    Random random = new Random();
    protected int stayMinutes;

    
    /**
     * Constructor for objects of class Car
     */
    public Car() {
    stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }
    public int getStayMinutes(){ return stayMinutes; };
    
    public abstract Color getColor();
}