package entity;

import entity.Collidable;
import javafx.scene.image.Image;

/**
 * 一级玩家不可摧毁，二级玩家可以摧毁的砖块类，具有碰撞体积
 *
 * @author Matrix53
 * @version 1.0
 */
public class Wall implements Collidable {
    private int x;
    private int y;
    private final Image image;

    /**
     * 生成一个砖块
     *
     * @param x 砖块的x坐标
     * @param y 砖块的y坐标
     */
    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = new Image("images/wall/wall.png");
    }

    /**
     * 得到砖块的x坐标
     *
     * @return 砖块的x坐标
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * 得到墙的y坐标
     *
     * @return 墙的y坐标
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * 设置砖块的x坐标
     *
     * @param x x坐标
     */
    public void setX(int x) {
        this.x = x;
    }


    /**
     * 设置砖块的y坐标
     *
     * @param y 砖块的y坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 得到砖块的高度
     *
     * @return 砖块的高度
     */
    @Override
    public int getHeight() {
        return (int) image.getHeight();
    }

    /**
     * 得到砖块的宽度
     *
     * @return 砖块的宽度
     */
    @Override
    public int getWidth() {
        return (int) image.getWidth();
    }

    /**
     * 得到目标的图片
     *
     * @return 需要显示的图
     */
    public Image getImage() {
        return image;
    }
}
