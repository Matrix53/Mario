package controller;

import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.HashSet;

/**
 * 使用单例模式编写的主控制类，控制其他所有Controller，
 * 享有所有的Media对象，Scene对象以及其它一些全局的对象，
 * 该类中定义了Scene的全局按键绑定，游戏的逐帧处理逻辑
 * @author Matrix53
 * @version 1.0
 */
public class MainController {
    private final EntityController entityController;
    private final LevelController levelController;
    private final Group root;
    private final Scene scene;
    private final HashSet<KeyCode> input;
    private final Media normal;
    private final Media die;
    private final Media jump;
    private final MediaPlayer normalPlayer;
    private final MediaPlayer diePlayer;
    private final MediaPlayer jumpPlayer;
    private final AnimationTimer timer;
    private final Player player;

    /**
     * 开始游戏，
     * 清空玩家之前的输入，并开始对输入进行处理
     */
    public void startGame(){
        input.clear();
        timer.start();
        levelController.startLevel(0);
    }

    /**
     * 结束游戏，关闭屏幕
     */
    public void endGame() {
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }

    /**
     * 暂停游戏，使timer停止处理游戏逻辑
     */
    public void stopGame() {
        timer.stop();
    }

    /**
     * 继续游戏，重新开始timer的计时
     */
    public void continueGame() {
        input.clear();
        timer.start();
    }

    private MainController() {
        // 初始化控制器属性
        entityController=EntityController.getInstance();
        levelController=LevelController.getInstance();
        root = new Group(entityController.getCanvas());
        scene = new Scene(root);
        input = new HashSet<>();
        normal = new Media(new File("assets/sounds/default.mp3").toURI().toString());
        die = new Media(new File("assets/sounds/die.mp3").toURI().toString());
        jump= new Media(new File("assets/sounds/jump.mp3").toURI().toString());
        normalPlayer = new MediaPlayer(normal);
        diePlayer = new MediaPlayer(die);
        jumpPlayer=new MediaPlayer(jump);
        player= entityController.getPlayer();
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
                    normalPlayer.setMute(true);
                    diePlayer.play();
                    diePlayer.setOnEndOfMedia(diePlayer::stop);
                    if(diePlayer.getStatus().equals(MediaPlayer.Status.STOPPED)){
                        levelController.startNextLevel();
                    };
                } else if (player.isDead()) {
                    normalPlayer.setMute(true);
                    diePlayer.play();
                    diePlayer.setOnEndOfMedia(diePlayer::stop);
                    if (diePlayer.getStatus().equals(MediaPlayer.Status.STOPPED)) {
                        levelController.startLastLevel();
                        return;
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
    public static MainController getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private final MainController instance;

        Singleton() {
            instance = new MainController();
        }

        private MainController getInstance() {
            return instance;
        }
    }
}
