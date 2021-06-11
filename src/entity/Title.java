package entity;

import javafx.scene.image.Image;

public class Title {
    private int x, y;
    private Image image = new Image("/images/background/title.png");

    public Title(int x, int y){
        this.x = x;
        this.y = y;
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

    public Image getImage(){
        return image;
    }
}
