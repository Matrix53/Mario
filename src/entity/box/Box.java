package entity.box;

import entity.Collidable;
import javafx.scene.image.Image;

/**
 * 一个可以开出道具的盒子
 * @author luxia
 * @version 1.0
 */
public class Box implements Collidable {
    private int x;
    private int y; // position
    private final Image[] image; // store the animation of entity.box;
    private final Image usedImage;
    private boolean isOpened;
    private int timer = 0; // timer
    private final PropType propType;
    private BoxCoin coin;
    private PowerUp powerUp;

    /**
     * 含随机道具的盒子的构造方法
     *
     * @param x 盒子的x坐标
     * @param y 盒子的y坐标
     */
    public Box(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = new Image[3];
        for (int i = 0; i < 3; i++) {
            image[i] = new Image("images/box/box" + i + ".png");
        }
        this.usedImage = new Image("images/box/box3.png");
        this.isOpened = false;
        propType = PropType.getRandomType();
    }

    /**
     * 可以指定盒内道具的盒子的构造方法
     *
     * @param x    盒子的坐标x
     * @param y    盒子的坐标y
     * @param type 盒子内道具种类
     */
    public Box(int x, int y, PropType type) {
        this.x = x;
        this.y = y;
        this.image = new Image[3];
        for (int i = 0; i < 3; i++) {
            image[i] = new Image("images/box/box" + i + ".png");
        }
        this.usedImage = new Image("images/box/box3.png");
        this.isOpened = false;
        propType = type;
    }


    /**
     * 返回盒子的高度
     *
     * @return 盒子的高度
     */
    @Override
    public int getHeight() {
        return (int) image[0].getHeight();
    }

    /**
     * 返回盒子的宽度
     *
     * @return 盒子的宽度
     */
    @Override
    public int getWidth() {
        return (int) image[0].getWidth();
    }

    /**
     * 返回盒子的x坐标
     *
     * @return 盒子的x坐标
     */
    @Override
    public int getX() {
        // get the x position
        return x;
    }

    /**
     * 设置盒子的x坐标
     *
     * @param x 将盒子的x坐标设置成x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 返回盒子的y坐标
     *
     * @return 盒子的y坐标
     */
    @Override
    public int getY() {
        // get the y position
        return y;
    }

    /**
     * 设置盒子的y坐标
     *
     * @param y 盒子的目标y坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 返回盒子中装的道具的类型，BOXCOIN或者POWERUP
     *
     * @return 盒子中道具类型
     */
    public PropType getPropType() {
        return propType;
    }

    /**
     * 返回盒子当前显示的图片
     *
     * @return 盒子的图片
     */
    @Override
    public Image getImage() {
        //  image of the entity.box
        return this.isOpened ? usedImage : image[timer / 15];
    }

    /**
     * 得到盒子中的硬币
     *
     * @return 如果盒子有硬币，则得到盒子中的硬币，否则null
     */
    public BoxCoin getCoin() {
        return coin;
    }

    /**
     * 设置盒中金币对象
     *
     * @param coin 目标盒中金币对象
     */
    public void setCoin(BoxCoin coin) {
        this.coin = coin;
    }

    /**
     * 设置箱子中放着的powerup对象
     *
     * @param powerUp powerup对象
     */
    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    /**
     * 得到箱子里装的蘑菇
     *
     * @return 蘑菇对象/null
     */
    public PowerUp getPowerUp() {
        return powerUp;
    }

    /**
     * 检测盒子是否已经被打开过了（道具莫得了）
     *
     * @return 盒子是否已经被打开过了
     */
    public boolean isOpened() {
        return isOpened;
    }

    /**
     * 将盒子设置为已经打开了
     */
    public void setOpen() {
        // set the entity.box opened
        this.isOpened = true;
    }

    public Image getUsedImage() {
        return usedImage;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * 模拟盒子的动画，未开的盒子会闪烁。
     */
    public void animate() {
        // simulate the time
        if (++timer >= 45)
            timer -= 45;
        if (powerUp != null && !powerUp.isVisible()) {
            powerUp = null;
        }
        if (coin != null && !coin.isVisible()) {
            coin = null;
        }
    }

    /**
     * 打开盒子的事件。
     * 如果盒子还没开过，会生成盒子中的物体，并且将盒子设置为已经打开。
     * 如果盒子开过了，则什么都不干。
     */
    public void open() {
        if (isOpened)
            return;
        if (propType == PropType.BOXCOIN) {
            coin = new BoxCoin(x + (int) image[0].getWidth() / 4, y - (int) image[0].getHeight());
        } else {
            powerUp = new PowerUp(x, y - (int) image[0].getHeight());
        }
        isOpened = true;
    }
}
