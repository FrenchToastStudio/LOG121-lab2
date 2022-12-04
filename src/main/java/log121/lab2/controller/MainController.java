package log121.lab2.controller;

import log121.lab2.model.Image;
import log121.lab2.model.Store;
import log121.lab2.service.JSONWriter;
import log121.lab2.service.SaveState;
import log121.lab2.view.ImageLabel;
import log121.lab2.view.MainView;

public class MainController {

    MainView mainView;
    public MainController(MainView mainView)
    {
        this.mainView = mainView;
        Image img = new Image();
        mainView.attach(img);
        img.setPath("src/main/resources/log121/lab2/Image/IMG_0661.jpg");
        Store.getInstance().setImage(img);
    }

    public void changeView(int viewId)
    {
        mainView.ChangeImageView(viewId);
    }

    public void save(){
        SaveState saveState = new SaveState(Store.getInstance().getPerspectives(), Store.getInstance().getImage());
        JSONWriter writer = new JSONWriter();
        writer.saveState(saveState);
    }

    public void load(){

    }
}
