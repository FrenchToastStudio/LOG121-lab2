package log121.lab2.controller;

import log121.lab2.service.JSONWriter;
import log121.lab2.service.SaveState;

public class SaveImageCommand extends Command{
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
