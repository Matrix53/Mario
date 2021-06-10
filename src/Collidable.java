public interface Collidable {
    public double getHeight();
    public double getWidth();
    public double getX();
    public double getY();
    default public boolean judgeCollision(Collidable object){
        return !(getX()+getWidth()<object.getX() ||
                getX()> object.getX()+ object.getWidth() ||
                getY()+getHeight()<object.getY() ||
                getY()> object.getY()+object.getHeight());
    }
}
