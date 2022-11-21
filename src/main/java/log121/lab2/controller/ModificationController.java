package log121.lab2.controller;

import log121.lab2.model.Perspective;

public class ModificationController extends ImageController{
    private Perspective perspective;

    public void translate(int x, int y){

    }

    public void zoom(int percentage){

    }

    public void undo(){

    }

    private PerspectiveMomento createMomento(){
        PerspectiveMomento m = new PerspectiveMomento();
        m.setPerspective(this.perspective);
        return m;
    }

    private void setMomento(PerspectiveMomento m){
        this.perspective = m.getPerspective();
    }
}
