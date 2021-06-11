package level;

public class Level1 extends Level{
    public void start(){
        Controller controller=Controller.getInstance();

        int intro = 500;
        controller.addEnemy(intro, 0);
        controller.addWall(intro + 100, 120);
        controller.addBox(intro + 100 + 34, 120);
        controller.addWall(intro + 100 + 2 * 34, 120);
        controller.addBox(intro + 100 + 34, 240);


        int PipeAreaStart = 1000;
        controller.addSmallPipe(PipeAreaStart);
        controller.addEnemy(PipeAreaStart + 100, 0);
        controller.addSmallPipe( PipeAreaStart + 200);
        controller.addEnemy(PipeAreaStart + 300, 0);
        controller.addCoin(PipeAreaStart + 350, 200);
        controller.addBigPipe(PipeAreaStart + 400);
//        controller.addEnemy(PipeAreaStart + 450, 0);
        controller.addCoin(PipeAreaStart + 520, 290);
        controller.addEnemy(PipeAreaStart + 550, 0);
        controller.addBigPipe(PipeAreaStart + 630);
    }
}
