package entity;

import entity.Collidable;
import javafx.scene.image.Image;

/**
 * 游戏的敌人类，目前只有蘑菇头图像素材
 * @author Matrix53
 * @version 1.0
 */
public class Enemy implements Collidable {
    private int x;
    private int y;
    private boolean isToLeft;
    private int removeTimer;
    private int moveLength;
    private Image[] image;
    private int animateTimer;
    private int floor;

    public Enemy() {
        this.x=800;
        this.y=(int)(550-85-image[0].getHeight());
        this.isToLeft=true;
        this.removeTimer=0;
        this.moveLength=2;
        this.image=new Image[2];
        image[0]=new Image("images/enemy/enemy1.png");
        image[1]=new Image("images/enemy/enemy2.png");
        this.animateTimer=0;
        this.floor=y;
    }

    /**
     * 方法描述
     * @return 返回值是什么
     */
    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isToLeft() {
        return isToLeft;
    }

    public void setToLeft(boolean toLeft) {
        isToLeft = toLeft;
    }

    public int getMoveLength() {
        return moveLength;
    }

    public void setMoveLength(int moveLength) {
        this.moveLength = moveLength;
    }

    public Image getImage() {
        return animateTimer<20? image[0]:image[1];
    }

    @Override
    public int getHeight(){
        return (int)getImage().getHeight();
    }

    @Override
    public int getWidth(){
        return (int)getImage().getWidth();
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void changeDirection(){
        isToLeft=!isToLeft;
    }

    public void move(){
        if(removeTimer>0){
           if(removeTimer<30){
               removeTimer++;
           } else{
              removeTimer=0;
              x=-50;
           }
        }else if(isToLeft){
            if(x>0) x-=moveLength;
            else isToLeft=false;
        }else{
            if(x+image[0].getWidth()<800) x+=moveLength;
            else isToLeft=true;
        }
        animateTimer++;
        if(animateTimer==40) animateTimer=0;
    }

    public void remove(){
        Image deadImage=new Image("images/enemy/enemyDead.png");
        image[0]=deadImage;
        image[1]=deadImage;
        floor=(int)(550-85-image[0].getHeight());
        y=floor;
        moveLength=0;
        removeTimer=1;
    }
}
