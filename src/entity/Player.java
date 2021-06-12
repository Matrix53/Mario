package entity;

import entity.box.Box;
import entity.box.PowerUp;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.Collection;
import java.util.HashSet;

/**
 * 游戏的玩家类，使用经典的Mario图像素材进行渲染
 *
 * @author Matrix53
 * @version 1.0
 */
public class Player implements Collidable {
    private int x;
    private int y;
    private int floor;
    private boolean isToUp;
    private boolean isToDown;
    private boolean isToRight;
    private int moveLength;
    private final int jumpHeight;
    private final Image[] image;
    private int animateTimer;
    private int jumpTimer;
    private boolean isWin;
    private boolean isDead;
    private int level;

    /**
     * 玩家的构造方法
     */
    public Player() {
        this.level = 0;
        this.image = new Image[10];
        for (int i = 0; i < 5; ++i) {
            image[i] = new Image("images/player/marioRight" + i + "Lvl" + level + ".png");
            image[i + 5] = new Image("images/player/marioLeft" + i + "Lvl" + level + ".png");
        }
        this.x = 100;
        this.floor = (int) (550 - 85 - image[0].getHeight());
        this.y = floor;
        this.animateTimer = 0;
        this.jumpTimer = 0;
        this.moveLength = 5;
        this.jumpHeight = 5;
        this.isWin = false;
        this.isDead = false;
        this.isToDown = false;
        this.isToUp = false;
        this.isToRight = false;
    }

    /**
     * 可以设置玩家位置的构造方法
     *
     * @param x 玩家的x坐标
     * @param y 玩家的y坐标
     */
    public Player(int x, int y) {
        this.level = 0;
        this.image = new Image[10];
        for (int i = 0; i < 5; ++i) {
            image[i] = new Image("images/player/marioRight" + i + "Lvl" + level + ".png");
            image[i + 5] = new Image("images/player/marioLeft" + i + "Lvl" + level + ".png");
        }
        this.x = x;
        this.floor = (int) (550 - 85 - image[0].getHeight());
        this.y = y;
        this.animateTimer = 0;
        this.jumpTimer = 0;
        this.moveLength = 5;
        this.jumpHeight = 5;
        this.isWin = false;
        this.isDead = false;
        this.isToDown = false;
        this.isToUp = false;
        this.isToRight = false;
    }

    /**
     * 重置玩家的属性，以便开始新的关卡
     */
    public void reset() {
        this.floor = (int) (550 - 85 - image[0].getHeight());
        this.animateTimer = 0;
        this.jumpTimer = 0;
        this.moveLength = 5;
        this.isWin = false;
        this.isDead = false;
        this.isToDown = false;
        this.isToUp = false;
        this.isToRight = false;
        updateLevel();
    }

    /**
     * 得到玩家的高度
     *
     * @return 玩家的高度
     */
    @Override
    public int getHeight() {
        return (int) image[0].getHeight();
    }

    /**
     * 得到玩家的宽度
     *
     * @return 玩家的宽度
     */
    @Override
    public int getWidth() {
        return (int) image[0].getWidth();
    }

    /**
     * 得到玩家的x坐标
     *
     * @return 玩家的x坐标
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * 设置玩家的x坐标
     *
     * @param x 玩家的目标x坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 得到玩家的y坐标
     *
     * @return 玩家的y坐标
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * 设置玩家的y坐标
     *
     * @param y 玩家的目标y坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 设置玩家所处的地面高度
     *
     * @param floor 玩家的地面高度
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * 判断玩家是否正在下落
     *
     * @return 玩家是否在下落
     */
    public boolean isToDown() {
        return isToDown;
    }

    /**
     * 得到玩家的移动速度
     *
     * @return 玩家单位时间内移动长度
     */
    public int getMoveLength() {
        return moveLength;
    }

    /**
     * 得到玩家的图片
     *
     * @return 玩家的图
     */
    public Image getImage() {
        return isToRight ? image[animateTimer / 10] : image[animateTimer / 10 + 5];
    }

    /**
     * 判断玩家是否已经死亡
     *
     * @return 玩家是否死亡
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * 判断玩家是否胜利了
     *
     * @return 玩家是否胜利
     */
    public boolean isWin() {
        return isWin;
    }

