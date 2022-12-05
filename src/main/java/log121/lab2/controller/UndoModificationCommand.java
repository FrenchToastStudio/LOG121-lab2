package log121.lab2.controller;

import log121.lab2.model.Perspective;

public class UndoModificationCommand extends Command {
    private ModificationController modificationController;
    private final Perspective perspective;
    private final PerspectiveMomento momento;
    private boolean hasToExecute;


    public UndoModificationCommand(PerspectiveMomento momento, Perspective perspective){
        this.momento = momento;
        this.perspective = perspective;
    }

    @Override
    public void execute() {
        momento.setPerspective(perspective);
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
