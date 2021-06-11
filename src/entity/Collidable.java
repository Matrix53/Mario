package entity;

import javafx.scene.image.Image;

import java.util.Collection;

/**
 * 可碰撞的实体的接口
 */
public interface Collidable {
    /**
     * 得到实体的图片
     *
     * @return 实体的图片
     */
    Image getImage();

    /**
     * 得到实体的高度
     *
     * @return 实体的高度
     */
    int getHeight();

    /**
     * 得到实体的宽度
     *
     * @return 实体的宽度
     */
    int getWidth();

    /**
     * 得到实体的x坐标
     *
     * @return 实体的x坐标
     */
    int getX();

    /**
     * 得到实体的y坐标
     *
     * @return 实体的y坐标
     */
    int getY();

    /**
     * 判断是否有碰撞
     *
     * @param object 可能碰撞的实体的对象
     * @return 是否有碰撞
     */
    default boolean judgeCollision(Collidable object) {
        return !(getX() + getWidth() < object.getX() ||
                getX() > object.getX() + object.getWidth() ||
                getY() + getHeight() < object.getY() ||
                getY() > object.getY() + object.getHeight());
    }

    /**
     * 判断实体是否与一列元素中任意一个有碰撞
     *
     * @param objects 实体列表
     * @return 是否有碰撞
     */
    default boolean judgeCollision(Collection<? extends Collidable> objects) {
        return objects.stream().anyMatch(this::judgeCollision);
    }
}
