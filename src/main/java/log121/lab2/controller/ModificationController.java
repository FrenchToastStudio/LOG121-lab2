package log121.lab2.controller;

import log121.lab2.model.Perspective;
import log121.lab2.model.Position;
import log121.lab2.model.Store;
import log121.lab2.view.ImageView;
import log121.lab2.view.ModificationImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ModificationController extends ImageController{
    private Perspective perspective;
    private List<PerspectiveMomento> perspectiveMomentos;
    private int pointer = -1;

    public ModificationController(ImageView imageView) {
        super(imageView);
        this.perspective = new Perspective();
        this.perspectiveMomentos = new ArrayList<>();

        perspective.attach(imageView);
        Store.getInstance().addPerspective(perspective);
    }

    public ModificationController(Perspective perspective)
    {
        super();
        this.perspective = perspective;
    }

    private void addMomento(){
        if (pointer>=0) {
            perspectiveMomentos = perspectiveMomentos.subList(0, pointer);
        }
        PerspectiveMomento memento = new PerspectiveMomento(this.perspective);
        perspectiveMomentos.add(memento);
        pointer = perspectiveMomentos.size()-1;

    }
    public void setView(ModificationImageView modificationImageView)
    {
        super.setView(modificationImageView);
        perspective.attach(modificationImageView);
    }

    public void translate(Position position){
        this.perspective.setPosition(position);
    }

    public void stopTranslate(){
        addMomento();
    }

    public void zoom(int zoom){
        this.perspective.setZoom(-zoom);
        addMomento();
    }

    public void undo()
    {
        if (pointer>0) {
            pointer--;
            PerspectiveMomento momento = perspectiveMomentos.get(pointer);
            momento.setPerspectiveToState(this.perspective);

        }
        addMomento();
    }

    public void redo()
    {
        if (pointer<perspectiveMomentos.size()-1){
            pointer++;
            PerspectiveMomento momento = perspectiveMomentos.get(pointer);
            momento.setPerspectiveToState(this.perspective);
        }
        addMomento();
    }

    public void copy() {
        CopyImageMediator.getInstance().copy(this.perspective);
    }
    public void paste(Position position, int width, int height)
    {
        this.perspective.setPosition(position);
        this.perspective.setWidth(width);
        this.perspective.setHeight(height);
    }
    public void paste(Position position)
    {
        this.perspective.setPosition(position);
    }
    public void paste(int width, int height)
    {
        this.perspective.setWidth(width);
        this.perspective.setHeight(height);
    }
}
