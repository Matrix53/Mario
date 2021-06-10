package level;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

public class Controller {
    private final Scene scene;
    private final Canvas
    private Controller(){}

    public static Controller getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private final Controller instance;

        Singleton(){
            instance=new Controller();
        }

        private Controller getInstance(){
            return instance;
        }
    }


}
