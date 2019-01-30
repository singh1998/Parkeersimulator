/**
 * This class represents a car of a regular customer
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.model;

import java.util.Random;
import java.awt.*;

/**
 * Constructor for objects of class AdHocCar
 */
public class AdHocCar extends Car {
	private static final Color COLOR=Color.red;
    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }


    /**
     * This method gives the color of the car
     * @return an enum with a color
     */
    public Color getColor(){
    	return COLOR;
    }
}
