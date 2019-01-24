package parkeersimulator.model;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private static final Color COLOR=Color.blue;

	
    public ParkingPassCar() {

        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }



    public Color getColor(){
    	return COLOR;
    }
}
