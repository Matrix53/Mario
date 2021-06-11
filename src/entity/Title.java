package entity;

import javafx.scene.image.Image;

/**
 * 关卡开始的时候的介绍页，无碰撞体积
 *
 * @author luxia
 */
public class Title {
    private int x;
    private int y;
    private final Image image;

    /**
     * 生成一个标题的构造函数
     *
     * @param x 标题的x坐标
     * @param y 标题的y坐标
     */
    public Title(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = new Image("/images/background/title.png");
    }

    /**
     * 得到标题的x坐标
     *
     * @return 标题的x坐标
     */
    public int getX() {
        return x;
    }

    /**
     * 得到标题的y坐标
     *
     * @return 标题的y坐标
     */
    public int getY() {
        return y;
    }

    /**
     * 设置标题的x坐标
     *
     * @param x 目标x坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 设置标题的y坐标
     *
     * @param y 目标y坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 得到标题的图
     *
     * @return 需要展示的图
     */
    public Image getImage() {
        return image;
    }
}
