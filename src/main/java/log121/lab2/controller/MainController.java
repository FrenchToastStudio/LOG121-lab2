package log121.lab2.controller;

import log121.lab2.view.MainView;

public class MainController {

    MainView mainView;
    public MainController(MainView mainView)
    {
        this.mainView = mainView;
    }

    public void changeView(int viewId)
    {
        mainView.ChangeImageView(viewId);
    }

}
