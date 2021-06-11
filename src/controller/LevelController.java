package controller;

import level.*;

public class LevelController {
    private final Level[] levels;
    private int levelPointer;

    public boolean startLevel(int level){
        if(level<0 || level>=levels.length){
            return false;
        }else{
            levelPointer=level;
            levels[levelPointer].start();
            return true;
        }
    }

    public boolean startNextLevel(){
        if(levelPointer==levels.length-1){
            return false;
        }else{
            levelPointer++;
            levels[levelPointer].start();
            return true;
        }
    }

    private LevelController(){
        levels=new Level[5];
        levels[0]=new StartLevel();
        levels[1]=new Level1();
        levels[2]=new Level2();
        levels[3]=new Level3();
        levels[4]=new EndLevel();
        levelPointer=0;
    }

    public static LevelController getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private final LevelController instance;

        Singleton(){
            instance=new LevelController();
        }

        private LevelController getInstance() {
            return instance;
        }
    }
}
