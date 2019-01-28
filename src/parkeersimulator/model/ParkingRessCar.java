package parkeersimulator.model;

import java.util.Random;
import java.awt.*;

public class ParkingRessCar extends Car {
    private static final Color COLOR=Color.gray;


    public ParkingRessCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }



    public Color getColor(){
        return COLOR;
    }
}

