package entity.box;

import java.util.Random;

/**
 * 盒子中藏着的道具类型
 * @author luxia
 * @version 1.0
 */
public enum PropType {
    BOXCOIN,
    POWERUP;

    static final private double coinProb = 0.8;
    static final private Random rand = new Random();

    /**
     * 返回一个随机的道具类型
     * @return 一个随机的道具类型
     */
    static public PropType getRandomType(){
        return rand.nextDouble() < coinProb ? BOXCOIN : POWERUP;
    }
}
