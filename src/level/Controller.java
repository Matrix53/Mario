package level;

import entity.*;
import entity.box.Box;
import entity.box.PowerUp;
import entity.box.BoxCoin;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
    private final Canvas canvas = new Canvas(800, 550);
    private final Group root = new Group(canvas);
    private final Scene scene = new Scene(root);
    private final HashSet<KeyCode> input = new HashSet<>();
    private final LinkedList<Enemy> enemies = new LinkedList<>();
    private final LinkedList<Wall> walls = new LinkedList<>();
    private final LinkedList<Pipe> pipes = new LinkedList<>();
    private final LinkedList<Box> boxes = new LinkedList<>();
    private final LinkedList<Coin> coins = new LinkedList<>();
    private final Flag flag = new Flag(-50, 0);
    private final Player player = new Player();
    private final Random random = new Random();
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private final Image background = new Image("images/background/background.png");
    private final Title title = new Title(0, 0);
    private final Media normal = new Media(new File("assets/sounds/default.mp3").toURI().toString());
    private final Media die = new Media(new File("assets/sounds/die.mp3").toURI().toString());
    private final MediaPlayer normalPlayer = new MediaPlayer(normal);
    private final MediaPlayer diePlayer = new MediaPlayer(die);
    private final AnimationTimer timer;

    public void clearScreen() {
        boxes.clear();
        enemies.clear();
        walls.clear();
        pipes.clear();
        coins.clear();
    }

    public void endGame(){
        Stage stage=(Stage)scene.getWindow();
        stage.close();
    }

    public void stopGame(){
        timer.stop();
    }

    public void continueGame(){
        timer.start();
    }

    public void addEnemy(int x, int h) {
        Enemy enemy = new Enemy();
        enemy.setX(x);
        enemy.setY(550 - 85 - enemy.getHeight() - h);
        enemies.add(enemy);
    }

    public void addBox(int x, int h) {
        Box box = new Box(x, 0);
        box.setY(550 - 85 - box.getHeight() - h);
        boxes.add(box);
    }

    public void addCoin(int x, int h) {
        Coin coin = new Coin(x, 0);
        coin.setY(550 - 85 - coin.getHeight() - h);
        coins.add(coin);
    }

    public void addBigPipe(int x) {
        Image image = new Image("images/pipe/pipeBig.png");
        Pipe pipe = new Pipe(x, image);
        pipes.add(pipe);
    }

    public void addSmallPipe(int x) {
        Image image = new Image("images/pipe/pipeSmall.png");
        Pipe pipe = new Pipe(x, image);
        pipes.add(pipe);
    }

    public void addWall(int x, int h) {
        Wall wall = new Wall(x, 0);
        wall.setY(550 - 85 - wall.getHeight() - h);
        walls.add(wall);
    }

    public void setFlagPos(int x, int h) {
        flag.setX(x);
        flag.setY(550 - 85 - flag.getHeight() - h);
    }

    public void setPlayerPos(int x, int h) {
        player.setX(x);
        player.setY(550 - 85 - player.getHeight() - h);
    }

    public void setTitlePos(int x){
        title.setX(x);
        title.setY(0);
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
                case E -> {
                    Enemy enemy = new Enemy();
                    enemies.add(enemy);
                }
                // 添加盒子
                case B -> {
                    Box box = new Box(random.nextInt(800), 250);
                    while (box.judgeCollision(boxes)
                            || box.judgeCollision(walls)
                            || box.judgeCollision(coins)) {
                        box.setX(random.nextInt(800));
                    }
                    boxes.add(box);
                }
                // 添加墙壁
                case W -> {
                    Wall wall = new Wall(random.nextInt(800), 250);
                    while (wall.judgeCollision(walls)
                            || wall.judgeCollision(boxes)
                            || wall.judgeCollision(coins)) {
                        wall.setX(random.nextInt(800));
                    }
                    walls.add(wall);
                }
                // 添加管道
                case O, P -> {
                    String pipeName = (keyCode == KeyCode.O) ? "pipeSmall.png" : "pipeBig.png";
                    Image pipeImage = new Image("images/pipe/" + pipeName);
                    Pipe pipe = new Pipe(random.nextInt(760), pipeImage);
                    while (pipe.judgeCollision(pipes)
                            || pipe.judgeCollision(enemies)
                            || pipe.judgeCollision(coins)) {
                        pipe.setX(random.nextInt(760));
                    }
                    pipes.add(pipe);
                }
                // 添加金币
                case C -> {
                    Coin coin = new Coin(random.nextInt(800), random.nextInt(550));
                    while (coin.judgeCollision(boxes)
                            || coin.judgeCollision(walls)
                            || coin.judgeCollision(coins)
                            || coin.judgeCollision(pipes)) {
                        coin.setX(800 - coin.getWidth());
                        coin.setY(550 - 85 - coin.getHeight());
                    }
                    coins.add(coin);
                }
                // 添加旗子
                case F ->{
                    flag.setY(550-85-flag.getHeight());
                    flag.setX(random.nextInt(800-flag.getWidth()));
                    while(flag.judgeCollision(enemies)
                            || flag.judgeCollision(boxes)
                            || flag.judgeCollision(walls)
                            || flag.judgeCollision(pipes)
                            || flag.judgeCollision(coins)
                            || flag.judgeCollision(player)){
                        flag.setX(random.nextInt(800-flag.getWidth()));
                    }
                }
                // 清屏
                case Z -> {
                    boxes.clear();
                    enemies.clear();
                    walls.clear();
                    pipes.clear();
                    coins.clear();
                    player.setFloor(550 - 85 - player.getHeight());
                    player.jump();
                }
                // 播放跳跃音乐
                case UP -> {
                    if (!player.isToDown()) {
                        Media jump = new Media(new File("assets/sounds/jump.mp3").toURI().toString());
                        MediaPlayer jumpPlayer = new MediaPlayer(jump);
                        jumpPlayer.setStartTime(Duration.millis(7));
                        jumpPlayer.play();
                    }
                }
            }
        });
        // 定义动画的逐帧处理逻辑
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // 背景音乐
                normalPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                normalPlayer.play();
                // 移动屏幕
                if (player.getX() >= 500) {
                    int moveLength = player.getMoveLength();
                    player.setX(500 - moveLength);
                    coins.forEach(coin -> coin.setX(coin.getX() - moveLength));
                    walls.forEach(wall -> wall.setX(wall.getX() - moveLength));
                    pipes.forEach(pipe -> pipe.setX(pipe.getX() - moveLength));
                    enemies.forEach(enemy -> enemy.setX(enemy.getX() - moveLength));
                    boxes.forEach(box -> box.setX(box.getX() - moveLength));
                    title.setX(title.getX() - moveLength);
                    flag.setX(flag.getX()-moveLength);
                }
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
                    // 处理旗子的碰撞
                    if(player.judgeCollision(flag)){
                        player.hitFlag();
                    }
                    // 处理敌人的碰撞
                    for (int i = 0; i < enemies.size(); ++i) {
                        Enemy enemy = enemies.get(i);
                        enemy.move();
                        if (player.judgeCollision(enemy)) {
                            player.hitEnemy(enemy);
                        }
                        for (int j = i + 1; j < enemies.size(); ++j) {
                            if (enemy.judgeCollision(enemies.get(j))) {
                                enemy.changeDirection();
                                enemies.get(j).changeDirection();
                            }
                        }
                        pipes.forEach(pipe -> {
                            if (enemy.judgeCollision(pipe)) {
                                enemy.changeDirection();
                            }
                        });
                    }
                    // 处理管道的碰撞
                    pipes.forEach(pipe -> {
                        if (player.judgeCollision(pipe)) {
                            player.hitPipe(pipe);
                        }
                    });
                    // 处理盒子的碰撞
                    boxes.forEach(box -> {
                        box.animate();
                        if (player.judgeCollision(box)) {
                            player.hitBox(box);
                        }
                        if (box.getCoin() != null) {
                            box.getCoin().animate();
                        } else if (box.getPowerUp() != null) {
                            PowerUp powerUp = box.getPowerUp();
                            if (!powerUp.judgeCollision(box)
                                    && !powerUp.getFalling()
                                    && box.getY() > powerUp.getY()) {
                                powerUp.setFalling(true);
                            }
                            powerUp.move();
                            pipes.forEach(pipe -> {
                                if (powerUp.judgeCollision(pipe)) {
                                    powerUp.changeDirection();
                                }
                            });
                            if (player.judgeCollision(powerUp)) {
                                player.hitPowerUp(powerUp);
                            }
                        }
                    });
                    // 处理墙壁的碰撞
                    walls.forEach(wall -> {
                        if (player.judgeCollision(wall)) {
                            player.hitWall(wall);
                        }
                    });
                    // 处理硬币的碰撞
                    coins.forEach(coin -> {
                        if (player.judgeCollision(coin)) {
                            coin.vanish();
                        }
                        coin.animate();
                    });
                    // 移除失效的实体
                    walls.removeIf(wall -> wall.getX() < 0);
                    coins.removeIf(coin -> coin.getX() < 0);
                    enemies.removeIf(enemy -> enemy.getX() < 0);
                    for (Box box : boxes) {
                        if (box.getCoin() != null
                                && box.getCoin().getX() < 0) {
                            box.setCoin(null);
                        } else if (box.getPowerUp() != null
                                && box.getPowerUp().getX() < 0) {
                            box.setPowerUp(null);
                        }
                    }
                    // 处理用户输入
                    player.move(input);
                }
                // 处理跳跃动画
                player.jump();
                // 重新绘制屏幕
                gc.drawImage(background, 0, 0);
                gc.drawImage(title.getImage(), title.getX(), title.getY());
                coins.forEach(coin -> gc.drawImage(coin.getImage(), coin.getX(), coin.getY()));
                pipes.forEach(pipe -> gc.drawImage(pipe.getImage(), pipe.getX(), pipe.getY()));
                walls.forEach(wall -> gc.drawImage(wall.getImage(), wall.getX(), wall.getY()));
                boxes.forEach(box -> {
                    gc.drawImage(box.getImage(), box.getX(), box.getY());
                    if (box.getCoin() != null) {
                        BoxCoin coin = box.getCoin();
                        gc.drawImage(coin.getImage(), coin.getX(), coin.getY());
                    } else if (box.getPowerUp() != null) {
                        PowerUp powerUp = box.getPowerUp();
                        gc.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY());
                    }
                });
                enemies.forEach(enemy -> gc.drawImage(enemy.getImage(), enemy.getX(), enemy.getY()));
                gc.drawImage(flag.getImage(),flag.getX(), flag.getY());
                gc.drawImage(player.getImage(), player.getX(), player.getY());
            }
        };
    }

    public Scene getScene() {
        return scene;
    }

    public AnimationTimer getTimer() {
        return timer;
    }

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
