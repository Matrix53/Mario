package entity;

import entity.box.Box;
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
    private boolean isWin;
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
        this.isWin=false;
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

    public void setY(int y){
        this.y=y;
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

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    private void updateLevel(){
        int oldHeight=getHeight();
        int oldFloor=floor;
        int oldY=y;
        for(int i=0;i<5;++i){
            image[i]=new Image("images/player/marioRight"+i+"Lvl"+level+".png");
            image[i+5]=new Image("images/player/marioLeft"+i+"Lvl"+level+".png");
        }
        floor=oldFloor+oldHeight-getHeight();
        y=oldY+oldHeight-getHeight();
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
            floor=550-85-getHeight();
        } else if(!input.contains(KeyCode.UP)
                && input.contains(KeyCode.DOWN)){
            jumpTimer=0;
            isToUp=false;
            isToDown=true;
//            floor=550-85-getHeight();
//            y=floor;
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
                    y=floor;
                }
            }
        }
    }

    public void hitEnemy(Enemy enemy){
        if(enemy.isDead()){
          return ;
        } else if(isToDown){
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

    public void hitBox(Box box){
        if(Math.abs(y+getHeight()- box.getY())<10){
            floor= box.getY()-getHeight();
            y=floor;
        }else if(Math.abs(x+getWidth()-box.getX())<=10){
            x-=moveLength;
        }else if(Math.abs(x-box.getX()- box.getWidth())<=10){
            x+=moveLength;
        }else{
            if(Math.abs(y-box.getY()- box.getHeight())<=10
                    && isToUp){
                box.open();
                isToUp=false;
                isToDown=true;
            }else if(x<=box.getX()){
                x-=moveLength;
            }else{
                x+=moveLength;
            }
        }
        if((Math.abs(x-box.getX()-box.getWidth())<=5
                || Math.abs(x+getWidth()- box.getX())<=5)
                && !isToUp
                && !isToDown){
            floor=550-85-getHeight();
            isToDown=true;
        }
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

    public void hitPipe(Pipe pipe){
        if(Math.abs(y+getHeight()- pipe.getY())<10
                && isToDown){
            floor= pipe.getY()-getHeight();
            y=floor;
        }else if(Math.abs(x+getWidth()-pipe.getX())<=10){
            x-=moveLength;
        }else if(Math.abs(x-pipe.getX()- pipe.getWidth())<=10){
            x+=moveLength;
        }
        if((Math.abs(x-pipe.getX()-pipe.getWidth())<=5
                || Math.abs(x+getWidth()- pipe.getX())<=5)
                && Math.abs(y+getHeight()-pipe.getY()-pipe.getHeight())>10
                && !isToUp
                && !isToDown){
            floor=550-85-getHeight();
            isToDown=true;
        }
    }

    public void hitWall(Wall wall){
        if(Math.abs(y+getHeight()- wall.getY())<10){
            floor= wall.getY()-getHeight();
            y=floor;
        }else if(Math.abs(x+getWidth()-wall.getX())<=10){
            x-=moveLength;
        }else if(Math.abs(x-wall.getX()- wall.getWidth())<=10){
            x+=moveLength;
        }else{
            if(Math.abs(y-wall.getY()- wall.getHeight())<=10
                    && isToUp){
                if(level>0){
                    wall.setX(-50);
                }
                isToUp=false;
                isToDown=true;
            }else if(x<=wall.getX()){
                x-=moveLength;
            }else{
                x+=moveLength;
            }
        }
        if((Math.abs(x-wall.getX()-wall.getWidth())<=5
                || Math.abs(x+getWidth()- wall.getX())<=5)
                && !isToUp
                && !isToDown){
            floor=550-85-getHeight();
            isToDown=true;
        }
    }
}
