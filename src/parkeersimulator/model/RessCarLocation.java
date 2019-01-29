package parkeersimulator.model;

import java.util.Random;
import java.awt.*;

public class RessCarLocation extends Car {
    private static final Color COLOR=Color.lightGray;


    public RessCarLocation() {
        int stayMinutes =  15;
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }



    public Color getColor(){
        return COLOR;
    }
}
