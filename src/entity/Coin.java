package entity;

import javafx.scene.image.Image;

/**
 * 需要自己收集的硬币
 *
 * @author luxia
 * @version 1.0
 */
public class Coin implements Collidable {
    private int x;
    private int y;
    private final Image[] image = new Image[3];
    private int timer;

    /**
     * 生成硬币的构造方法
     *
     * @param x x坐标
     * @param y y坐标
     */
    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < 3; i++) {
            image[i] = new Image("images/coin/coin" + i + ".png");
        }

    }

    /**
     * 得到硬币的尺寸高度
     *
     * @return 硬币尺寸中的高度
     */
    @Override
    public int getHeight() {
        return (int) image[0].getHeight();
    }

    /**
     * 得到硬币的宽度
     *
     * @return 硬币的宽度
     */
    @Override
    public int getWidth() {
        return (int) image[0].getWidth();
    }

    /**
     * 得到硬币的x坐标
     *
     * @return 硬币的x坐标
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * 设置硬币的x坐标
     *
     * @return 硬币的x坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 得到硬币的y坐标
     *
     * @return 硬币的y坐标
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * 设置硬币的y坐标
     *
     * @return 硬币的y坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 得到当前硬币的计时器的计数
     * @return 计时器的计数
     */
    public int getTimer() {
        return timer;
    }

    /**
     * 设置硬币的计时器计数
     * @param timer 计时器的计数
     */
    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * 硬币的动画
     */
    public void animate() {
        if (++timer >= 50)
            timer -= 50;
    }

    /**
     * 得到当前硬币的图片
     *
     * @return 当前硬币的图片
     */
    @Override
    public Image getImage() {
        if (timer < 28)
            return image[0];
        else if (timer < 37)
            return image[1];
        else
            return image[2];
    }

    /**
     * 让硬币消失
     */
    public void vanish() {
        x = -50;
    }
}
