import javafx.scene.image.Image;

/**
 * 不可摧毁的砖块类，具有碰撞体积
 * @author Matrix53
 * @version 1.0
 */
public class Wall implements Collidable{
    private int x;
    private int y;
    private Image image;

    public Wall(int x,int y){
        this.x=x;
        this.y=y;
        this.image=new Image("images/wall/wall.png");
    }

    @Override
    public double getX(){
        return x;
    }

    public void setX(int x){
        this.x=x;
    }

    @Override
    public double getY(){
        return y;
    }

    @Override
    public double getHeight(){
        return image.getHeight();
    }

    @Override
    public double getWidth(){
        return image.getWidth();
    }

    public Image getImage(){
        return image;
    }
}
