package controller;

import level.*;

/**
 * 以单例模式编写的关卡控制器，控制关卡的切换
 * @author Matrix53
 * @version 1.0
 */
public class LevelController {
    private final Level[] levels;
    private int levelPointer;

    /**
     * 开始某一个指定的关卡，
     * 若关卡开始成功则返回true，否则返回false
     * @param level 指定的关卡编号
     * @return 关卡是否成功开始
     */
    public boolean startLevel(int level){
        if(level<0 || level>=levels.length){
            return false;
        }else{
            levelPointer=level;
            levels[levelPointer].start();
            return true;
        }
    }

    /**
     * 开始下一个关卡，
     * 若成功开始则返回true，否则返回false
     * @return 是否成功开始下一个关卡
     */
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

    /**
     * 得到当前关卡控制器的实例对象
     * @return 关卡控制器的实例
     */
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
