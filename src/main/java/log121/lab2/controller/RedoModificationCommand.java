package log121.lab2.controller;

import log121.lab2.model.Perspective;

public class RedoModificationCommand extends Command{

    private ModificationController modificationController;
    private boolean hasToExecute;

    public RedoModificationCommand(ModificationController modificationController){
        this.modificationController = modificationController;
    }

    @Override
    public void execute() {
        modificationController.redo();
        hasToExecute = false;
    }

    @Override
    public void unExecute() {

    }

    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    public void toggle(){hasToExecute = true;}
}
