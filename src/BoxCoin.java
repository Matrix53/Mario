import javafx.scene.image.Image;

class BoxCoin {
    private int x, y; // position
    private Image[] image = new Image[4];
    private int timer = 0;
    private int jumpHeight = 5;
    private int jumping = 15;
    private int falling = 0;

    public BoxCoin(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < 4; i++)
            image[i] = new Image("images/boxCoin/boxCoin" + i + ".png");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image[timer / 3];
    }

    public void animate() {
        /*
        * the animation of the coin from the box
        */
        if (++timer >= 12) timer -= 12;

        // one jump of the coin
        if (jumping > 0) {
            y -= jumpHeight;
            jumping--;
            if (jumping == 0)
                falling = 15;
        } else if (falling > 0) {
            y += jumpHeight;
            falling--;
        } else // and then the coin vanish
            x = -50;
    }
}