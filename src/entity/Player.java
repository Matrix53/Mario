package entity;

import entity.box.PowerUp;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.HashSet;

/**
 * 游戏的玩家类，使用经典的Mario图像素材进行渲染
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
    private int jumpHeight;
    private Image[] image;
    private int animateTimer;
    private int jumpTimer;
    private boolean isDead;
    private int level;

    public Player(){
        this.level=0;
        this.image=new Image[10];
        for(int i=0;i<5;++i){
            image[i]=new Image("images/player/marioRight"+i+"Lvl"+level+".png");
            image[i+5]=new Image("images/player/marioLeft"+i+"Lvl"+level+".png");
        }
        this.x=100;
        this.floor=(int)(550-85-image[0].getHeight());
        this.y=floor;
        this.animateTimer=0;
        this.jumpTimer=0;
        this.moveLength=5;
        this.jumpHeight=5;
        this.isDead=false;
        this.isToDown=false;
        this.isToUp=false;
        this.isToRight=false;
    }

    @Override
    public int getHeight() {
        return (int)image[0].getHeight();
    }

    @Override
    public int getWidth() {
        return (int)image[0].getWidth();
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x){
        this.x=x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setFloor(int floor){
        this.floor=floor;
    }

    public boolean isToDown(){
        return isToDown;
    }

    public int getMoveLength(){
        return moveLength;
    }

    public Image getImage(){
        return isToRight? image[animateTimer/10]:image[animateTimer/10+5];
    }

    public boolean isDead(){
        return isDead;
    }

    private void updateLevel(){
        for(int i=0;i<5;++i){
            image[i]=new Image("images/player/marioRight"+i+"Lvl"+level+".png");
            image[i+5]=new Image("images/player/marioLeft"+i+"Lvl"+level+".png");
        }
    }

    public void move(HashSet<KeyCode> input){
        animateTimer++;
        if(animateTimer==50
                || input.isEmpty()){
            animateTimer=0;
        }
        if(input.contains(KeyCode.LEFT)
                && !input.contains(KeyCode.RIGHT)
                && x>0){
            x-=moveLength;
            if(isToRight){
                isToRight=false;
                animateTimer=0;
            }
        } else if(!input.contains(KeyCode.LEFT)
                && input.contains(KeyCode.RIGHT)
                && x<800-getWidth()){
            x+=moveLength;
            if(!isToRight){
                isToRight=true;
                animateTimer=0;
            }
        }
        if(input.contains(KeyCode.UP)
                && !input.contains(KeyCode.DOWN)
                && !isToUp
                && !isToDown){
            isToUp=true;
        } else if(!input.contains(KeyCode.UP)
                && input.contains(KeyCode.DOWN)){
            jumpTimer=0;
            floor=550-85-getHeight();
            y=floor;
        }
    }

    public void jump(){
        for(int i=0;i<2;++i){
            if(isToUp){
                y-=jumpHeight;
                jumpTimer++;
                if(jumpTimer==62){
                    isToUp=false;
                    isToDown=true;
                }
            }else if(isToDown){
                jumpTimer=0;
                y+=jumpHeight;
                if(y>=floor){
                    isToDown=false;
                }
            }
        }
    }

    public void hitEnemy(Enemy enemy){
        if(isToDown){
            enemy.remove();
            isToDown=false;
            isToUp=true;
        }else{
            if(level==0){
                image[0]=new Image("images/player/marioDead.png");
                moveLength=0;
                floor=550;
                isToUp=true;
                isDead=true;
                animateTimer=0;
            }else{
                level--;
                enemy.changeDirection();
                updateLevel();
            }
        }
    }

    public void hitBox(){

    }

    public void hitPowerUp(PowerUp powerUp){
        powerUp.vanish();
        if(level<2){
            level++;
            updateLevel();
            floor=550-85-getHeight();
            if(y>floor) y=floor;
        }
    }

    public void hitCoin(Coin coin){
        coin.vanish();
    }

    public void hitPipe(){

    }

    public void hitWall(){

    }
}
