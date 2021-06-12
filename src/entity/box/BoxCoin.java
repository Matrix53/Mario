package entity.box;

import entity.Collidable;
import javafx.scene.image.Image;

/**
 * 盒子被撞击后生成的金币
 *
 * @author luxia
 * @version 1.0
 */
public class BoxCoin implements Collidable {
    private int x;
    private int y; // position
    private Image[] image = new Image[4];
    private int timer = 0;
    private int jumpHeight = 5;
    private int jumping = 15;
    private int falling = 0;
    private boolean isVisible = true;

    /**
     * 盒中金币的构造方法
     *
     * @param x 生成实体的x坐标
     * @param y 生成实体的y坐标
     */
    public BoxCoin(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < 4; i++)
            image[i] = new Image("images/boxCoin/boxCoin" + i + ".png");
    }

    /**
     * 返回实体的x坐标
     *
     * @return 实体的x坐标
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * 设置BoxCoin的x坐标
     *
     * @param x 目标x坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 返回实体的y坐标
     *
     * @return 实体的y坐标
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * 返回当前实体的可见性
     *
     * @return 实体是否可见
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * 返回当前帧需要显示的图片
     *
     * @return 当前帧需要显示的图片
     */
    @Override
    public Image getImage() {
        return image[timer / 3];
    }

    @Override
    public int getHeight() {
        return (int) image[0].getHeight();
    }

    @Override
    public int getWidth() {
        return (int) image[0].getWidth();
    }

    /**
     * 实体显示的动画
     */
    public void animate() {
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
        } else {
            // and then the coin vanish
            y = -50;
            this.isVisible = false;
        }
    }
}