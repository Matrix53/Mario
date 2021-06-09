import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface Collidable {
    public double getHeight();
    public double getWidth();
    public double getX();
    public double getY();
    default public boolean judgeCollision(Collidable object){
        ArrayList<Point2D.Double> points=new ArrayList<>();
        points.add(new Point2D.Double(getX(),getY()));
        points.add(new Point2D.Double(getX()+getWidth(),getY()));
        points.add(new Point2D.Double(getX(),getY()+getHeight()));
        points.add(new Point2D.Double(getX()+getWidth(),getY()+getHeight()));
        for(Point2D.Double point:points){
            if(point.x>=object.getX()
                    && point.x<=object.getX()+ object.getWidth()
                    && point.y>=object.getY()
                    && point.y<= object.getY()+ object.getHeight()){
                return true;
            }
        }
        return false;
    }
}
