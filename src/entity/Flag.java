package entity;

import javafx.scene.image.Image;

/**
 * 碰到旗子后代表本关结束的旗子类
 */
public class Flag implements Collidable{
    private int x, y;
    private Image image = new Image("/assets/images/flag/flag.png");

    public Flag(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * 得到旗子的高度
     * @return 旗子的高度
     */
    @Override
    public int getHeight() {
        return (int) image.getHeight();
    }

    /**
     * 得到旗子的宽度
     * @return 旗子的宽度
     */
    @Override
    public int getWidth() {
        return (int) image.getWidth();
    }

    /**
     * 得到旗子的x坐标
     * @return 旗子的x坐标
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * 得到旗子的y坐标
     * @return 旗子的y坐标
     */
    @Override
    public int getY() {
        return y;
    }


}
