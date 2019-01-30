/**
 * This class represents a car of a subscription-customer
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.model;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private static final Color COLOR=Color.blue;

    /**
     * Constructor for objects of class ParkingPassCar
     */
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
