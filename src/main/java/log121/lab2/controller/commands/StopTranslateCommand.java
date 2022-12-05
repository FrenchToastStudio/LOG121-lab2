package log121.lab2.controller.commands;

import log121.lab2.controller.Command;
import log121.lab2.controller.ModificationController;

public class StopTranslateCommand extends Command {
    private ModificationController modificationController;
    private boolean hasToExecute;

    public StopTranslateCommand(ModificationController modificationController){
        this.modificationController=modificationController;
    }

    @Override
    public void execute() {
        modificationController.stopTranslate();
        hasToExecute = false;
    }

    @Override
    public void unExecute() {

    }

    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    public void toggle() {
        hasToExecute = true;
    }
}
