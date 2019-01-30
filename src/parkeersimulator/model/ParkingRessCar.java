package parkeersimulator.model;

import java.util.Random;
import java.awt.*;

public class ParkingRessCar extends Car {
    private static final Color COLOR=Color.green;

    /**
     * Constructor for objects of class ParkingRessCar
     */
    public ParkingRessCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }


    /**
     * Retrieves the color of the car
     * @return a Color data type.
     */
    public Color getColor(){
        return COLOR;
    }
}

