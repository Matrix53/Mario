package entity;

import javafx.scene.image.Image;

/**
 * 碰到旗子后代表本关结束的旗子类
 */
public class Flag implements Collidable {
    private int x;
    private int y;
    private final Image image;

    /**
     * 生成终点小旗子的构造函数
     *
     * @param x 小旗子的x坐标
     * @param y 小旗子的y坐标
     */
    public Flag(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = new Image("images/flag/flag.png", 150, 300, false, true);
    }

    /**
     * 得到旗子的高度
     *
     * @return 旗子的高度
     */
    @Override
    public int getHeight() {
        return (int) image.getHeight();
    }

    /**
     * 得到旗子的宽度
     *
     * @return 旗子的宽度
     */
    @Override
    public int getWidth() {
        return (int) image.getWidth();
    }

    /**
     * 得到旗子的x坐标
     *
     * @return 旗子的x坐标
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * 设置小旗子的x坐标
     *
     * @param x 小旗子的目标x坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 设置小旗子的y坐标
     *
     * @param y 小旗子的目标x坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 得到旗子的y坐标
     *
     * @return 旗子的y坐标
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * 小旗子显示的图片
     *
     * @return 小旗子图片
     */
    public Image getImage() {
        return image;
    }
}
