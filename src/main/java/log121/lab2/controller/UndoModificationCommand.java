package log121.lab2.controller;

import log121.lab2.model.Perspective;

public class UndoModificationCommand extends Command {
    Perspective perspective;
    PerspectiveMomento momento;

    public UndoModificationCommand(PerspectiveMomento momento, Perspective perspective){
        this.momento = momento;
        this.perspective = perspective;
    }


    @Override
    public void execute() {
        momento.setPerspective(perspective);
    }

    @Override
    public void unExecute() {
        this.perspective = momento.getPerspective();
    }

    @Override
    public boolean isConditionMet() {
        return false;
    }
}
