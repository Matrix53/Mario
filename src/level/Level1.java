package level;

import entity.Enemy;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Level1 {
    public static void start(Stage stage) {
        Controller controller = Controller.getInstance();
        Scene scene = controller.getScene();
        stage.setScene(scene);
        stage.setTitle("SuperMario");
        Image icon = new Image("images/icon/marioIcon.png");

        stage.getIcons().add(icon);
        AnimationTimer timer = controller.getTimer();
        timer.start();

        controller.addSmallPipe(600);
        controller.addEnemy(700, 550 - 85);
        controller.addSmallPipe(800);
        stage.show();
    }
}
