package log121.lab2.controller;

import log121.lab2.model.Image;
import log121.lab2.model.Perspective;
import log121.lab2.model.Store;
import log121.lab2.service.JSONReader;
import log121.lab2.service.JSONWriter;
import log121.lab2.service.SaveState;
import log121.lab2.view.MainView;

import java.util.List;

public class MainController {
    private final MainView mainView;
    private final CopyImageMediator copyImageMediator;
    public MainController(MainView mainView)
    {
        this.mainView = mainView;
        Image img = new Image();
        setImage(img);
        img.setPath("src/main/resources/log121/lab2/Image/IMG_0661.jpg");
        copyImageMediator = new CopyImageMediator();
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
        JSONReader jsonReader = new JSONReader();
        SaveState saveState = jsonReader.load();
        Image image = saveState.getImage();
        List<Perspective> perspectives = saveState.getPerspectiveList();

        setPerspective(perspectives);
        setImage(image);

        Store.getInstance().setPerspectives(perspectives);
    }

    private void setImage(Image image)
    {
        this.mainView.attach(image);
        Store.getInstance().setImage(image);
    }
    private void setPerspective(List<Perspective> perspectives)
    {
        mainView.setImageViews(
         perspectives.stream().map(this::setPerspective).toList()
        );
    }
    private ModificationController setPerspective(Perspective perspective)
    {
        return new ModificationController(perspective);
    }
}
