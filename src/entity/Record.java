package entity;

import controller.MainController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Record {
    private int score;
    private int timer;
    private int level;
    private final GraphicsContext gc;
    private long animateTimer;

    public Record(GraphicsContext graphicsContext){
        this.score=0;
        this.timer=0;
        this.level=1;
        this.gc=graphicsContext;
        gc.setFill(Color.WHITE);
        this.animateTimer=0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void updateTime(long now){
        animateTimer++;
        if(animateTimer%50==0){
            timer++;
        }
    }

    public void addScore(int score){
        this.score+=score;
    }

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

    private String getLevelText(){
        switch(level){
            case 0 ->{return "StartLevel";}
            case 1 ->{return "Level1";}
            case 2 ->{return "Level2";}
            default ->{return "EndLevel";}
        }
    }

    public void drawNormalScore(){
        Font font=new Font(30);
        gc.setFont(font);
        gc.fillText("SCORE:"+score,60,30);
        gc.fillText("TIME:"+timer,310,30);
        gc.fillText("LEVEL:"+getLevelText(),560,30);
    }

    public void drawStartScore(){
        Font font=new Font(40);
        gc.setFont(font);
        gc.fillText("SCORE:"+score,120,300);
        gc.fillText("TIME:"+timer,120,360);
        gc.fillText("LEVEL:"+getLevelText(),120,420);
    }

    public void drawEndScore(){
        Font font=new Font(50);
        gc.setFont(font);
        gc.fillText("SCORE:"+score,315,100);
    }

    public void reset(){
        score=0;
        timer=0;
    }
}
