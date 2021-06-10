import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * 继承JavaFX的Application类作为游戏的主类，
 * 游戏将从这个类开始运行
 * @author Matrix53
 * @version 1.0
 */
public class Main extends Application{
    /**
     * 重写父类的start方法，用来进行游戏的初始化
     * @param stage 游戏的唯一stage，所有游戏内容都将在这个stage上面
     */
    @Override
    public void start(Stage stage){
        // 对游戏进行窗口相关的的初始化
        Canvas canvas =new Canvas(800,500);
        Group root=new Group(canvas);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SuperMario");
        Image icon=new Image("images/icon/marioIcon.png");
        stage.getIcons().add(icon);

        // 定义一些全局的对象集合
        HashSet<KeyCode> input=new HashSet<>();
        LinkedList<Enemy> enemies=new LinkedList<>();
        LinkedList<Wall> walls=new LinkedList<>();

        // 记录用户的输入，忽略短时间内的重复输入
        scene.setOnKeyPressed(event ->{
            KeyCode keyCode=event.getCode();
            input.add(keyCode);
        });

        // 处理用户的输入
        scene.setOnKeyReleased(event ->{
            KeyCode keyCode=event.getCode();
            input.remove(keyCode);

            switch(keyCode){
                case E ->{
                    Enemy enemy=new Enemy();
                    enemies.add(enemy);
                }
            }
        });

        stage.show();
    }

    /**
     * 游戏主类的main方法，JavaFX程序的主入口
     * @param args 传递给JavaFX程序的参数列表
     */
    public static void main(String[] args){
        launch(args);
    }
}
