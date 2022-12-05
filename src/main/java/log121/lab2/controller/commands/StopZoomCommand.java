package log121.lab2.controller.commands;

import log121.lab2.controller.ModificationController;

public class StopZoomCommand extends Command {
    private final ModificationController modificationController;
    private boolean hasToExecute = false;

    public StopZoomCommand(ModificationController modificationController)
    {
        this.modificationController = modificationController;
    }
    
    @Override
    public void execute() {
        modificationController.stopZoom();
        hasToExecute= false;
    }

    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    public void toggle()
    {
        hasToExecute = true;
    }
}
