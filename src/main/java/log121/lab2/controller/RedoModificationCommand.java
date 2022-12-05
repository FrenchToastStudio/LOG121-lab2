package log121.lab2.controller;

import log121.lab2.model.Perspective;

public class RedoModificationCommand extends Command{

    private final PerspectiveMomento momento;
    private ModificationController modificationController;
    private Perspective perspective;
    private boolean hasToExecute;

    public RedoModificationCommand(PerspectiveMomento momento, ModificationController modificationController){
        this.momento = momento;
        this.modificationController = modificationController;
    }

    @Override
    public void execute() {
        this.perspective = momento.getPerspective();
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
}
