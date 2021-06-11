package entity;

import javafx.scene.image.Image;

public class Title {
    private int x;
    private int y;
    private Image image;

    public Title(int x, int y){
        this.x = x;
        this.y = y;
        this.image= new Image("/images/background/title.png");
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public Image getImage(){
        return image;
    }
}
