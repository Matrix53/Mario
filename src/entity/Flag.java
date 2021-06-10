package entity;

import javafx.scene.image.Image;

public class Flag implements Collidable{
    private int x, y;
    private Image image = new Image("/assets/images/flag/flag.png");

    public Flag(int x, int y){
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

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }


}
