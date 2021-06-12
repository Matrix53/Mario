package level;

import controller.EntityController;
import javafx.scene.canvas.Canvas;

/**
 * 所有关卡类的基类，具有关卡功能的类都必须要继承该类，
 * 用来进行关卡的初始化与添加关卡独有的事件监听
 * @author Matrix53
 * @version 1.0
 */
abstract public class Level {
    /**
     * 所有关卡都享有的实体控制器
     */
    protected final EntityController controller;

    /**
     * 所有关卡类的构造方法
     */
    protected Level(){
        this.controller=EntityController.getInstance();
    }

    /**
     * 添加关卡独有的按键事件，该方法不必须被重写
     */
    public void addKeyEvent(){
        Canvas canvas= controller.getCanvas();
        canvas.setOnKeyReleased(null);
    }

    /**
     * 添加关卡独有的鼠标事件，该方法不必须被重写
     */
    public void addMouseEvent(){
        Canvas canvas= controller.getCanvas();
        canvas.setOnKeyReleased(null);
    }

    /**
     * 添加该关卡中具有的游戏实体，该方法必须被重写
     */
    abstract public void addEntity();

    /**
     * 设置关卡中角色，旗子，标题的初始位置，该方法必须被重写
     */
    abstract public void setInitPos();

    /**
     * 开始当前关卡，除了关卡控制器外，
     * 其他任何位置的代码都不应该调用该方法
     */
    public void start(){
        controller.clearScreen();
        controller.getPlayer().reset();
        setInitPos();
        addEntity();
        addKeyEvent();
        addMouseEvent();
    }
}