    /**
     * 设置玩家胜利状态
     *
     * @param win 玩家目标胜利状态
     */
    public void setWin(boolean win) {
        isWin = win;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isToUp() {
        return isToUp;
    }

    public void setToUp(boolean toUp) {
        isToUp = toUp;
    }

    public void setToDown(boolean toDown) {
        isToDown = toDown;
    }

    public boolean isToRight() {
        return isToRight;
    }

    public void setToRight(boolean toRight) {
        isToRight = toRight;
    }

    public void setMoveLength(int moveLength) {
        this.moveLength = moveLength;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public int getAnimateTimer() {
        return animateTimer;
    }

    public void setAnimateTimer(int animateTimer) {
        this.animateTimer = animateTimer;
    }

    public int getJumpTimer() {
        return jumpTimer;
    }

    public void setJumpTimer(int jumpTimer) {
        this.jumpTimer = jumpTimer;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 玩家升级
     */
    private void updateLevel() {
        int oldHeight = getHeight();
        int oldFloor = floor;
        int oldY = y;
        for (int i = 0; i < 5; ++i) {
            image[i] = new Image("images/player/marioRight" + i + "Lvl" + level + ".png");
            image[i + 5] = new Image("images/player/marioLeft" + i + "Lvl" + level + ".png");
        }
        floor = oldFloor + oldHeight - getHeight();
        y = oldY + oldHeight - getHeight();
    }

    /**
     * 玩家的响应键盘的移动动画
     *
     * @param input 键盘输入
     */
    public void move(HashSet<KeyCode> input) {
        animateTimer++;
        if (animateTimer == 50
                || input.isEmpty()) {
            animateTimer = 0;
        }
        if (input.contains(KeyCode.LEFT)
                && !input.contains(KeyCode.RIGHT)
                && x > 0) {
            x -= moveLength;
            if (isToRight) {
                isToRight = false;
                animateTimer = 0;
            }
        } else if (!input.contains(KeyCode.LEFT)
                && input.contains(KeyCode.RIGHT)
                && x < 800 - getWidth()) {
            x += moveLength;
            if (!isToRight) {
                isToRight = true;
                animateTimer = 0;
            }
        }
        if (input.contains(KeyCode.UP)
                && !input.contains(KeyCode.DOWN)
                && !isToUp
                && !isToDown) {
            isToUp = true;
            floor = 550 - 85 - getHeight();
        } else if (!input.contains(KeyCode.UP)
                && input.contains(KeyCode.DOWN)) {
            jumpTimer = 0;
            isToUp = false;
            isToDown = true;
        }
    }

    /**
     * 玩家跳
     */
    public void jump() {
        for (int i = 0; i < 2; ++i) {
            if (isToUp) {
                y -= jumpHeight;
                jumpTimer++;
                if (jumpTimer == 36) {
                    isToUp = false;
                    isToDown = true;
                }
            } else if (isToDown) {
                jumpTimer = 0;
                y += jumpHeight;
                if (y >= floor) {
                    isToDown = false;
                    y = floor;
                }
            }
        }
    }

    /**
     * 玩家得到旗子
     */
    public void hitFlag() {
        isWin = true;
        isToDown = false;
        isToUp = true;
        moveLength = 0;
        animateTimer = 0;
    }

    /**
     * 玩家撞上了敌人
     *
     * @param enemy 撞上的敌人对象
     */
    public void hitEnemy(Enemy enemy) {
        if (enemy.isDead()) {
            return;
        } else if (isToDown) {
            enemy.remove();
            isToDown = false;
            isToUp = true;
        } else {
            if (level == 0) {
                image[0] = new Image("images/player/marioDead.png");
                moveLength = 0;
                floor = 550;
                isToUp = true;
                isDead = true;
                animateTimer = 0;
            } else {
                level--;
                enemy.changeDirection();
                updateLevel();
            }
        }
    }

    /**
     * 开盒子
     *
     * @param box 目标盒子对象
     */
    public void hitBox(Box box) {
        if (Math.abs(y + getHeight() - box.getY()) < 10) {
            floor = box.getY() - getHeight();
            y = floor;
        } else if (Math.abs(x + getWidth() - box.getX()) <= 10) {
            x -= moveLength;
        } else if (Math.abs(x - box.getX() - box.getWidth()) <= 10) {
            x += moveLength;
        } else {
            if (Math.abs(y - box.getY() - box.getHeight()) <= 10
                    && isToUp) {
                box.open();
                isToUp = false;
                isToDown = true;
            } else if (x <= box.getX()) {
                x -= moveLength;
            } else {
                x += moveLength;
            }
        }
        if ((Math.abs(x - box.getX() - box.getWidth()) <= 5
                || Math.abs(x + getWidth() - box.getX()) <= 5)
                && !isToUp
                && !isToDown) {
            floor = 550 - 85 - getHeight();
            isToDown = true;
        }
    }

    /**
     * 撞上了大蘑菇的事件
     *
     * @param powerUp 蘑菇对象
     */
    public void hitPowerUp(PowerUp powerUp) {
        powerUp.vanish();
        if (level < 2) {
            level++;
            updateLevel();
            floor = 550 - 85 - getHeight();
            if (y > floor) y = floor;
        }
    }

    /**
     * 撞上硬币事件
     *
     * @param coin 硬币对象
     */
    public void hitCoin(Coin coin) {
        coin.vanish();
    }

    /**
     * 玩家撞上管道事件
     *
     * @param pipe 管道对象
     */
    public void hitPipe(Pipe pipe) {
        if (Math.abs(y + getHeight() - pipe.getY()) < 10
                && isToDown) {
            floor = pipe.getY() - getHeight();
            y = floor;
        } else if (Math.abs(x + getWidth() - pipe.getX()) <= 10) {
            x -= moveLength;
        } else if (Math.abs(x - pipe.getX() - pipe.getWidth()) <= 10) {
            x += moveLength;
        }
        if ((Math.abs(x - pipe.getX() - pipe.getWidth()) <= 5
                || Math.abs(x + getWidth() - pipe.getX()) <= 5)
                && Math.abs(y + getHeight() - pipe.getY() - pipe.getHeight()) > 10
                && !isToUp
                && !isToDown) {
            floor = 550 - 85 - getHeight();
            isToDown = true;
        }
    }

    /**
     * 玩家撞上墙的事件
     *
     * @param wall 墙的对象
     */
    public void hitWall(Wall wall, Collection<Wall> walls) {
// 我试图检测有没有方块在人的左边，阻止人从方块中间掉下去，但似乎没有用？
//        boolean blockedH = false;
//        for (Wall otherWall : walls) {
//            // be block
//            if (y >= otherWall.getY()
//                    && otherWall.getY() + 34 >= y
//                    || y + this.getHeight() >= otherWall.getY()
//                    && otherWall.getY() + 34 >= y + this.getHeight()
//            ) {
//                System.out.println("be block1");
//                if (isToRight
//                        && (x + 5 <= otherWall.getX() + 34
//                        && x + 5 >= otherWall.getX()
//                        || x + 5 + this.getWidth() <= otherWall.getX() + 34
//                        && x + 5 + this.getWidth() >= otherWall.getX())
//                ) {
//                    blockedH = true;
////                    x = otherWall.getX() - this.getWidth();
//                    System.out.println("right1: ");
//                    break;
//                } else if (!isToRight
//                        && (x - 5 <= otherWall.getX() + 34
//                        && x - 5 >= otherWall.getX()
//                        || x - 5 + this.getWidth() <= otherWall.getX() + 34
//                        && x - 5 + this.getWidth() >= otherWall.getX())
//                ) {
//                    blockedH = true;
//                    System.out.println("left1");
//                    break;
//                }
//            }
//        }

        if (Math.abs(y + getHeight() - wall.getY()) < 10) {
            floor = wall.getY() - getHeight();
            y = floor;
        } else if (Math.abs(x + getWidth() - wall.getX()) <= 10) {
            x -= moveLength;
        } else if (Math.abs(x - wall.getX() - wall.getWidth()) <= 10) {
                x += moveLength;
        } else {
            // break the wall
            if (Math.abs(y - wall.getY() - wall.getHeight()) <= 10
                    && isToUp) {
                if (level > 0) {
                    wall.setX(-50);
                }
                isToUp = false;
                isToDown = true;
            } else if (x <= wall.getX()) {
                x -= moveLength;
            } else if (x >= wall.getX() + wall.getWidth()  ) {

                    x += moveLength;
            }
        }
        if ((Math.abs(x - wall.getX() - wall.getWidth()) <= 5
                || Math.abs(x + getWidth() - wall.getX()) <= 5)
                && !isToUp
                && !isToDown
        ) {

            boolean willFall = true;
            int direction = 0;
            if (isToRight) {
                direction = 1;
            } else {
                direction = -1;
            }

            for (Wall otherWall : walls) {
                if (otherWall == wall) {
                    continue;
                }
                // can go through
                if (wall.getY() == otherWall.getY()
                ) {
                    if (isToRight
                            && x + 5 <= otherWall.getX() + 34
                            && x + 5 >= otherWall.getX()) {
                        willFall = false;
                        break;
                    } else if (!isToRight
                            && x - 5 <= otherWall.getX() + 34
                            && x - 5 >= otherWall.getX()) {
                        willFall = false;
                        break;
                    }
                }

                // be block
                if (y >= otherWall.getY()
                        && otherWall.getY() + 34 >= y
                        || y + this.getHeight() >= otherWall.getY()
                        && otherWall.getY() + 34 >= y + this.getHeight()
                ) {
                    System.out.println("be block");
                    if (isToRight
                            && (x + 5 <= otherWall.getX() + 34
                            && x + 5 >= otherWall.getX()
                            || x + 5 + this.getWidth() <= otherWall.getX() + 34
                            && x + 5 + this.getWidth() >= otherWall.getX())
                    ) {
                        willFall = false;
                        isToDown = false;
//                        x = wall.getX() + wall.getWidth() - this.getWidth();
                        break;
                    } else if (!isToRight
                            && (x - 5 <= otherWall.getX() + 34
                            && x - 5 >= otherWall.getX()
                            || x - 5 + this.getWidth() <= otherWall.getX() + 34
                            && x - 5 + this.getWidth() >= otherWall.getX())
                    ) {
                        willFall = false;
                        isToDown = false;
//                        x = wall.getX();
                        break;
                    }
                }
            }
            if (willFall) {
                floor = 550 - 85 - getHeight();
                isToDown = true;
            }

        }
    }
}
