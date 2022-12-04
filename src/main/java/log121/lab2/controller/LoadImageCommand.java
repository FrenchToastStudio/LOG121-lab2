package log121.lab2.controller;

import log121.lab2.service.LoadState;

public class LoadImageCommand extends Command{

    private boolean hasToExecute;

    private LoadState loadState;

    public LoadImageCommand(LoadState loadState) {
        this.loadState = loadState;
    }

    @Override
    public void execute() {
        loadState.load();
        if(hasToExecute)
            hasToExecute = false;
    }

    @Override
    public void unExecute() {

    }

    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    public void setCondition(boolean b) {
        hasToExecute = b;
    }
}
