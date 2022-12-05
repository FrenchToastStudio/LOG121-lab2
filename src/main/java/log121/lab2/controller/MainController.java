package log121.lab2.controller;

import log121.lab2.model.Image;
import log121.lab2.model.Perspective;
import log121.lab2.model.Store;
import log121.lab2.service.ImagePicker;
import log121.lab2.service.JSONReader;
import log121.lab2.service.JSONWriter;
import log121.lab2.service.SaveState;
import log121.lab2.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private static final int NUMBER_OF_MODIFICATION_VIEW = 2;
    private MainView mainView;
    private Image image;
    public MainController(MainView mainView)
    {
        this.mainView = mainView;
        this.image = new Image();
        setImage(image);
        image.setPath("src/main/resources/log121/lab2/Image/IMG_0661.jpg");
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
        this.image = saveState.getImage();
        List<Perspective> perspectives = saveState.getPerspectiveList();

        setPerspective(perspectives);

        mainView.attachViewsToSubject(image);

        Store.getInstance().setPerspectives(perspectives);
        Store.getInstance().setImage(image);

    }

    private void setImage(Image image)
    {
        Store.getInstance().setImage(image);
    }

    private void setPerspective(List<Perspective> perspectives)
    {
        List<ModificationController> controllers = new ArrayList<>();
        for (Perspective perspective: perspectives) {
            controllers.add(new ModificationController(perspective));
        }

        mainView.setImageViews(controllers);
    }
    private ModificationController setPerspective(Perspective perspective)
    {
        return new ModificationController(perspective);
    }
    public void resetViews()
    {

        List<ModificationController> modificationControllers = new ArrayList<>();
        Store.getInstance().resetPerspective();
        for(int i =0; i < NUMBER_OF_MODIFICATION_VIEW; i++)
        {
            modificationControllers.add(createNewModificationController());
        }
        mainView.setImageViews(modificationControllers);
        mainView.attachViewsToSubject(image);
    }
    private ModificationController createNewModificationController()
    {
        Perspective perspective = new Perspective();
        image.addPerspective(perspective);
        perspective.setPosition(this.mainView.getWidth()/2, this.mainView.getHeight()/2);
        perspective.setPosition(this.mainView.getWidth()/2, this.mainView.getHeight()/2);
        return new ModificationController(perspective);
    }


    public void changeImage()
    {
        ImagePicker imagePicker = new ImagePicker();
        var newPath = imagePicker.pickImage();
        if(newPath.trim().isEmpty())
        {
            return;
        }
        Store.getInstance().getImage().setPath(newPath);
    }
}
