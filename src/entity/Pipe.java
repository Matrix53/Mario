package entity;

import entity.Collidable;
import javafx.scene.image.Image;

public class Pipe implements Collidable {
    private int x;
    private int y;
    private Image image;

    public Pipe(int x,Image image){
        this.image=image;
        this.x=x;
        this.y=(int)(550-85- image.getHeight());
    }

    @Override
    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x=x;
    }

    @Override
    public int getY(){
        return y;
    }

    @Override
    public int getWidth(){
        return (int)image.getWidth();
    }

    @Override
    public int getHeight(){
        return (int)image.getHeight();
    }

    public Image getImage(){
        return image;
    }
}
