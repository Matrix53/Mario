package entity.box;

import java.util.Random;

public enum PropType {
    BOXCOIN,
    POWERUP;

    static final private double coinProb = 0.9;
    static final private Random rand = new Random();

    public PropType getRandomType(){
        return rand.nextDouble() < coinProb ? BOXCOIN : POWERUP;
    }
}
