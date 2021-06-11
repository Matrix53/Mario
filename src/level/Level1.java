package level;

public class Level1 {
    private static Controller controller = Controller.getInstance();

    private static int intro(int startPoint) {
        controller.addEnemy(startPoint, 0);
        controller.addWall(startPoint + 100, 120);
        controller.addBox(startPoint + 100 + 34, 120, PropType.BOXCOIN);
        controller.addWall(startPoint + 100 + 2 * 34, 120);
        controller.addBox(startPoint + 100 + 34, 240, PropType.BOXCOIN);
        return 100 + 34 * 2 + 200; // length
    }

    private static int pipeArea(int startPoint) {
        controller.addSmallPipe(startPoint);
        controller.addEnemy(startPoint + 100, 0);
        controller.addSmallPipe(startPoint + 200);
        controller.addEnemy(startPoint + 300, 0);
        controller.addCoin(startPoint + 350, 200);
        controller.addBigPipe(startPoint + 400);
//        controller.addEnemy(PipeAreaStart + 450, 0);
        controller.addCoin(startPoint + 520, 290);
        controller.addEnemy(startPoint + 550, 0);
        controller.addBigPipe(startPoint + 630);
        controller.addEnemy(startPoint + 700, 0);
        controller.addSmallPipe(startPoint + 800);
        return 1000;
    }

    private static int hadBetterClimb(int startPoint) {
        // you had better climb the wall
        for (int i = 0; i < 5; i++) {
            for (int j = Math.max(i - 1, 0); j < i; j++) {
                controller.addWall(startPoint + 100 * i, 34 * (2 * j + 1));
            }
        }
        controller.addCoin(startPoint + 90 * 5, 34 * 9);
        for (int i = 0; i < 5; i++) {
            controller.addWall(startPoint + 100 * (5 + i * 2), 34 * 7);
            if (i == 0) {
                continue;
            }
            if (i % 2 == 0) {
                controller.addCoin(startPoint + 100 * (5 + i * 2), 34 * 9);
            } else {
//                controller.addEnemy(startPoint + 100 * (5 + i * 2), 34 * 8);
            }
        }
        return 100 * (5 + 4 * 2) + 400;
    }

    private static int breakTheWall(int startPoint) {
        // you can break the wall
        int interval = 80;
        int boxHeight = 34;
        controller.addWall(startPoint, 80);
        controller.addBox(startPoint, 80 + boxHeight);
        controller.addWall(startPoint + boxHeight + interval, 80);
        controller.addBox(startPoint + boxHeight + interval, 80 + boxHeight);
        controller.addWall(startPoint + boxHeight * 2 + interval * 2, 80);
        controller.addBox(startPoint + boxHeight * 2 + interval * 2, 80 + boxHeight);

        controller.addBox(startPoint + 34 + interval, 80 + boxHeight + interval, PropType.POWERUP);
        controller.addWall(startPoint + 34 + interval, 80 + 2 * boxHeight + interval);
        return boxHeight * 2 + interval * 2 + 200;
    }

    private static int breakWallChallenge(int startPoint) {
//        controller.addBox(80, 50, PropType.POWERUP);
        int center = startPoint + 200;
        int boxHeight = 34;
        int lower = 50;
        controller.addWall(center, lower);
//        controller.addWall(center, lower + boxHeight);
        controller.addWall(center - boxHeight, lower + boxHeight);
        controller.addWall(center + boxHeight, lower + boxHeight);
        controller.addCoin(center + 5, lower + boxHeight);
        controller.addWall(center - boxHeight * 2, lower + boxHeight * 2);
        controller.addWall(center + boxHeight * 2, lower + boxHeight * 2);
        for (int i = 0; i < 3; i++) {
            controller.addCoin(center + (i - 1) * boxHeight + 5, lower + boxHeight * 2);
        }
//        controller.addBox(center + boxHeight * 2, lower + boxHeight * 2);
//        controller.addBox(center - boxHeight * 2, lower + boxHeight * 2);
        controller.addWall(center - boxHeight, lower + boxHeight * 3);
        controller.addWall(center + boxHeight, lower + boxHeight * 3);
        controller.addCoin(center + 5, lower + boxHeight * 3);
        controller.addBox(center, lower + boxHeight * 4);
        return lower + boxHeight * 3 + 200;
    }


    public static void start() {
        int initialPoint = 400;
        initialPoint += intro(initialPoint);
        initialPoint += pipeArea(initialPoint);
        initialPoint += hadBetterClimb(initialPoint);
        initialPoint += breakTheWall(initialPoint);
        initialPoint += breakWallChallenge(initialPoint);
        controller.setFlagPos(initialPoint + 200, 0);
    }
}
