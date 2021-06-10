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

    public PowerUp(int x, int y){
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    Image getImage() {
        return image;
    }

    boolean getFalling(){
        return isFalling;
    }
    void setFalling(boolean falling) {
        this.isFalling = falling;
    }

    private void changeMovingSite() {
        goLeft = !goLeft;
    }
    
}