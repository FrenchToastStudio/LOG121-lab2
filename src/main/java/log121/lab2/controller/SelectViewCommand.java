package log121.lab2.controller;

public class SelectViewCommand extends Command{

    private MainController mainController;
    private int viewId;
    private boolean hasToExecute;

    public SelectViewCommand(MainController mainController, int viewId)
    {
        this.mainController = mainController;
        this.viewId = viewId;
    }

    @Override
    public void execute() {
        mainController.changeView(viewId);
        if(hasToExecute)
            hasToExecute = false;
    }

    @Override
    public boolean isConditionMet() {
        return hasToExecute;
    }

    public void setCondition(boolean bool)
    {
        hasToExecute = bool;
    }
}
