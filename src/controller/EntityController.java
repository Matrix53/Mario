package controller;

import entity.*;
import entity.box.Box;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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

    private final Random random;
    private int maxAttempt;
    private final Canvas canvas;
    private final GraphicsContext gc;

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
                && entity.judgeCollision(enemies)
                && entity.judgeCollision(walls)
                && entity.judgeCollision(pipes)
                && entity.judgeCollision(coins)
                && entity.judgeCollision(boxes)
                && entity.judgeCollision(player);
    }

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

    public void addEnemy(int x, int h) {
        Enemy enemy = new Enemy();
        enemy.setX(x);
        enemy.setY(550 - 85 - enemy.getHeight() - h);
        enemies.add(enemy);
    }

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

    public void addBox(int x, int h) {
        Box box = new Box(x, 0);
        box.setY(550 - 85 - box.getHeight() - h);
        boxes.add(box);
    }

    public void addCoin(){
        Coin coin=new Coin();
        int attempt=0;
        while(!isPosLegal(coin)
                && attempt<maxAttempt){
            attempt++;
            coin.setX(random.nextInt(800- coin.getWidth()));
            coin.setY(random.nextInt(550-85- coin.getHeight()));
        }
        if(attempt<maxAttempt) coins.add(coin);
    }

    public void addCoin(int x, int h) {
        Coin coin = new Coin(x, 0);
        coin.setY(550 - 85 - coin.getHeight() - h);
        coins.add(coin);
    }


    public void addBigPipe(){
        Image image=new Image("images/pipe/pipeBig.png");
        Pipe pipe=new Pipe(800,image);
        int attempt=0;
        while(!isPosLegal(pipe)
                && attempt<maxAttempt){
            attempt++;
            pipe.setX(random.nextInt(800- pipe.getWidth()));
            pipe.setY(random.nextInt(550-85- pipe.getHeight()));
        }
        if(attempt<maxAttempt) pipes.add(pipe);
    }

    public void addBigPipe(int x) {
        Image image = new Image("images/pipe/pipeBig.png");
        Pipe pipe = new Pipe(x, image);
        pipes.add(pipe);
    }

    public void addSmallPipe(){
        Image image=new Image("images/pipe/pipeSmall.png");
        Pipe pipe=new Pipe(800,image);
        int attempt=0;
        while(!isPosLegal(pipe)
                && attempt<maxAttempt){
            attempt++;
            pipe.setX(random.nextInt(800- pipe.getWidth()));
            pipe.setY(random.nextInt(550-85- pipe.getHeight()));
        }
        if(attempt<maxAttempt) pipes.add(pipe);
    }

    public void addSmallPipe(int x) {
        Image image = new Image("images/pipe/pipeSmall.png");
        Pipe pipe = new Pipe(x, image);
        pipes.add(pipe);
    }

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

    public void redraw(){

    }

    private EntityController(){
        enemies=new LinkedList<>();
        walls=new LinkedList<>();
        pipes=new LinkedList<>();
        boxes=new LinkedList<>();
        coins=new LinkedList<>();
        flag=new Flag(-50,0);
        player=new Player();
        title=new Title(0,0);
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
