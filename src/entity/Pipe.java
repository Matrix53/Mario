package entity;

import javafx.scene.image.Image;

/**
 * 管道
 *
 * @author Matrix53
 * @author luxia
 * @version 1.1
 */
public class Pipe implements Collidable {
    private int x;
    private int y;
    private final Image image;

    /**
     * 管道的构造方法
     *
     * @param x     管道的x坐标
     * @param image 管道的图片
     */
    public Pipe(int x, Image image) {
        this.image = image;
        this.x = x;
        this.y = (int) (550 - 85 - image.getHeight());
    }

    /**
     * 得到管道的x坐标
     *
     * @return 管道的x坐标
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * 设置管道的x坐标
     *
     * @param x 管道的目标x坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 得到管道的y坐标
     *
     * @return 管道的y坐标
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * 设置管道的y坐标
     * @param y 管道的y坐标
     */
    public void setY(int y){
        this.y=y;
    }

    /**
     * 得到管道的宽度
     *
     * @return 管道的宽度
     */
    @Override
    public int getWidth() {
        return (int) image.getWidth();
    }

    /**
     * 得到管道的高度
     *
     * @return 管道的高度
     */
    @Override
    public int getHeight() {
        return (int) image.getHeight();
    }

    /**
     * 得到管道显示的图片
     * @return 管道的图片
     */
    public Image getImage() {
        return image;
    }
}
