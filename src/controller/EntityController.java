package controller;

import entity.*;
import entity.box.Box;
import entity.box.BoxCoin;
import entity.box.PowerUp;
import entity.box.PropType;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class EntityController {
    private final LinkedList<Enemy> enemies;
    private final LinkedList<Wall> walls;
    private final LinkedList<Pipe> pipes;
    private final LinkedList<Box> boxes;
    private final LinkedList<Coin> coins;

    private final Flag flag;
    private final Player player;
    private final Title title;
    private final Background background;

    private final Random random;
    private int maxAttempt;
    private final Canvas canvas;
    private final GraphicsContext gc;

    public void addKeyEvent(EventHandler handler){
        canvas.setOnKeyReleased(handler);
    }

    /**
     * 清除画面中所有实体，但不进行画面的重新绘制
     */
    public void clearScreen(){
        enemies.clear();
        walls.clear();
        pipes.clear();
        boxes.clear();
        coins.clear();
    }

    private boolean isPosLegal(Collidable entity){
        return entity.getX()+ entity.getWidth()<800
                && entity.getY()+ entity.getHeight()<550-85
                && !entity.judgeCollision(enemies)
                && !entity.judgeCollision(walls)
                && !entity.judgeCollision(pipes)
                && !entity.judgeCollision(coins)
                && !entity.judgeCollision(boxes)
                && !entity.judgeCollision(player);
    }

    /**
     * 在当前屏幕的随机位置增加一个敌人
     */
    public void addEnemy(){
        Enemy enemy=new Enemy();
        int attempt=0;
        while(!isPosLegal(enemy)
                && attempt<maxAttempt){
            attempt++;
            enemy.setX(random.nextInt(800- enemy.getWidth()));
            enemy.setY(random.nextInt(550-85- enemy.getHeight()));
        }
        if(attempt<maxAttempt) enemies.add(enemy);
    }

    /**
     * 在指定位置增加一个敌人
     *
     * @param x 敌人的x坐标
     * @param h 敌人的高度
     */
    public void addEnemy(int x, int h) {
        Enemy enemy = new Enemy();
        enemy.setX(x);
        enemy.setY(550 - 85 - enemy.getHeight() - h);
        enemies.add(enemy);
    }

    /**
     * 在当前屏幕的随机位置增加一个盒子
     */
    public void addBox(){
        Box box=new Box(800,0);
        int attempt=0;
        while(!isPosLegal(box)
                && attempt<maxAttempt){
            attempt++;
            box.setX(random.nextInt(800- box.getWidth()));
            box.setY(random.nextInt(550-85- box.getHeight()));
        }
        if(attempt<maxAttempt) boxes.add(box);
    }

    /**
     * 在指定位置增加一个盒子
     *
     * @param x 盒子的x坐标
     * @param h 盒子的高度
     */
    public void addBox(int x, int h) {
        Box box = new Box(x, 0);
        box.setY(550 - 85 - box.getHeight() - h);
        boxes.add(box);
    }

    /**
     * 增加一个固定类型的盒子
     *
     * @param x    盒子的x坐标
     * @param h    盒子的y坐标
     * @param type 盒子内道具类型
     */
    public void addBox(int x, int h, PropType type) {
        Box box = new Box(x, 0, type);
        box.setY(550 - 85 - box.getHeight() - h);
        boxes.add(box);
    }

    /**
     * 在当前屏幕的随机位置增加一个硬币
     */
    public void addCoin(){
        Coin coin=new Coin(800,0);
        int attempt=0;
        while(!isPosLegal(coin)
                && attempt<maxAttempt){
            attempt++;
            coin.setX(random.nextInt(800- coin.getWidth()));
            coin.setY(random.nextInt(550-85- coin.getHeight()));
        }
        if(attempt<maxAttempt) coins.add(coin);
    }

    /**
     * 在指定位置增加一个硬币
     *
     * @param x 硬币的x坐标
     * @param h 硬币的高度
     */
    public void addCoin(int x, int h) {
        Coin coin = new Coin(x, 0);
        coin.setY(550 - 85 - coin.getHeight() - h);
        coins.add(coin);
    }

    /**
     * 在当前屏幕的随机位置增加一个大管道
     */
    public void addBigPipe(){
        Image image=new Image("images/pipe/pipeBig.png");
        Pipe pipe=new Pipe(800,image);
        int attempt=0;
        while(!isPosLegal(pipe)
                && attempt<maxAttempt){
            attempt++;
            pipe.setX(random.nextInt(800- pipe.getWidth()));
        }
        if(attempt<maxAttempt) pipes.add(pipe);
    }

    /**
     * 增加一个大管道
     *
     * @param x 大管道的x坐标
     */
    public void addBigPipe(int x) {
        Image image = new Image("images/pipe/pipeBig.png");
        Pipe pipe = new Pipe(x, image);
        pipes.add(pipe);
    }

    /**
     * 在当前屏幕的随机位置增加一个小管道
     */
    public void addSmallPipe(){
        Image image=new Image("imagees/pipe/pipeSmall.png");
        Pipe pipe=new Pipe(800,image);
        int attempt=0;
        while(!isPosLegal(pipe)
                && attempt<maxAttempt){
            attempt++;
            pipe.setX(random.nextInt(800- pipe.getWidth()));
        }
        if(attempt<maxAttempt) pipes.add(pipe);
    }

    /**
     * 增加一个小管道
     *
     * @param x 小管道的x坐标
     */
    public void addSmallPipe(int x) {
        Image image = new Image("images/pipe/pipeSmall.png");
        Pipe pipe = new Pipe(x, image);
        pipes.add(pipe);
    }

    /**
     * 在当前屏幕的随机位置增加一个砖块
     */
    public void addWall(){
        Wall wall=new Wall(800,0);
        int attempt=0;
        while(!isPosLegal(wall)
                && attempt<maxAttempt){
            attempt++;
            wall.setX(random.nextInt(800- wall.getWidth()));
            wall.setY(random.nextInt(550-85- wall.getHeight()));
        }
        if(attempt<maxAttempt) walls.add(wall);
    }

    /**
     * 在指定位置增加一个砖块
     *
     * @param x 砖块的x坐标
     * @param h 砖块的高度
     */
    public void addWall(int x, int h) {
        Wall wall = new Wall(x, 0);
        wall.setY(550 - 85 - wall.getHeight() - h);
        walls.add(wall);
    }

    /**
     * 设置旗子的位置
     *
     * @param x 旗子的x坐标
     * @param h 旗子的高度
     */
    public void setFlagPos(int x, int h) {
        flag.setX(x);
        flag.setY(550 - 85 - flag.getHeight() - h);
    }

    /**
     * 设置玩家位置
     *
     * @param x 玩家的x坐标
     * @param h 玩家的y坐标
     */
    public void setPlayerPos(int x, int h) {
        player.setX(x);
        player.setY(550 - 85 - player.getHeight() - h);
    }

    /**
     * 设置标题的位置
     *
     * @param x 标题的x坐标
     */
    public void setTitlePos(int x){
        title.setX(x);
        title.setY(0);
    }

    public int getMaxAttempt() {
        return maxAttempt;
    }

    public void setMaxAttempt(int maxAttempt) {
        this.maxAttempt = maxAttempt;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Player getPlayer() {
        return player;
    }

    public void moveScreen(){
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
    }

    public void handleScreenEvent(HashSet<KeyCode> input){
        // 处理旗子的碰撞
        if (player.judgeCollision(flag)) {
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

    public void refreshScreen(){
        gc.drawImage(background.getImage(), background.getX(), background.getY());
        gc.drawImage(title.getImage(), title.getX(), title.getY());
        gc.drawImage(flag.getImage(),flag.getX(), flag.getY());
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
        gc.drawImage(player.getImage(), player.getX(), player.getY());
    }

    private EntityController(){
        enemies=new LinkedList<>();
        walls=new LinkedList<>();
        pipes=new LinkedList<>();
        boxes=new LinkedList<>();
        coins=new LinkedList<>();
        flag=new Flag(-200,0);
        player=new Player();
        title=new Title(0,0);
        background=new Background(0,0);
        random=new Random();
        maxAttempt=10;
        canvas=new Canvas(800,550);
        gc=canvas.getGraphicsContext2D();
    }

    public static EntityController getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private final EntityController instance;

        Singleton(){
            instance=new EntityController();
        }

        private EntityController getInstance(){
            return instance;
        }
    }
}
