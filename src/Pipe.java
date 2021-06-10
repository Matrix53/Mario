import javafx.scene.image.Image;

public class Pipe implements Collidable{
    private int x;
    private int y;
    private Image image;

    public Pipe(int x,Image image){
        this.image=image;
        this.x=x;
        this.y=(int)(550-85- image.getHeight());
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
    public double getWidth(){
        return image.getWidth();
    }

    @Override
    public double getHeight(){
        return image.getHeight();
    }
}
