package entity.box;

import entity.Collidable;
import javafx.scene.image.Image;

public class PowerUp implements Collidable {
    private int x;
    private int y;
    private int moveLength;
    private Image image;
    private int jumpHeight;
    private boolean goLeft;
    private boolean isFalling;
    private boolean isVisible;

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
        this.moveLength=2;
        this.jumpHeight=8;
        this.image=new Image("images/powerUp/powerUp.png");
        this.isVisible=true;
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

    public boolean isVisible() {
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

    public void changeDirection() {
        goLeft = !goLeft;
    }

    public void vanish(){
        isVisible = false;
        y = -50;
    }

    public void move() {
        if(goLeft){
            if(x>0){
                x-=moveLength;
            }else{
                goLeft=false;
            }
        }else if(x<800-getWidth()){
            x += moveLength;
        }else{
            goLeft=true;
        }
        if(isFalling){
            y+=jumpHeight;
            if(Math.abs(550-85-getHeight()-y)<=10){
                isFalling=false;
                y=550-85-getHeight();
            }
        }
    }
}