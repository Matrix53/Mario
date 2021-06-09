import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage){
        Canvas canvas =new Canvas(800,500);
        Group root=new Group(canvas);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SuperMario");
        Image icon=new Image("images/icon/marioIcon.png");
        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}
