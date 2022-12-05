package log121.lab2.controller.commands;

import log121.lab2.controller.ModificationController;
import log121.lab2.controller.commands.Command;

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
    public boolean isConditionMet() {
        return needExectuion;
    }

    public void toggle()
    {
        needExectuion = true;
    }
}
