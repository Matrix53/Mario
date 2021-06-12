import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 继承JavaFX的Application类作为游戏的主类， 游戏将从这个类开始运行
 * 
 * @author Matrix53
 * @version 1.0
 */
public class Main extends Application {
    /**
     * 重写父类的start方法，用来进行游戏的初始化
     * 
     * @param stage 游戏的唯一stage，所有游戏内容都将在这个stage上面
     */
    @Override
    public void start(Stage stage) {
        MainController controller = MainController.getInstance();
        Scene scene = controller.getScene();
        stage.setScene(scene);
        stage.setTitle("SuperMario");
        Image icon = new Image("images/icon/marioIcon.png");
        stage.getIcons().add(icon);
        controller.startGame();
        stage.show();
    }

    /**
     * 游戏主类的main方法，JavaFX程序的主入口
     * 
     * @param args 传递给JavaFX程序的参数列表
     */
    public static void main(String[] args) {
        launch(args);
    }
}
