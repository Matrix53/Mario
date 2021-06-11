package controller;

public class MainController {
    private MainController(){}

    public static MainController getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private final MainController instance;

        Singleton(){
            instance=new MainController();
        }

        private MainController getInstance(){
            return instance;
        }
    }
}
