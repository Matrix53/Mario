package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Record {
    private int score;
    private int timer;
    private int level;
    private final GraphicsContext gc;

    public Record(GraphicsContext graphicsContext){
        this.score=0;
        this.timer=0;
        this.level=1;
        this.gc=graphicsContext;
        Font font=new Font(30);
        gc.setFont(font);
        gc.setFill(Color.WHITE);
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

    public void readRecord(){

    }

    public void saveRecord(){

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
        gc.fillText("SCORE:"+score,60,30);
        gc.fillText("TIME:"+timer,310,30);
        gc.fillText("LEVEL:"+getLevelText(),560,30);
    }

    public void drawStartScore(){

    }

    public void drawEndScore(){

    }
}
