package log121.lab2.controller;

import log121.lab2.model.Position;

public class ZoomImageCommand extends Command{

    private ModificationController modificationController;
    private boolean hasToExecute;

    private int zoom;
    public ZoomImageCommand(ModificationController modificationController)
    {
        this.modificationController = modificationController;
    }

    @Override
    public void execute() {
        modificationController.zoom(zoom);
        if(hasToExecute)
            hasToExecute = false;
    }

    @Override
    public void unExecute() {

    }

    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    public void changeZoom(int zoom){
        this.zoom = zoom;
        hasToExecute = true;
    }
}
