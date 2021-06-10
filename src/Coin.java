import javafx.scene.image.Image;

public class Coin implements Collidable{
    private int x, y;
    private Image[] image = new Image[3];
    private int timer;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < 3; i++) {
            image[i] = new Image("images/coin/coin" + i + ".png");
        }

    }

    @Override
    public int getHeight() {
        return (int)image[0].getHeight();
    }

    @Override
    public int getWidth() {
        return (int)image[0].getWidth();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    void animate() {
        if (++timer >= 50)
            timer -= 50;
    }

    Image getImage(){
        if(timer < 28)
            return image[0];
        else if (timer < 37)
            return image[1];
        else
            return image[2];
    }
}
