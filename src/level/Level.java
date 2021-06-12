package level;

import controller.EntityController;
import javafx.scene.canvas.Canvas;

/**
 * 所有关卡类的基类，具有关卡功能的类都必须要继承该类
 * @author Matrix53
 * @version 1.0
 */
abstract public class Level {
    protected final EntityController controller;
    protected Level(){
        this.controller=EntityController.getInstance();
    }
    public void addKeyEvent(){
        Canvas canvas= controller.getCanvas();
        canvas.setOnKeyReleased(null);
    }
    abstract public void addEntity();
    abstract public void setInitPos();
    public final void start(){
        controller.clearScreen();
        controller.getPlayer().reset();
        setInitPos();
        addEntity();
        addKeyEvent();
    }
}
