package parkeersimulator.model;

import java.util.Random;
import java.awt.*;

/**
 * Constructor for objects class AdHocCar
 */
public class ParkingPassCar extends Car {
    private static final Color COLOR=Color.blue;


    public ParkingPassCar() {

        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }


    /**
     * This method gives the color of the car
     * @return an enum with a color
     */
    public Color getColor(){
        return COLOR;
    }
}
