package log121.lab2.controller;

import log121.lab2.service.JSONReader;

public class LoadImageCommand extends Command{

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
