package level;

import controller.EntityController;
import controller.LevelController;
import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.HashSet;

/**
 * 控制类
 */
public class Controller {
    private final EntityController entityController=EntityController.getInstance();
    private final LevelController levelController=LevelController.getInstance();
    private final Group root = new Group(entityController.getCanvas());
    private final Scene scene = new Scene(root);
    private final HashSet<KeyCode> input = new HashSet<>();
    private final Image background = new Image("images/background/background.png");
    private final Media normal = new Media(new File("assets/sounds/default.mp3").toURI().toString());
    private final Media die = new Media(new File("assets/sounds/die.mp3").toURI().toString());
    private final MediaPlayer normalPlayer = new MediaPlayer(normal);
    private final MediaPlayer diePlayer = new MediaPlayer(die);
    private final AnimationTimer timer;
    private final Player player= entityController.getPlayer();

    /**
     * 游戏结束
     */
    public void endGame() {
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }

    /**
     * 暂停游戏
     */
    public void stopGame() {
        timer.stop();
    }

    /**
     * 继续游戏
     */
    public void continueGame() {
        timer.start();
    }

    private Controller() {
        // 定义按键绑定，记录用户的输入，忽略短时间内的重复输入
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            input.add(keyCode);
        });
        // 定义按键绑定，处理用户的输入
        scene.setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            input.remove(keyCode);
            switch (keyCode) {
                // 添加敌人
                case E -> entityController.addEnemy();
                // 添加盒子
                case B -> entityController.addBox();
                // 添加墙壁
                case W -> entityController.addWall();
                // 添加小型管道
                case O -> entityController.addSmallPipe();
                // 添加大型管道
                case P -> entityController.addBigPipe();
                // 添加金币
                case C -> entityController.addCoin();
                // 清屏
                case Z -> entityController.clearScreen();
                // 播放跳跃音乐
                case UP -> {
                    if(!player.isToDown()){
                        Media jump = new Media(new File("assets/sounds/jump.mp3").toURI().toString());
                        MediaPlayer jumpPlayer = new MediaPlayer(jump);
                        jumpPlayer.setStartTime(Duration.millis(7));
                        jumpPlayer.play();
                    }
                }
            }
        });
        // 定义动画的逐帧处理逻辑
        this.timer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                // 背景音乐
                normalPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                normalPlayer.play();
                // 移动屏幕
                entityController.moveScreen();
                // 处理核心碰撞和动画
                if (player.isWin()) {
                    ;
                } else if (player.isDead()) {
                    normalPlayer.setMute(true);
                    diePlayer.play();
                    diePlayer.setOnEndOfMedia(diePlayer::stop);
                    if (diePlayer.getStatus().equals(MediaPlayer.Status.STOPPED)) {
                        Platform.exit();
                    }
                } else {
                    entityController.handleScreenEvent(input);
                }
                // 处理跳跃动画
                player.jump();
                // 重新绘制屏幕
                entityController.refreshScreen();
            }
        };
    }

    /**
     * 得到场景
     *
     * @return 场景
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * 得到计时器
     *
     * @return 计时器
     */
    public AnimationTimer getTimer() {
        return timer;
    }

    /**
     * 得到控制类
     *
     * @return 控制类
     */
    public static Controller getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private final Controller instance;

        Singleton() {
            instance = new Controller();
        }

        private Controller getInstance() {
            return instance;
        }
    }
}
