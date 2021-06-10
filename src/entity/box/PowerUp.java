package entity.box;

import entity.Collidable;
import javafx.scene.image.Image;

public class PowerUp implements Collidable {
    private int x, y;
    private int moveLength = 2;
    private Image image = new Image("images/powerUp/powerUp.png");
    private int jumpHeight = 8;
    private boolean goLeft;
    private boolean isFalling;
    private boolean isVisible = true;

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getHeight() {
        return (int) image.getHeight();
    }

    @Override
    public int getWidth() {
        return (int) image.getWidth();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean getIsVisible(){
        return isVisible;
    }

    public Image getImage() {
        return image;
    }

    public boolean getFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        this.isFalling = falling;
    }

    private void changeMovingSite() {
        /*
         * change the direction
         */
        goLeft = !goLeft;
    }

    public void vanish(){
        isVisible = false;
        y = -50;
    }


}