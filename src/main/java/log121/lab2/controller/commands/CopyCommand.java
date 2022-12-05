package log121.lab2.controller.commands;

import log121.lab2.controller.Command;
import log121.lab2.controller.ModificationController;

public class CopyCommand extends Command {


    private boolean needExectuion;
    private ModificationController modificationController;

    public CopyCommand(ModificationController modificationController)
    {
        this.modificationController = modificationController;
    }
    @Override
    public void execute()
    {
        modificationController.copy();
        needExectuion = false;
    }
    @Override
    public boolean isConditionMet() {
        return needExectuion;
    }

    public void toggle()
    {
        this.needExectuion = true;
    }
}
