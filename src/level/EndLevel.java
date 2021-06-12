package level;

import controller.LevelController;
import controller.MainController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 游戏的结束界面
 * @author Matrix53
 * @version 1.0
 */
public class EndLevel extends Level{
    /**
     * 结束界面无需添加实体，该方法的重写仅仅是为了保证语法正确
     */
    @Override
    public void addEntity(){

    }

    /**
     * 游戏的结束界面角色，旗子，标题的位置
     */
    @Override
    public void setInitPos(){

    }

    /**
     * 重写start方法，以此获得Player结束游戏时的状态
     */
    @Override
    public void start(){
        MainController mainController=MainController.getInstance();
        boolean isWin=controller.getPlayer().isWin();
        String path=isWin? "images/background/happyEnd.jpg":"images/background/badEnd.jpg";
        Image image=new Image(path);
        GraphicsContext gc= controller.getGc();
        mainController.stopGame();
        gc.drawImage(image,0,0);
        if(isWin){
            controller.addMouseEvent(mouseEvent -> {
                int x=(int)mouseEvent.getX();
                int y=(int)mouseEvent.getY();
                if(x>=291 && x<=512 && y>=357 && y<=442) mainController.endGame();
            });
        }else{
            controller.addMouseEvent(mouseEvent -> {
                int x=(int)mouseEvent.getX();
                int y=(int)mouseEvent.getY();
                if(x>=304 && x<=521 && y>=304 && y<=390){
                    LevelController levelController=LevelController.getInstance();
                    levelController.startLevel(1);
                    mainController.continueGame();
                }else if(x>=304 && x<=521 && y>=418 && y<=504){
                    mainController.endGame();
                }
            });
        }
    }
}
