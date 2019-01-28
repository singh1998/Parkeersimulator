package parkeersimulator.model;

import java.util.Random;
import java.awt.*;

public class RessCarLocation extends Car {
    private static final Color COLOR=Color.lightGray;


    public RessCarLocation() {
        int stayMinutes = (int) (Math.floor(Math.random() * 45) + 15);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }



    public Color getColor(){
        return COLOR;
    }
}
