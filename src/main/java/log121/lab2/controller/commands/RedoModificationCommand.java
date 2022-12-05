package log121.lab2.controller.commands;

import log121.lab2.controller.ModificationController;
import log121.lab2.controller.commands.Command;

public class RedoModificationCommand extends Command {

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
    public boolean isConditionMet() {
        return hasToExecute;
    }

    public void toggle(){hasToExecute = true;}
}
