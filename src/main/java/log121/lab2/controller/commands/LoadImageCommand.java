package log121.lab2.controller.commands;


import log121.lab2.controller.MainController;
import log121.lab2.controller.commands.Command;

public class LoadImageCommand extends Command {

    private boolean hasToExecute;

    private MainController mainController;

    public LoadImageCommand(MainController mainController) {
       this.mainController = mainController;
    }

    @Override
    public void execute() {
        this.mainController.load();
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