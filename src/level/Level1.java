package level;

import entity.Enemy;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Level1 {
    public static void start(){
        Controller controller=Controller.getInstance();
        controller.addSmallPipe(500);
    }
}
