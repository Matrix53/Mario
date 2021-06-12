package entity;

import javafx.scene.image.Image;

/**
 * 游戏的背景对象
 *
 * @author Matrix53
 * @version 1.0
 */
public class Background {
    private int x;
    private int y;
    private Image image;

    /**
     * 初始化游戏背景的位置及图像
     * @param x 游戏背景的x坐标
     * @param y 游戏背景的y坐标
     */
    public Background(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = new Image("images/background/background.png");
    }

    /**
     * 得到游戏背景的x坐标
     * @return 游戏背景的x坐标
     */
    public int getX() {
        return x;
    }

    /**
     * 得到游戏背景的y坐标
     * @return 游戏背景的y坐标
     */
    public int getY() {
        return y;
    }

    /**
     * 设置游戏背景的x坐标
     * @param x 想要设置的x坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 设置游戏背景的y坐标
     * @param y 想要设置的y坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 得到游戏背景的图像
     * @return 游戏背景的图像
     */
    public Image getImage() {
        return image;
    }
}
