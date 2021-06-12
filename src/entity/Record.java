package entity;

import controller.MainController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 游戏的计分板类，暂时没有用单例模式实现，而是作为游戏实体
 * @author Matrix53
 * @version 1.0
 */
public class Record {
    private int score;
    private int timer;
    private int level;
    private final GraphicsContext gc;
    private long animateTimer;

    /**
     * Record类的构造方法
     * @param graphicsContext graphicsContext对象
     */
    public Record(GraphicsContext graphicsContext){
        this.score=0;
        this.timer=0;
        this.level=1;
        this.gc=graphicsContext;
        gc.setFill(Color.WHITE);
        this.animateTimer=0;
    }

    /**
     * 得到当前分数
     * @return 当前的分数
     */
    public int getScore() {
        return score;
    }

    /**
     * 设置当前的分数
     * @param score 想要设置的分数
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 得到当前距离游戏开始的秒数
     * @return 游戏开始的秒数
     */
    public int getTimer() {
        return timer;
    }

    /**
     * 直接设置距游戏开始时的秒数
     * @param timer
     */
    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * 得到当前关卡的标识符
     * @return 当前关卡的标识符
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设置当前关卡标识符
     * @param level 关卡的标识符
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 得到Record控制的GraphicsContext对象
     * @return GraphicsContext对象
     */
    public GraphicsContext getGc() {
        return gc;
    }

    /**
     * 随着游戏流程更新时间
     * @param now 当前时间，其实可以不用
     */
    public void updateTime(long now){
        animateTimer++;
        if(animateTimer%50==0){
            timer++;
        }
    }

    /**
     * 给计分板加分
     * @param score 需要加的分
     */
    public void addScore(int score){
        this.score+=score;
    }

    /**
     * 读取历史数据
     */
    public void readRecord(){
        try{
            File file=new File("record.txt");
            if(file.exists()){
                Scanner scanner=new Scanner(file);
                if(scanner.hasNextInt()) score= scanner.nextInt();
                if(scanner.hasNextInt()) timer= scanner.nextInt();
                if(scanner.hasNextInt()) level= scanner.nextInt();
                scanner.close();
            }
        }catch(Exception e){
            MainController mainController=MainController.getInstance();
            mainController.endGame();
        }
    }

    /**
     * 向文件写入数据
     */
    public void saveRecord(){
        try{
            File file=new File("record.txt");
            if(file.exists()) file.delete();
            file.createNewFile();
            PrintWriter writer=new PrintWriter(file);
            writer.println(score);
            writer.println(timer);
            writer.println(level);
            writer.close();
        }catch (Exception e){
            MainController mainController=MainController.getInstance();
            mainController.endGame();
        }
    }

    /**
     * 得到当前关卡应该显示的文字
     * @return 应该显示的文字
     */
    private String getLevelText(){
        switch(level){
            case 0 ->{return "StartLevel";}
            case 1 ->{return "Level1";}
            case 2 ->{return "Level2";}
            default ->{return "EndLevel";}
        }
    }

    /**
     * 在游戏流程中绘制计分板
     */
    public void drawNormalScore(){
        Font font=new Font(30);
        gc.setFont(font);
        gc.fillText("SCORE:"+score,60,30);
        gc.fillText("TIME:"+timer,310,30);
        gc.fillText("LEVEL:"+getLevelText(),560,30);
    }

    /**
     * 在游戏开始界面绘制计分板
     */
    public void drawStartScore(){
        Font font=new Font(40);
        gc.setFont(font);
        gc.fillText("SCORE:"+score,120,300);
        gc.fillText("TIME:"+timer,120,360);
        gc.fillText("LEVEL:"+getLevelText(),120,420);
    }

    /**
     * 在游戏结束界面绘制计分板
     */
    public void drawEndScore(){
        Font font=new Font(50);
        gc.setFont(font);
        gc.fillText("SCORE:"+score,315,100);
    }

    /**
     * 重置分数和计时器
     */
    public void reset(){
        score=0;
        timer=0;
    }
}
