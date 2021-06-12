package level;

import controller.LevelController;
import controller.MainController;
import entity.Record;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 游戏的初始界面
 * @author Matrix53
 * @version 1.0
 */
public class StartLevel extends Level{
    /**
     * 初始界面无需添加实体，该方法的重写仅仅是为了保证语法正确
     */
    @Override
    public void addEntity(){}

    /**
     * 设置游戏的初始界面角色，标题，旗子的位置
     */
    @Override
    public void setInitPos(){}

    /**
     * 设置游戏初始界面的鼠标点击事件
     */
    @Override
    public void addMouseEvent(){
        MainController mainController=MainController.getInstance();
        LevelController levelController=LevelController.getInstance();
        Record record=controller.getRecord();
        GraphicsContext gc=controller.getGc();
        Image image=new Image("images/background/start.jpg");
        mainController.stopGame();
        gc.drawImage(image,0,0);
        record.readRecord();
        record.drawStartScore();
        controller.getPlayer().setRecord(record);
        controller.addMouseEvent(mouseEvent -> {
            int x=(int)mouseEvent.getX();
            int y=(int)mouseEvent.getY();
            if(x>=552 && x<=769 && y>=281 && y<=366){
                mainController.continueGame();
                levelController.startNextLevel();
                record.reset();
            } else if(x>=552 && x<=769 && y>=408 && y<=491){
                mainController.endGame();
            }
        });
    }
}
