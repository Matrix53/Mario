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

    /**
     * 得到当前高度
     * @return 当前高度
     */
    @Override
    public int getHeight() {
        return (int) image[0].getHeight();
    }

    /**
     * 得到当前宽度
     * @return 当前宽度
     */
    @Override
    public int getWidth() {
        return (int) image[0].getWidth();
    }

    /**
     * 设置当前的y坐标
     * @param y 当前的y坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 设置当前的图像
     * @param image 想要设置的图像
     */
    public void setImage(Image[] image) {
        this.image = image;
    }

    /**
     * 得到当前的计时器
     * @return 当前的计时器计数
     */
    public int getTimer() {
        return timer;
    }

    /**
     * 设置当前的计时器计数
     * @param timer 当前的计时器计数
     */
    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * 得到当前的跳跃高度
     * @return 跳跃高度
     */
    public int getJumpHeight() {
        return jumpHeight;
    }

    /**
     * 设置其跳跃高度
     * @param jumpHeight 跳跃高度
     */
    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    /**
     * 得到当前的跳跃状态
     * @return 是否在上升
     */
    public int getJumping() {
        return jumping;
    }

    /**
     * 设置是否在跳跃
     * @param jumping 是否在跳跃
     */
    public void setJumping(int jumping) {
        this.jumping = jumping;
    }

    /**
     * 得到当前的跳跃状态
     * @return 是否在下落
     */
    public int getFalling() {
        return falling;
    }

    /**
     * 设置是否在下落
     * @param falling 是否在下落
     */
    public void setFalling(int falling) {
        this.falling = falling;
    }

    /**
     * 设置BoxCoin的可见性
     * @param visible BoxCoin的可见性
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
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