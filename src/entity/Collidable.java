package entity;

import java.util.Collection;

public interface Collidable {
    int getHeight();

    int getWidth();

    int getX();

    int getY();

    default boolean judgeCollision(Collidable object) {
        return !(getX() + getWidth() < object.getX() ||
                getX() > object.getX() + object.getWidth() ||
                getY() + getHeight() < object.getY() ||
                getY() > object.getY() + object.getHeight());
    }

    default boolean judgeCollision(Collection<? extends Collidable> objects) {
        return objects.stream().anyMatch(this::judgeCollision);
    }
}
