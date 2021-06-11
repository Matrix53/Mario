package level;

import controller.EntityController;

public class Level2 extends Level{
    private final EntityController controller=EntityController.getInstance();

    private void addStairs(int x, int n, int direction){
        int blocks = n;
        if(direction == 1){
            for(int i = 0;i<n;i++){
                int start = i*34+x;
                for(int k = 0;k<blocks;k++){
                    controller.addWall(start, i*34);
                    start += 34;
                }
                blocks --;
            }
        }else{
            for(int i = 0;i<n;i++){
                int start = x;
                for(int k = 0;k<blocks;k++){
                    controller.addWall(start, i*34);
                    start += 34;
                }
                blocks --;
            }
        }
    }

    private void addVerticalWall(int x, int h, int n){
        int height = h;
        for(int i = 0;i<n;i++){
            controller.addWall(x, height);
            height += 34;
        }
    }

    private void addHorizontalWall(int x, int h, int n){
        int pos = x;
        for(int i = 0;i<n;i++){
            controller.addWall(pos, h);
            pos += 34;
        }
    }

    @Override
    public void start(){
        //page1
        controller.addBox(300, 100);
        controller.addSmallPipe(500);
        controller.addBigPipe(700);
        controller.addEnemy(600, 100);
        controller.addCoin(700+20, 180);
        //page2
        controller.addWall(800, 200);
        controller.addBox(800+34, 200);
        controller.addWall(800+34*2, 200);
        controller.addBox(800+34*2, 300);
        controller.addBox(800+34*3, 200);
        controller.addWall(800+34*4, 200);
        controller.addSmallPipe(1000);
        controller.addBigPipe(1200);
        controller.addEnemy(1100, 100);
        controller.addWall(1300, 200);
        controller.addBox(1300+34, 200);
        controller.addWall(1300+34*2, 200);
        controller.addWall(1300+34*3, 300);
        controller.addWall(1300+34*4, 300);
        controller.addWall(1300+34*5, 300);
        controller.addWall(1300+34*6, 300);
        controller.addWall(1300+34*7, 300);
        controller.addEnemy(1300+34*7,0);
        controller.addEnemy(1300+34*6,301);
        //page3
        controller.addBox(1700, 150);
        controller.addBox(1800, 150);
        controller.addBox(1800, 250);
        controller.addCoin(1800, 300);
        controller.addBox(1900, 150);
        controller.addWall(2100, 150);
        controller.addWall(2200, 300);
        controller.addWall(2200+34, 300);
        controller.addWall(2200+34*2, 300);
        //page 4
        addStairs(2400, 4, 1);
        addStairs(2700, 4, 0);
        controller.addCoin(3100+40,0);
        controller.addCoin(3100+60,0);
        controller.addCoin(3100+80,0);
        controller.addCoin(3100+40,34);
        controller.addCoin(3100+60,34);
        controller.addCoin(3100+80,34);
        controller.addCoin(3100+40,34*2);
        controller.addCoin(3100+60,34*2);
        controller.addCoin(3100+80,34*2);
        controller.addCoin(3100+40,34*3);
        controller.addCoin(3100+60,34*3);
        controller.addCoin(3100+80,34*3);
        //page 5
        addVerticalWall(3200, 0, 5);
        addVerticalWall(3300, 0, 9);

        addVerticalWall(3400, 34*5, 4);
        addVerticalWall(3400+34*3, 0, 9);
        addHorizontalWall(3400, 34*5, 3);
        addHorizontalWall(3400, 34*8, 3);

        addHorizontalWall(3400+34*3+100, 0, 4);
        addHorizontalWall(3400+34*3+100, 34*4, 4);
        addHorizontalWall(3400+34*3+100, 34*8, 4);
        addVerticalWall(3400+34*3+100, 0, 4);
        addVerticalWall(3400+34*3+100+34*3, 34*4, 4);

        addVerticalWall(3400+34*3+100+34*3+100, 0, 9);

        //end
        controller.setFlagPos(3400+34*3+100+34*3+100, 34*9);
    }
}
