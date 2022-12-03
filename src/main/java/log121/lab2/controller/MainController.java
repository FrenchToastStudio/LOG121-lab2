package log121.lab2.controller;

import log121.lab2.model.Image;
import log121.lab2.model.Store;
import log121.lab2.view.ImageLabel;
import log121.lab2.view.MainView;

public class MainController {

    MainView mainView;
    public MainController(MainView mainView)
    {
        this.mainView = mainView;
        Image img = new Image();
        mainView.attach(img);
        Store.getInstance().setImage(img);
        img.setPath("src/main/resources/log121/lab2/Image/IMG_0661.jpg");
    }

    public void changeView(int viewId)
    {
        mainView.ChangeImageView(viewId);
    }
}
