package log121.lab2.controller.commands;

import log121.lab2.controller.MainController;
import log121.lab2.controller.commands.Command;

public class SaveImageCommand extends Command {
    private MainController mainController;

    private boolean hasToExecute;
    public SaveImageCommand(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void execute() {
        mainController.save();
        if(hasToExecute)
            hasToExecute = false;
    }


    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    public void setCondition(boolean b) {
        hasToExecute = b;
    }
}
