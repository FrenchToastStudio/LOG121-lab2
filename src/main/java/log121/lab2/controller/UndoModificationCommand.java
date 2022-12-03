package log121.lab2.controller;

import log121.lab2.model.Perspective;

public class UndoModificationCommand extends Command {
    Perspective perspective;
    PerspectiveMemento memento;

    public UndoModificationCommand(PerspectiveMemento memento, Perspective perspective){
        this.memento = memento;
        this.perspective = perspective;
    }

    @Override
    public void execute() {
        memento.setPerspective(perspective);
    }

    @Override
    public void unExecute() {
        this.perspective = memento.getPerspective();
    }

    @Override
    public boolean isConditionMet() {
        return false;
    }
}
