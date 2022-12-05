package log121.lab2.controller;

import log121.lab2.model.Perspective;

public class UndoModificationCommand extends Command {
    private ModificationController modificationController;
    private boolean needExectuion;

    public UndoModificationCommand(ModificationController modificationController){
        this.modificationController = modificationController;
    }

    @Override
    public void execute() {
        modificationController.undo();
        needExectuion = false;
    }

    @Override
    public void unExecute() {
    }

    @Override
    public boolean isConditionMet() {
        return needExectuion;
    }

    public void toggle()
    {
        needExectuion = true;
    }
}
