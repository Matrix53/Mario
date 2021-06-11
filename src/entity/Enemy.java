package entity;

import javafx.scene.image.Image;

/**
 * 游戏的敌人类，目前只有蘑菇头图像素材
 *
 * @author Matrix53
 * @version 1.0
 */
public class Enemy implements Collidable {
    private int x;
    private int y;
    private boolean isToLeft;
    private boolean isDead;
    private int removeTimer;
    private int moveLength;
    private final Image[] image;
    private int animateTimer;
    private int floor;

    /**
     * 生成一个敌人的构造函数
     */
    public Enemy() {
        this.image = new Image[2];
        image[0] = new Image("images/enemy/enemy1.png");
        image[1] = new Image("images/enemy/enemy2.png");
        this.x = 800;
        this.y = (int) (550 - 85 - image[0].getHeight());
        this.isToLeft = true;
        this.isDead = false;
        this.removeTimer = 0;
        this.moveLength = 2;
        this.animateTimer = 0;
        this.floor = y;
    }

    public Enemy(int x, int y) {
        this.image = new Image[2];
        image[0] = new Image("images/enemy/enemy1.png");
        image[1] = new Image("images/enemy/enemy2.png");
        this.x = x;
        this.y = y;
        this.isToLeft = true;
        this.isDead = false;
        this.removeTimer = 0;
        this.moveLength = 2;
        this.animateTimer = 0;
        this.floor = y;
    }

    /**
     * 得到敌人的x坐标
     *
     * @return x坐标
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * 设置敌人的x坐标
     *
     * @param x 目标x坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 得到敌人的y坐标
     *
     * @return 敌人的y坐标
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * 设置敌人的y坐标
     *
     * @param y 敌人的目标y坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 判断是否正在向左走
     *
     * @return 是否在往左走
     */
    public boolean isToLeft() {
        return isToLeft;
    }

    /**
     * 改变敌人行进方向
     *
     * @param toLeft 敌人行进方向是否在左侧
     */
    public void setToLeft(boolean toLeft) {
        isToLeft = toLeft;
    }

    /**
     * 得到敌人的单位时间移动长度
     *
     * @return 单位时间移动长度
     */
    public int getMoveLength() {
        return moveLength;
    }

    /**
     * 设置移动速度
     *
     * @param moveLength 目标的单位时间移动长度
     */
    public void setMoveLength(int moveLength) {
        this.moveLength = moveLength;
    }

    /**
     * 得到敌人目前显示的图片
     *
     * @return 敌人显示的图片
     */
    @Override
    public Image getImage() {
        return animateTimer < 20 ? image[0] : image[1];
    }

    /**
     * 得到敌人的高度
     *
     * @return 敌人的高度
     */
    @Override
    public int getHeight() {
        return (int) getImage().getHeight();
    }

    /**
     * 得到敌人的宽度
     *
     * @return 敌人的宽度
     */
    @Override
    public int getWidth() {
        return (int) getImage().getWidth();
    }

    /**
     * 得到敌人是否已经死亡
     *
     * @return 敌人是否死亡
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * 得到所在的地面高度
     *
     * @return 地面高度
     */
    public int getFloor() {
        return floor;
    }

    /**
     * 设置敌人所在的地面高度
     *
     * @param floor 目标地面高度
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * 反转敌人的运动方向
     */
    public void changeDirection() {
        isToLeft = !isToLeft;
    }

    /**
     * 敌人的移动动画
     */
    public void move() {
        if (removeTimer > 0) {
            if (removeTimer < 30) {
                removeTimer++;
            } else {
                removeTimer = 0;
                x = -50;
            }
        } else if (isToLeft) {
            if (x > 0) x -= moveLength;
            else isToLeft = false;
        } else {
            if (x + image[0].getWidth() < 800) x += moveLength;
            else isToLeft = true;
        }
        animateTimer++;
        if (animateTimer == 40) animateTimer = 0;
    }

    /**
     * 移除敌人
     */
    public void remove() {
        isDead = true;
        Image deadImage = new Image("images/enemy/enemyDead.png");
        image[0] = deadImage;
        image[1] = deadImage;
        floor = (int) (550 - 85 - image[0].getHeight());
        y = floor;
        moveLength = 0;
        removeTimer = 1;
    }
}
