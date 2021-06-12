package entity.box;

import entity.Collidable;
import javafx.scene.image.Image;

/**
 * 可以使玩家升级的大蘑菇
 *
 * @author luxia
 * @version 1.0
 */
public class PowerUp implements Collidable {
    private int x;
    private int y;
    private final int moveLength;
    private final Image image;
    private final int jumpHeight;
    private boolean goLeft;
    private boolean isFalling;
    private boolean isVisible;

    /**
     * 生成一个powerup的大蘑菇
     *
     * @param x 蘑菇的x坐标
     * @param y 蘑菇的y坐标
     */
    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
        this.moveLength = 2;
        this.jumpHeight = 8;
        this.image = new Image("images/powerUp/powerUp.png");
        this.isVisible = true;
    }

    /**
     * 得到蘑菇的高度
     *
     * @return 蘑菇高度
     */
    @Override
    public int getHeight() {
        return (int) image.getHeight();
    }

    /**
     * 得到蘑菇的宽度
     *
     * @return 蘑菇的宽度
     */
    @Override
    public int getWidth() {
        return (int) image.getWidth();
    }

    /**
     * 得到蘑菇的x坐标
     *
     * @return 蘑菇的x坐标
     */
    public int getX() {
        return x;
    }

    /**
     * 设置大蘑菇的x坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 得到蘑菇的y坐标
     *
     * @return 蘑菇的y坐标
     */
    public int getY() {
        return y;
    }

    /**
     * 判断蘑菇是否可见， 当蘑菇被玩家吃掉后会不可见
     *
     * @return 蘑菇是否可见
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * 设置PowerUp的y坐标
     * @param y PowerUp的y坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 得到PowerUp的水平移动速度
     * @return 水平移动速度
     */
    public int getMoveLength() {
        return moveLength;
    }

    /**
     * 得到PowerUp的跳跃高度
     * @return 跳跃高度
     */
    public int getJumpHeight() {
        return jumpHeight;
    }

    /**
     * 得到PowerUp的水平移动状态
     * @return 水平移动状态
     */
    public boolean isGoLeft() {
        return goLeft;
    }

    /**
     * 设置PowerUp的水平移动状态
     * @param goLeft 水平移动状态
     */
    public void setGoLeft(boolean goLeft) {
        this.goLeft = goLeft;
    }

    /**
     * PowerUp是否在降落
     * @return 是否在降落
     */
    public boolean isFalling() {
        return isFalling;
    }

    /**
     * 设置PowerUp的可见性
     * @param visible PowerUp的可见性
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * 得到蘑菇的图片
     *
     * @return 图
     */
    @Override
    public Image getImage() {
        return image;
    }

    /**
     * 判断这个蘑菇是不是正在下落
     *
     * @return 蘑菇是否在下落
     */
    public boolean getFalling() {
        return isFalling;
    }

    /**
     * 设置蘑菇的下落状态
     *
     * @param falling 蘑菇是否正在下落
     */
    public void setFalling(boolean falling) {
        this.isFalling = falling;
    }

    /**
     * 反转蘑菇的行进方向
     */
    public void changeDirection() {
        goLeft = !goLeft;
    }

    /**
     * 使得蘑菇消失（发生在诸如被玩家吃掉后
     */
    public void vanish() {
        isVisible = false;
        y = -50;
    }

    /**
     * 蘑菇的移动动画
     */
    public void move() {
        if (goLeft) {
            if (x > 0) {
                x -= moveLength;
            } else {
                goLeft = false;
            }
        } else if (x < 800 - getWidth()) {
            x += moveLength;
        } else {
            goLeft = true;
        }
        if (isFalling) {
            y += jumpHeight;
            if (Math.abs(550 - 85 - getHeight() - y) <= 10) {
                isFalling = false;
                y = 550 - 85 - getHeight();
            }
        }
    }
}