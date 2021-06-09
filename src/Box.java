import javafx.scene.image.Image;

public class Box {
    private int x, y; // position
    private Image[] image = new Image[3]; // store the animation of box;
    private Image usedImage = new Image("images/box/box3.png");
    private boolean isOpened = false;
    private int i = 0; // timer

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < 3; i++)
            image[i] = new Image("images/box/box" + i + ".png");
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
        //  image of the box
        return this.isOpened ? usedImage : image[i / 15];
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpen() {
        // set the box opened
        this.isOpened = true;
    }

    public void animate() {
        // simulate the time
        if (++i >= 45)
            i -= 45;
    }

}
