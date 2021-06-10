package entity.box;

import entity.Collidable;
import javafx.scene.image.Image;


public class Box implements Collidable {
    private int x, y; // position
    private Image[] image = new Image[3]; // store the animation of entity.box;
    private Image usedImage = new Image("images/entity.box/box3.png");
    private boolean isOpened = false;
    private int timer = 0; // timer

    private PropType type;
    

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < 3; i++)
            image[i] = new Image("images/entity.box/entity.box" + i + ".png");
        
    }

    @Override
    public int getHeight() {
        return (int) image[0].getHeight();
    }

    @Override
    public int getWidth() {
        return (int) image[0].getWidth();
    }

    public int getX() {
        // get the x position
        return x;
    }

    public int getY() {
        // get the y position
        return y;
    }

    public Image getImage() {
        //  image of the entity.box
        return this.isOpened ? usedImage : image[timer / 15];
    }

    public boolean isOpened() {
        /*
         * check whether the entity.box has been opened
         */
        return isOpened;
    }

    public void setOpen() {
        // set the entity.box opened
        this.isOpened = true;
    }

    public void animate() {
        // simulate the time
        if (++timer >= 45)
            timer -= 45;
    }

}
