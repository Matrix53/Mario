package level;

import entity.Enemy;
import entity.Pipe;
import entity.Player;
import entity.Wall;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
    private final Canvas canvas=new Canvas(800,550);
    private final Group root=new Group(canvas);
    private final Scene scene=new Scene(root);
    private final HashSet<KeyCode> input=new HashSet<>();
    private final LinkedList<Enemy> enemies=new LinkedList<>();
    private final LinkedList<Wall> walls=new LinkedList<>();
    private final LinkedList<Pipe> pipes=new LinkedList<>();
    private final Player player=new Player();
    private final Random random=new Random();
    private final GraphicsContext gc= canvas.getGraphicsContext2D();
    private final Image background=new Image("images/background/background.png");
    private final Media normal=new Media("sounds/default.mp3");
    private final Media die=new Media("sounds/die.mp3");
    private final MediaPlayer normalPlayer=new MediaPlayer(normal);
    private final MediaPlayer diePlayer=new MediaPlayer(die);
    private final AnimationTimer timer;

    private Controller(){
        // 定义按键绑定，记录用户的输入，忽略短时间内的重复输入
        scene.setOnKeyPressed(event ->{
            KeyCode keyCode=event.getCode();
            input.add(keyCode);
        });
        // 定义按键绑定，处理用户的输入
        scene.setOnKeyReleased(event ->{
            KeyCode keyCode=event.getCode();
            input.remove(keyCode);
            switch(keyCode){
                // 添加敌人
                case E -> {
                    Enemy enemy=new Enemy();
                    enemies.add(enemy);
                }
                // 添加墙壁
                case W -> {
                    Wall wall=new Wall(random.nextInt(800),250);
                    while(wall.judgeCollision(walls)){
                        wall.setX(random.nextInt(800));
                    }
                    walls.add(wall);
                }
                // 添加管道
                case O,P ->{
                    String pipeName=(keyCode == KeyCode.O)? "pipeSmall.png":"pipeBig.png";
                    Image pipeImage=new Image("images/pipe/"+pipeName);
                    Pipe pipe=new Pipe(random.nextInt(760),pipeImage);
                    while(pipe.judgeCollision(pipes)
                            || pipe.judgeCollision(enemies)){
                        pipe.setX(random.nextInt(760));
                    }
                    pipes.add(pipe);
                }
            }
        });
        // 定义动画的逐帧处理逻辑
        this.timer=new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }

    public Scene getScene(){
        return scene;
    }

    public AnimationTimer getTimer(){
        return timer;
    }

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
